package com.android.ad.mycart.logicuty_clerk.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.QueryUtils_ClerkRRetrival;
import com.android.ad.mycart.logicuty_clerk.Model.RetrivalClass_Clerk;
import com.android.ad.mycart.logicuty_clerk.Model.RetrivalItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeev on 1/2/2017.
 */

public class RetrivalActivity_Clerk extends AppCompatActivity {
    private static final String Retrival_REQUEST_URL = "http://172.23.200.42/LogicUniversityStore/InventoryService/Service.svc/Retreival/1";

    private RetrivalItemAdapter mAdapter;
    //public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrival_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        retrivalAsyncTask task = new retrivalAsyncTask();
        task.execute(Retrival_REQUEST_URL);

        // Find a reference to the {@link ListView} in the layout
        ListView retrivalListView = (ListView) findViewById(R.id.listretrival);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new RetrivalItemAdapter(this, new ArrayList<RetrivalClass_Clerk>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        retrivalListView.setAdapter(mAdapter);

    }



    private class retrivalAsyncTask extends AsyncTask<String,Void,List<RetrivalClass_Clerk>> {

        @Override
        protected List<RetrivalClass_Clerk> doInBackground(String... urls) {
            if(urls.length<1||urls[0]== null){
                return null;
            }
            List result = QueryUtils_ClerkRRetrival.getRetrivalDetails(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<RetrivalClass_Clerk> data) {

            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }


}
