package com.android.ad.mycart.logicuty_clerk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ad.mycart.R;

import com.android.ad.mycart.R;

import java.util.ArrayList;
import java.util.List;

public class ManageRole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerole);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        List<String> schoolNames = new ArrayList<String>();
        schoolNames = Role.listRole();

        final Spinner mySpinner = (Spinner) findViewById(R.id.userSpinner);
        mySpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, schoolNames));

        Button changebutton = (Button) findViewById(R.id.changebutton);
        registerForContextMenu(changebutton);

        Button assignButton = (Button) findViewById(R.id.assignButton);
        assignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee e = (Employee) mySpinner.getTag();
                Employee.updateEmployee(e);
                Toast.makeText(getApplicationContext(), "Assigned Role", Toast.LENGTH_SHORT).show();
                recreate();
            }
        });
    }

    @Override
    public void registerForContextMenu(View view) {
        super.registerForContextMenu(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.role1:
                TextView role1 = (TextView) findViewById(R.id.userRole);
                role1.setText(item.toString());
                return true;
            case R.id.role2:
                TextView role2 = (TextView) findViewById(R.id.userRole);
                role2.setText(item.toString());
                return true;
            case R.id.role3:
                TextView role3 = (TextView) findViewById(R.id.userRole);
                role3.setText(item.toString());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}