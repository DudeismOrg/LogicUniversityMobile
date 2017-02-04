package com.android.ad.mycart.logicuty_clerk.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.Clerk_Item;
import com.android.ad.mycart.logicuty_clerk.Model.RequisitionItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeev on 26/1/2017.
 */

public class DisbursementList_Clerk extends Activity {

    private List<Clerk_Item> item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_disbursementrequisition);

        //getData();
        Bundle i = getIntent().getExtras();
        ArrayList<String> retrievePstnList = i.getStringArrayList("PositionList");
        Log.i("Value got", String.valueOf(retrievePstnList));

        ListView lv = (ListView) findViewById(R.id.Listitem);
        lv.setAdapter(new SimpleAdapter
                (this,item,R.layout.itemrow_clerk, new String[]{"itemCode", "quantity"},
                        new int[]{ R.id.itemCode, R.id.itemCount}));


        Button approve = (Button) findViewById(R.id.approveButton);
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DisbursementList_Clerk.this)
                        .setTitle("Approve Requisition")
                        .setMessage("Are you sure you want to approve this requisition?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DisbursementList_Clerk.this, "Approved", Toast.LENGTH_SHORT).show();
                                RequisitionItemAdapter.positions.clear();
                                Intent intent = new Intent(getApplicationContext(), RequisitionActivity_Clerk.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DisbursementList_Clerk.this, "Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        Button cancel = (Button) findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequisitionItemAdapter.positions.clear();
                Intent intent = new Intent(getApplicationContext(), RequisitionActivity_Clerk.class);
                startActivity(intent);
            }
        });
    }
}
