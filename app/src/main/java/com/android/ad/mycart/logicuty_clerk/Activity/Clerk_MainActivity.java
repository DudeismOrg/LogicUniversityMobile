package com.android.ad.mycart.logicuty_clerk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ad.mycart.HomepageActivity;
import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Employee;
import com.android.ad.mycart.logicuty_clerk.Model.Clerk_User;
import com.android.ad.mycart.logicuty_hod.HodHome;

/**
 * Created by rajeev on 25/1/2017.
 */

public class Clerk_MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerkmain);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        clearErrorMessage();


        Button login = (Button) findViewById(R.id.buttonLogin);
        login.setOnClickListener(this);
    }

    private void clearErrorMessage() {
        TextView msg = (TextView) findViewById(R.id.loginMessage);
        msg.setText("");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonLogin:
                clearErrorMessage();
                checkLogin();
                break;

        }
    }

    private void checkLogin() {
        EditText userName = (EditText) findViewById(R.id.editTextUN);
        EditText password = (EditText) findViewById(R.id.editTextPwd);

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        LoginUser logInUser = new LoginUser(userName.getText().toString(), password.getText().toString());
        Employee loginEmp = LoginUser.ValidateUser(logInUser);

        ClerkCommon.CreateSharedPreference(getApplicationContext(), "UserId", String.valueOf(loginEmp.getUserID()) , "String");
        ClerkCommon.CreateSharedPreference(getApplicationContext(), "DepartmentID", String.valueOf(loginEmp.getDepartmentID()) , "Integer");


        if (loginEmp!=null) {
            if (loginEmp.getRoleName().equals("Head")) {
                Toast.makeText(this, "Redirecting as HOD...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, HodHome.class);
                intent.putExtra("Employee", loginEmp);
                Log.i("Debug", String.valueOf(loginEmp.getUserID()));
                startActivity(intent);
            } else if (loginEmp.getRoleName().equals("Representative")) {
                Toast.makeText(this, "Redirecting as Representative...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, HomepageActivity.class);
                intent.putExtra("Employee", loginEmp);
                Log.i("Debug", String.valueOf(loginEmp.getUserID()));
                startActivity(intent);
            } else if (loginEmp.getRoleName().equals("Clerk")) {
                Toast.makeText(this, "Redirecting as Clerk...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ClerkHome.class);
                intent.putExtra("Employee", loginEmp);
                Log.i("Debug", String.valueOf(loginEmp.getUserID()));
                startActivity(intent);
            }
            }
        else {
            Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Clerk_MainActivity.class);
            startActivity(intent);
        }
    }
}

