package com.android.ad.mycart.logicuty_clerk.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.ad.mycart.JSONParser;
import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.Supplier;
import com.android.ad.mycart.logicuty_clerk.Model.SupplierItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrder_MainActivity extends AppCompatActivity {
   // private static final String Requisition_REQUEST_URL="http://www.json-generator.com/api/json/get/bZbtAVxFRu?indent=2";

    private static final String JSON_SUPPLIERS ="http://172.23.134.192/InventoryService/Service.svc/Suppliers";

    JSONArray jsonarray;
    ArrayList<String> supplierList;
    List<com.android.ad.mycart.logicuty_clerk.Model.Supplier> suppliers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clerkpurchase_order);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        DownloadJSON task = new DownloadJSON();
        task.execute(JSON_SUPPLIERS);



    }

    // Download JSON file AsyncTask
    private class DownloadJSON extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String...urls) {
            // Locate the WorldPopulation Class
            suppliers = new ArrayList<>();
            supplierList = new ArrayList<>();

            // JSON file URL address
            jsonarray = JSONParser.getJSONArrayFromUrl("http://172.23.134.192/InventoryService/Service.svc/Suppliers");

            try {
                // Locate the NodeList name

                for (int i = 0; i < jsonarray.length(); i++) {
                   JSONObject currentsupplier = (JSONObject) jsonarray.get(i);
                    Supplier supplier = new Supplier(currentsupplier.getString("SupplierCode"), currentsupplier.getInt("SupplierId"));
                    supplierList.add(currentsupplier.getString("SupplierName"));
                    supplier.Items = new ArrayList<>();
                    JSONArray itemsArray = currentsupplier.getJSONArray("SupplierItems");

                    for (int x = 0; x < itemsArray.length(); x++){
                        JSONObject itemDetails = itemsArray.getJSONObject(x);
                        SupplierItem item = new SupplierItem(itemDetails.getInt("ItemId"), itemDetails.getString("ItemName"));
                        supplier.Items.add(item);
                    }

                    suppliers.add(supplier);
                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Locate the spinner in activity_main.xml
            final Spinner mySpinner = (Spinner) findViewById(R.id.supplier_spinner);
            final Spinner itemSpinner = (Spinner) findViewById(R.id.item_spinner);

            // Spinner adapter
            mySpinner
                    .setAdapter(new ArrayAdapter<String>(PurchaseOrder_MainActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            supplierList));

            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Supplier supplier = suppliers.get(position);
                    List<String> lstItems = new ArrayList<String>();
                    for (SupplierItem item : supplier.Items){
                        lstItems.add(item.getItemName());
                    }

                    itemSpinner
                            .setAdapter(new ArrayAdapter<String>(PurchaseOrder_MainActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    lstItems));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            // Spinner on item click listener

        }
    }

}
