package com.android.ad.mycart.logicuty_clerk.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.Clerk_Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeev on 25/1/2017.
 */

public class Clerk_PurchaseOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    static List<String> supplierList;
    static List<String> categoryList;
    static List<String> itemList;
    static String supplier, category, item = "";
    public static List<Clerk_Item> cart;
    TextView cartCount;
    EditText itemQuatity;
    Spinner categorySpin, itemSpin, supplierSpin;
    Clerk_Item itemDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clerkpurchase_order);

        //SetParameters
        setParameters();

        supplierSpin.requestFocus();
        itemDescription.setText("");

        if (getIntent().getSerializableExtra("itemDetails") instanceof Clerk_Item) {
            itemDetails = (Clerk_Item) getIntent().getSerializableExtra("itemDetails");
        }


        //SetEventListeners
        setListeners();

    }

    ArrayAdapter supAdap, catAdap, itemAdap;

    private void setItemDetails() {

        if (itemDetails != null) {
            supAdap = (ArrayAdapter) supplierSpin.getAdapter(); //cast to an ArrayAdapter
            supplierSpin.setSelection(supAdap.getPosition(itemDetails.getSupplier()));
            catAdap = (ArrayAdapter) categorySpin.getAdapter(); //cast to an ArrayAdapter
            categorySpin.setSelection(catAdap.getPosition(itemDetails.getCategory()));
            itemAdap = (ArrayAdapter) itemSpin.getAdapter(); //cast to an ArrayAdapter
            itemSpin.setSelection(itemAdap.getPosition(itemDetails.getItem()));

            //categorySpin.setSelection(categoryList.indexOf(itemDetails.getCategory()));
            //itemSpin.setSelection(itemList.indexOf(itemDetails.getItem()));
            itemQuatity.setText(itemDetails.getQuantity());
            itemDescription.setText(itemDetails.getDescription());

        }
    }

    TextView itemDescription;

    private void setParameters() {
        supplierSpin = (Spinner) findViewById(R.id.supplier_spinner);
        itemSpin = (Spinner) findViewById(R.id.item_spinner);
        cartCount = (TextView) findViewById(R.id.cartCount);
        itemQuatity = (EditText) findViewById(R.id.quantityEditText);
        itemDescription = (TextView) findViewById(R.id.ItemDesc_TextView);
    }

    private void setListeners() {

        ImageButton button = (ImageButton) findViewById(R.id.addCart_click);
        button.setOnClickListener(this);
    }


    public void onClick(View v) {
        Log.i("debug", "clicked");
        if (cart == null)
            cart = new ArrayList<Clerk_Item>();
        switch (v.getId()) {
            case R.id.addCart_click:
                String quantity = itemQuatity.getText().toString();
                if (validateAddItem()) {
                    cart.add(new Clerk_Item(supplier, category, item, quantity));
                    String value = cartCount.getText().toString();
                    int count = Integer.parseInt(value);
                    cartCount.setText(String.valueOf(++count));
                    showToastMessage(this, "Item " + item + " has been added to the lstCart");
                    supplierSpin.setEnabled(false);
                    categorySpin.setSelection(0);
                    itemSpin.setSelection(0);
                    itemQuatity.setText("");
                    supplierSpin.requestFocus();
                    category = "";
                    item = "";
                }
                break;
            /*case R.id.removeCart_click:
                for (Item item : lstCart) {
                    if (item.getSupplier().equals(supplier) && item.getCategory().equals(category) && item.getItem().equals(item)) {
                        lstCart.remove(item);
                        showToastMessage(this, "Item " + item + "has been removed from the lstCart");
                    }
                }
                //Intent intent =*/
        }
        Intent i = new Intent(this,PurchaseOrder_MainActivity.class);
    }

    private boolean validateAddItem() {
        String quantity = itemQuatity.getText().toString();
        boolean isValid = false;
        if (category == null || category.isEmpty()) {
            showToastMessage(this, "Please select the category");
            return isValid;
        }
        if (item == null || item.isEmpty()) {
            showToastMessage(this, "Please select the Item");
            return isValid;
        }

        if (quantity == null || quantity.isEmpty()) {
            showToastMessage(this, "Please enter the quantity");
            return isValid;
        }
        if (Integer.parseInt(quantity) <= 0) {
            showToastMessage(this, "Quantity should be greater than zero");
            return isValid;
        }
        return !isValid;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0 && itemDetails == null) {
            switch (parent.getId()) {
                case R.id.supplier_spinner:
                    if (supplierList != null) {
                        supplier = supplierList.get(position);
                        showCategories(supplier);
                    }
                    break;
                case R.id.item_spinner:
                    if (itemList != null) {
                        item = itemList.get(position);
                    }
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        showToastMessage(getApplicationContext(), "Please select the category");
    }

    private void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }



    private void showCategories(String supplier) {
        //Get Categories of items supplied by a supplier

        categoryList = ClerkCommon.getCategoriesBySupplierId(supplier);
        categoryList.add(0, "Select Category");
        this.listAdapter(categorySpin, categoryList);
        if (itemDetails != null && itemDetails.getCategory() != "")
            showItems(supplier, itemDetails.getCategory());
    }

    private void showItems(String supplier, String category) {

        itemList = ClerkCommon.getItemsByCategorySupplier(supplier, category);
        itemList.add(0, "Select Item");
        this.listAdapter(itemSpin, itemList);
    }

    private void listAdapter(Spinner spinner, List listItems) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart_view_click:
                Intent cartIntent = new Intent(this, ClerkViewCart.class);
                cartIntent.putExtra("cart_items", (Serializable) cart);
                startActivity(cartIntent);
                return true;
            default:
                super.onOptionsItemSelected(item);
                return true;
        }
    }
}
