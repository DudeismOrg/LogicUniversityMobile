package com.android.ad.mycart.logicuty_clerk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.DisbursementDetailsClass_Clerk;
import com.android.ad.mycart.logicuty_clerk.Model.DisbursementDetailsItemAdapter;
import com.android.ad.mycart.logicuty_clerk.Model.QueryUtils_ClerkRequisition;

import java.util.List;

/**
 * Created by rajeev on 3/2/2017.
 */

public class DisbursementDetails extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursementdetails_clerk);
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);

        Intent i = getIntent();
        String itemCode = i.getStringExtra("ReqItem");
        TextView t = (TextView) findViewById(R.id.requisitioniddisbursement);
        t.setText(itemCode);

        List<DisbursementDetailsClass_Clerk> dtails =  QueryUtils_ClerkRequisition.getDisbursementDetails(itemCode);

        ListView itemQuantityList = (ListView) findViewById(R.id.disbursementdetail_list);
        itemQuantityList.setAdapter(new DisbursementDetailsItemAdapter
                (this, dtails));}

        public void orderAccept(View view){
        Intent i = new Intent(this,DisbursementActivity_Clerk.class);
        startActivity(i);

    }
    }

