package com.android.ad.mycart.logicuty_clerk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.Clerk_User;

/**
 * Created by rajeev on 25/1/2017.
 */

public class Clerk_MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerkmain);
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

        Clerk_User logInUser = new Clerk_User(userName.getText().toString(), password.getText().toString());
        if (logInUser.ValidateUser()) {
            Clerk_User user = logInUser.GetUser();
            Toast.makeText(this, "Redirecting...", Toast.LENGTH_SHORT).show();
            ClerkCommon.CreateSharedPreference(getApplicationContext(), "UserId", String.valueOf(user.getUserID()) , "String");
            Intent intent = new Intent(this, ClerkHome.class);
            intent.putExtra("user", user);
            Log.i("Debug", String.valueOf(user.getUserID()));
            startActivity(intent);
        } else {
            TextView msgText = (TextView) findViewById(R.id.loginMessage);
            msgText.setText(R.string.login_error_message);
        }
    }
}

