package com.android.ad.mycart.logicuty_clerk.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class RequisitionActivity_Clerk extends Activity implements AdapterView.OnItemClickListener {

   /* private List<Clerk_Item> itemList=new ArrayList<>();
    private void getData(){
        itemList.add(new Clerk_Item("reqId1","itemCode1","description1","1"));
        itemList.add(new Clerk_Item("reqId2","itemCode2","description2","2"));
        itemList.add(new Clerk_Item("reqId3","itemCode3","description3","3"));
        itemList.add(new Clerk_Item("reqId4","itemCode4","description4","4"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisitionclerk);
        getData();
        Bundle b = getIntent().getExtras();
        HashMap<String,String> item = (HashMap<String,String>) b.get("value");


        TableLayout table = (TableLayout) findViewById(R.id.tableId);
        TableRow row = (TableRow) findViewById(R.id.reqRowId);
        TextView tv = (TextView)findViewById(R.id.reqID);
        tv.setText(item.get("reqId"));

        TableRow tableText = (TableRow) findViewById(R.id.gridviewTable);

        GridView tgrid = (GridView)findViewById(R.id.gridview);

        tgrid.setAdapter(new SimpleAdapter
                (this, itemList, R.layout.row, new String[]{"itemCode", "description", "quantity"},
                        new int[]{ R.id.text1, R.id.text2, R.id.text3}));

    }

*/
   private static final String Requisition_REQUEST_URL = "http://beta.json-generator.com/api/json/get/VyoU8LtDG";

    private RequisitionItemAdapter mAdapter;
    //public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requisition_activity);

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

