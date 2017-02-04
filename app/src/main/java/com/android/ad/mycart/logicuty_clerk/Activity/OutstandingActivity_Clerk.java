package com.android.ad.mycart.logicuty_clerk.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.OutstandingAdapter;
import com.android.ad.mycart.logicuty_clerk.Model.OutstandingClass_Clerk;
import com.android.ad.mycart.logicuty_clerk.Model.QueryUtils_ClerkOutstanding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeev on 1/2/2017.
 */

public class OutstandingActivity_Clerk extends AppCompatActivity {
    private static final String Requisition_REQUEST_URL = "http://172.23.134.192/LogicUniversityStore/InventoryService/Service.svc/Outstanding";

    private OutstandingAdapter mAdapter;
    //public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outstanding);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        OutstandingAsyncTask task = new OutstandingAsyncTask();
        task.execute(Requisition_REQUEST_URL);

        // Find a reference to the {@link ListView} in the layout
        ListView outstandingListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new OutstandingAdapter(this, new ArrayList<OutstandingClass_Clerk>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        outstandingListView.setAdapter(mAdapter);

    }



    private class OutstandingAsyncTask extends AsyncTask<String,Void,List<OutstandingClass_Clerk>> {

        @Override
        protected List<OutstandingClass_Clerk> doInBackground(String... urls) {
            if(urls.length<1||urls[0]== null){
                return null;
            }
            List result = QueryUtils_ClerkOutstanding.fetchOutstandingData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<OutstandingClass_Clerk> data) {

            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }


}
