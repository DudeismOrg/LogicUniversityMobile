package com.android.ad.mycart.logicuty_clerk.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.DisbursementClass_Clerk;
import com.android.ad.mycart.logicuty_clerk.Model.DisbursementItemAdapter;
import com.android.ad.mycart.logicuty_clerk.Model.QueryUtils_ClerkDisbursement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeev on 26/1/2017.
 */

public class DisbursementActivity_Clerk extends AppCompatActivity implements AdapterView.OnItemClickListener {

   private static final String Requisition_REQUEST_URL = "http://www.json-generator.com/api/json/get/bThTKumnsi?indent=2";

   // final static String host = "http://172.23.134.192/LogicUniversityStore/InventoryService/Service.svc/Disbursement";

    private DisbursementItemAdapter mAdapter;
    //public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disbursementrequisition_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        DisbursementAsyncTask task = new DisbursementAsyncTask();
        task.execute(Requisition_REQUEST_URL );

        // Find a reference to the {@link ListView} in the layout
       ListView disbursementistView = (ListView) findViewById(R.id.list_disbursementView);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new DisbursementItemAdapter(this, new ArrayList<DisbursementClass_Clerk>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        disbursementistView.setAdapter(mAdapter);



        final ListView list = (ListView) findViewById(R.id.list_disbursementView);
        final DisbursementItemAdapter itemAdapter = mAdapter;
        list.setAdapter(itemAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent appInfo = new Intent(DisbursementActivity_Clerk.this, DisbursementDetails.class);
                //appInfo.putExtra("reqNum",itemAdapter.get);
                //Object o =
                list.getItemAtPosition(position);
                //prestationEco str=(prestationEco)o;//As you are using Default String Adapter
                //Toast.makeText(getBaseContext(),str.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
                /*setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(DisbursementActivity_Clerk.this, DisbursementDetails.class);
                appInfo.putExtra("reqNum",itemAdapter.get);
                startActivity(appInfo);
            }
        });*/

        //----passing by intent
        Intent intent = new Intent(DisbursementActivity_Clerk.this, DisbursementDetails.class);

        startActivity(intent);







/*
        final ListView lv = (ListView) findViewById(R.id.list_disbursementView);
        final DisbursementItemAdapter itemAdapter = mAdapter;
        lv.setAdapter(itemAdapter);

    lv.setOnItemClickListener(this);
      Button b = (Button) findViewById(R.id.button);
      b.setOnClickListener(new View.OnClickListener()

         {
            @Override
            public void onClick(View v) {
                long[] itemIDs = lv.getCheckedItemIds();
                for (long val : itemIDs) {
                    Log.i("debg",String.valueOf(val));
                }

                ArrayList<String> pstnList = new ArrayList<String>(DisbursementItemAdapter.positions.size());
                for(Integer i: DisbursementItemAdapter.positions)
                {
                    pstnList.add(String.valueOf(i));
                }

                Intent i = new Intent(getApplicationContext(), DisbursementList_Clerk.class);
                i.putStringArrayListExtra("PositionList",pstnList);
                startActivity(i);
                Log.i("Positions List",String.valueOf(pstnList));
            }
        });

        //method trying


/*
        ListView lv = (ListView) findViewById(R.id.list_disbursementView);
        lv.setTextFilterEnabled(true);

// Bind onclick event handler
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
Intent i = new Intent(getApplicationContext(),DisbursementDetails.class);
                startActivity(i);
                // Put in your code here, what you wanted trigger :)
            }
        }); */


    }




    private class DisbursementAsyncTask extends AsyncTask<String,Void,List<DisbursementClass_Clerk>> {

        @Override
        protected List<DisbursementClass_Clerk> doInBackground(String... urls) {
            if(urls.length<1||urls[0]== null){
                return null;
            }
            List result = QueryUtils_ClerkDisbursement.fetchRequisitionData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<DisbursementClass_Clerk> data) {

            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {

        DisbursementClass_Clerk req = (DisbursementClass_Clerk) av.getItemAtPosition(position);

        Intent disbursementActivity = new Intent(this, DisbursementDetails.class);
        disbursementActivity.putExtra("value", req);
        startActivity(disbursementActivity);

        Toast.makeText(DisbursementActivity_Clerk.this, "Redirecting ", Toast.LENGTH_SHORT).show();
    }

}

