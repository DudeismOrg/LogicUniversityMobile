package com.android.ad.mycart.logicuty_hod;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.ApproveItem;
import com.android.ad.mycart.logicuty_clerk.Model.ApproveItemAdapter;

import java.util.ArrayList;

/**
 * Created by Tyler Durden on 2/5/2017.
 */

public class ApproveCancelDetails extends AppCompatActivity {
    private static final String TAG = "ShoppingCartActivity";

    ListView lvCartItems;
    Button bApprove;
    Button bReject;
    TextView tvTotalPrice;
    private static final String RequisitionItem_REQUEST_URL = "http://172.23.200.42/LogicUniversityStore/InventoryService/Service.svc/Requisition/";
    private static final String AckRequisition_REQUEST_URL = "http://172.23.200.42/LogicUniversityStore/InventoryService/Service.svc/AckRequisition";
    private String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvecancelfinal);

        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.my_carttoolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        lvCartItems = (ListView) findViewById(R.id.approvecancel_list);
        LayoutInflater layoutInflater = getLayoutInflater();
        final String ReqId = getIntent().getStringExtra("RequisitionId");
        //final ApproveItem cart = CartHelper.getCart();
        // final TextView tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
        // tvTotalPrice.setText(Constant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));

        //lvCartItems.addHeaderView(layoutInflater.inflate(R.layout.cart_header, lvCartItems, false));

        String giv = getIntent().getStringExtra("RequisitionId");

        ArrayList<ApproveItem> lstItems = ApproveItem.list(RequisitionItem_REQUEST_URL + giv);

        final ApproveItemAdapter approveItemAdapter = new ApproveItemAdapter(getApplicationContext(), lstItems);
        lvCartItems.setAdapter(approveItemAdapter);

        bApprove = (Button) findViewById(R.id.approvebuttonapprove);
        bReject = (Button) findViewById(R.id.cancelbuttonapprove);

        bApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref =
                        PreferenceManager.getDefaultSharedPreferences
                                (getApplicationContext());
                UserId = String.valueOf(pref.getString("UserId", "0"));
                boolean result = ApproveItem.AckRequisition(AckRequisition_REQUEST_URL, UserId, "",ReqId,"Approved");
                if(result){
                    Toast.makeText(getApplicationContext(), "Approved Requisition request", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HodHome.class);
                    startActivity(intent);
                }
            }
        });

        bReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref =
                        PreferenceManager.getDefaultSharedPreferences
                                (getApplicationContext());
                UserId = String.valueOf(pref.getString("UserId", "0"));
                boolean result = ApproveItem.AckRequisition(AckRequisition_REQUEST_URL, UserId, "",ReqId,"Rejected");
                if(result){
                    Toast.makeText(getApplicationContext(), "Rejected Requisition request", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HodHome.class);
                    startActivity(intent);
                }
            }
        });

        /*lvCartItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(com.android.ad.mycart.ShoppingCartActivity.this)
                        .setTitle(getResources().getString(R.string.delete_item))
                        .setMessage(getResources().getString(R.string.delete_item_message))
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                List<CartItem> cartItems = getCartItems(cart);
                                cart.remove(cartItems.get(position-1).getProduct());
                                cartItems.remove(position-1);
                                cartItemAdapter.updateCartItems(cartItems);
                                cartItemAdapter.notifyDataSetChanged();
                                tvTotalPrice.setText(Constant.CURRENCY+String.valueOf(cart.getTotalPrice().setScale(2, BigDecimal.ROUND_HALF_UP)));
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
                Bundle bundle = new Bundle();
                List<CartItem> cartItems = getCartItems(cart);
                Product product = cartItems.get(position-1).getProduct();
                Log.d(TAG, "Viewing product: " + product.getName());
                bundle.putSerializable("product", product);
                Intent intent = new Intent(com.android.ad.mycart.ShoppingCartActivity.this, ProductActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private List<CartItem> getCartItems(Cart cart) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        Log.d(TAG, "Current shopping cart: " + cart);

        Map<Saleable, Integer> itemMap = cart.getItemWithQuantity();

        for (Map.Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct((Product) entry.getKey());
            cartItem.setQuantity(entry.getValue());
            cartItems.add(cartItem);
        }

        Log.d(TAG, "Cart item list: " + cartItems);
        return cartItems;
    }*/

    }
}
