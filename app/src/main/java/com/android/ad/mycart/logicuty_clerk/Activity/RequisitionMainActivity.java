package com.android.ad.mycart.logicuty_clerk.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.RequisitionClass_Clerk;
import com.android.ad.mycart.logicuty_clerk.Model.RequisitionItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeev on 26/1/2017.
 */

public class RequisitionMainActivity extends Activity implements AdapterView.OnItemClickListener {
    private ArrayList<RequisitionClass_Clerk> requisitions;
    private List<RequisitionClass_Clerk> selectedRequisitions;

    private void getData() {
        requisitions = new ArrayList<RequisitionClass_Clerk>();
        requisitions.add(new RequisitionClass_Clerk("A001", "CS", "20/01/2006",""));
        requisitions.add(new RequisitionClass_Clerk("A002", "ARTS", "20/07/2006",""));
        requisitions.add(new RequisitionClass_Clerk("A003", "ENGG", "20/08/2008",""));
        requisitions.add(new RequisitionClass_Clerk("A004", "CS", "20/12/2004",""));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_requisition);
        getData();
        final ListView lv = (ListView) findViewById(R.id.listView1);
        /*lv.setAdapter(new SimpleAdapter(
                this, requisitions, R.layout.row, new String[]{"reqId", "dept", "reqDate"}, new int[]{R.id.text1, R.id.text2, R.id.text3}
        ));*/
        RequisitionItemAdapter itemAdapter = new RequisitionItemAdapter(this, requisitions);
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
                for(Integer i:RequisitionItemAdapter.positions)
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

    @Override
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {
        //Toast.makeText(getApplicationContext(), s.get("name") + " selected",
        //  Toast.LENGTH_LONG).show();
        RequisitionClass_Clerk req = (RequisitionClass_Clerk) av.getItemAtPosition(position);

        Intent requisitionActivity = new Intent(this, RequisitionActivity_Clerk.class);
        requisitionActivity.putExtra("value", req);
        startActivity(requisitionActivity);

        Toast.makeText(RequisitionMainActivity.this, "Redirecting ", Toast.LENGTH_SHORT).show();
    }
}

