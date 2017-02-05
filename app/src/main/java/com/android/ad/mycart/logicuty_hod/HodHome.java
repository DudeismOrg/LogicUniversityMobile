package com.android.ad.mycart.logicuty_hod;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Activity.Clerk_PurchaseOrderActivity;
import com.android.ad.mycart.logicuty_clerk.ManageRole;
import com.android.ad.mycart.logicuty_clerk.Model.Clerk_User;

/**
 * Created by Tyler Durden on 2/1/2017.
 */

public class HodHome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hodhomepage);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Set Listener
        SetEventListeners();
        SetWelcomeMessage();

    }

    private void SetEventListeners() {
        Button managerole = (Button) findViewById(R.id.managerole_click);
        managerole.setOnClickListener(this);
        Button approvecancel = (Button) findViewById(R.id.approvecancel_click);
        approvecancel.setOnClickListener(this);
    }

    private void SetWelcomeMessage() {
        TextView welcomeMsg = (TextView) findViewById(R.id.welcomeMsg);
        Intent i = getIntent();
        welcomeMsg.setText("Welcome " + i.getStringExtra("EmployeeRole"));
    }

    public void onClick(View v) {
        Intent intent=null;

        switch (v.getId()) {
            case R.id.managerole_click:
                Toast.makeText(this, "Redirecting... Manage Role", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ManageRole.class);
                break;
            case R.id.approvecancel_click:
                Toast.makeText(this, "Redirecting... Approve/Cancel", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ApproveCancel.class);
                break;
        }

        startActivity(intent);
    }
}