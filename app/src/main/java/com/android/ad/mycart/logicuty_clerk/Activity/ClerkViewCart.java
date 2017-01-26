package com.android.ad.mycart.logicuty_clerk.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.CartItemAdapter_clerk;
import com.android.ad.mycart.logicuty_clerk.Model.Clerk_Item;

import java.util.ArrayList;

/**
 * Created by rajeev on 25/1/2017.
 */

public class ClerkViewCart extends AppCompatActivity implements View.OnClickListener {

    Button bShop;
    Button bClearCart;
    ArrayList<Clerk_Item> lstCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        bShop = (Button) findViewById(R.id.bShop_click);
        bShop.setOnClickListener(this);
        bClearCart = (Button) findViewById(R.id.bClear_click);
        bClearCart.setOnClickListener(this);

        lstCart = (ArrayList<Clerk_Item>) getIntent().getSerializableExtra("cart_items");
        Log.i("debug", String.valueOf(lstCart.size()));
        final CartItemAdapter_clerk itemAdapter = new CartItemAdapter_clerk(this,lstCart);
        ListView lvCartItems = (ListView) findViewById(R.id.lvCartItems);
        lvCartItems.addHeaderView(getLayoutInflater().inflate(R.layout.cart_header, lvCartItems, false));
        lvCartItems.setAdapter(itemAdapter);

        //Long Click delete
        lvCartItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(ClerkViewCart.this)
                        .setTitle(getResources().getString(R.string.delete_item))
                        .setMessage(getResources().getString(R.string.delete_item_message))
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                lstCart.remove(position - 1);
                                itemAdapter.setItems(lstCart);
                                itemAdapter.notifyDataSetChanged();
                                //tvTotalPrice.setText(Constant.CURRENCY + String.valueOf(lstCart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), null)
                        .show();
                return false;
            }
        });

        lvCartItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Clerk_Item item = lstCart.get(position - 1);
                Intent intent = new Intent(getApplicationContext(), Clerk_PurchaseOrderActivity.class);
                intent.putExtra("itemDetails", item);
                startActivity(intent);
            }
        });
    }

    public View getActionBarView() {
        Window window = getWindow();
        View v = window.getDecorView();
        int resId = getResources().getIdentifier("action_bar_container", "id", "android");
        return v.findViewById(resId);
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bShop_click:
                intent = new Intent(this, Clerk_PurchaseOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.bClear_click:
                lstCart.clear();
                Clerk_PurchaseOrderActivity.cart.clear();
                Toast.makeText(this, "Cart has been cleared!", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ClerkHome.class);
                startActivity(intent);
                break;
        }
    }
}

