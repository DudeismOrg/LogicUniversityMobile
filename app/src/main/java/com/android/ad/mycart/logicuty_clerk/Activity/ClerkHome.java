package com.android.ad.mycart.logicuty_clerk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.Clerk_User;

/**
 * Created by rajeev on 25/1/2017.
 */

public class ClerkHome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk_home);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Set Listener
        SetEventListeners();
        SetWelcomeMessage();

    }

    private void SetEventListeners() {
        Button outstanding = (Button) findViewById(R.id.outstanding_click);
        outstanding.setOnClickListener(this);
        Button requisition = (Button) findViewById(R.id.requisition_click);
        requisition.setOnClickListener(this);
        Button purchaseorder = (Button) findViewById(R.id.purchaseorder_click);
        purchaseorder.setOnClickListener(this);
        Button disbursement = (Button) findViewById(R.id.disbursement_click);
        disbursement.setOnClickListener(this);
    }

    private void SetWelcomeMessage() {
        TextView welcomeMsg = (TextView) findViewById(R.id.welcomeMsg);
        Clerk_User user = (Clerk_User) getIntent().getSerializableExtra("user");
        welcomeMsg.setText("Welcome " + user.getFirstName());
    }

    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()) {
            case R.id.outstanding_click:
                Toast.makeText(this, "Redirecting... Outstanding", Toast.LENGTH_SHORT).show();
                break;
            case R.id.requisition_click:
                Toast.makeText(this, "Redirecting... Requisition", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,RequisitionActivity_Clerk.class);
                break;
            case R.id.purchaseorder_click:
                Toast.makeText(this, "Redirecting... Purchase Order", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, Clerk_PurchaseOrderActivity.class);
                break;
            case R.id.disbursement_click:
                Toast.makeText(this, "Redirecting... Disbursement", Toast.LENGTH_SHORT).show();
                break;
        }
        startActivity(intent);
    }
}
