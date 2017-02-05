package com.android.ad.mycart.logicuty_clerk.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.QueryUtils_ClerkRequisition;
import com.android.ad.mycart.logicuty_clerk.Model.RequisitionClass_Clerk;
import com.android.ad.mycart.logicuty_clerk.Model.RequisitionItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeev on 26/1/2017.
 */

public class RequisitionActivity_Clerk extends AppCompatActivity implements AdapterView.OnItemClickListener {

   private static final String Requisition_REQUEST_URL = "http://172.23.134.192/LogicUniversityStore/InventoryService/Service.svc/Requisition/Status/Requested";

    private RequisitionItemAdapter mAdapter;
    //public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requisition_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        RequisitionAsyncTask task = new RequisitionAsyncTask();
        task.execute(Requisition_REQUEST_URL);

        // Find a reference to the {@link ListView} in the layout
        //ListView requisitionListView = (ListView) findViewById(R.id.listView1);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new RequisitionItemAdapter(this, new ArrayList<RequisitionClass_Clerk>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        //requisitionListView.setAdapter(mAdapter);


        final ListView lv = (ListView) findViewById(R.id.listView1);
        /*lv.setAdapter(new SimpleAdapter(
                this, requisitions, R.layout.row, new String[]{"reqId", "dept", "reqDate"}, new int[]{R.id.text1, R.id.text2, R.id.text3}
        ));*/
        final RequisitionItemAdapter itemAdapter = mAdapter;
        lv.setAdapter(itemAdapter);

        lv.setOnItemClickListener(this);
        Button b = (Button) findViewById(R.id.generateReqButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long[] itemIDs = lv.getCheckedItemIds();
                for (long val : itemIDs) {
                    Log.i("debg",String.valueOf(val));
                }

                ArrayList<String> pstnList = new ArrayList<String>(RequisitionItemAdapter.positions.size());
                for(Integer i: RequisitionItemAdapter.positions)
                {
                    pstnList.add(String.valueOf(i));
                }

                Intent i = new Intent(getApplicationContext(), RequisitionList_Clerk.class);
                i.putStringArrayListExtra("PositionList",pstnList);
                startActivity(i);
                Log.i("Positions List",String.valueOf(pstnList));
            }
        });

    }




    private class RequisitionAsyncTask extends AsyncTask<String,Void,List<RequisitionClass_Clerk>> {

        @Override
        protected List<RequisitionClass_Clerk> doInBackground(String... urls) {
            if(urls.length<1||urls[0]== null){
                return null;
            }
            List result = QueryUtils_ClerkRequisition.fetchRequisitionData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<RequisitionClass_Clerk> data) {

            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {
        //Toast.makeText(getApplicationContext(), s.get("name") + " selected",
        //  Toast.LENGTH_LONG).show();
        RequisitionClass_Clerk req = (RequisitionClass_Clerk) av.getItemAtPosition(position);

        Intent requisitionActivity = new Intent(this, RequisitionActivity_Clerk.class);
        requisitionActivity.putExtra("value", req);
        startActivity(requisitionActivity);

        Toast.makeText(RequisitionActivity_Clerk.this, "Redirecting ", Toast.LENGTH_SHORT).show();
    }

}

