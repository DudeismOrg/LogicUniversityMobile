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


        if (loginEmp!=null) {
            ClerkCommon.CreateSharedPreference(getApplicationContext(), "UserId", String.valueOf(loginEmp.getUserID()) , "String");
            ClerkCommon.CreateSharedPreference(getApplicationContext(), "DepartmentID", String.valueOf(loginEmp.getDepartmentID()) , "Integer");
            switch (loginEmp.getRoleCode())
            {
                case "HOD": Toast.makeText(this, "Redirecting as HOD...", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(this, HodHome.class);
                        intent1.putExtra("EmployeeRole", loginEmp.getRoleName());
                        Log.i("Debug", String.valueOf(loginEmp.getUserID()));
                        startActivity(intent1);
                        break;
                case "EMP": Toast.makeText(this, "Redirecting as Employee...", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(this, HomepageActivity.class);
                        //intent2.putExtra("Employee", loginEmp);
                        Log.i("Debug", String.valueOf(loginEmp.getUserID()));
                        startActivity(intent2);
                        break;
                case "CLERK": Toast.makeText(this, "Redirecting as Clerk...", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(this, ClerkHome.class);
                        intent3.putExtra("EmployeeRole", loginEmp.getRoleName());
                        Log.i("Debug", String.valueOf(loginEmp.getUserID()));
                        startActivity(intent3);
                        break;
            }
            }
        else {
            Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Clerk_MainActivity.class);
            startActivity(intent);
        }
    }
}

