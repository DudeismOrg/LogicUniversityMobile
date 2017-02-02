package com.android.ad.mycart.logicuty_clerk;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.android.ad.mycart.JSONParser;
import com.android.ad.mycart.R;

import com.android.ad.mycart.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManageRole extends AppCompatActivity {

    private static final String Requisition_REQUEST_URL="http://172.23.134.192/InventoryService/Service.svc/Users/ENGL";
    JSONObject jsonobject;
    JSONArray jsonarray;
    List<Employee> eList;
    int DepartmentID;
    List<String> userNamesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerole);

        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences
                        (getApplicationContext());
        DepartmentID = pref.getInt("DepartmentID",1);

        //List<Employee> eList = Role.listEmployeeByDeptId(DepartmentID);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        DownloadJSON task = new DownloadJSON();
        task.execute(Requisition_REQUEST_URL);

        //List<String> schoolNames = new ArrayList<String>();
        //schoolNames = Role.listRole();

        //final Spinner mySpinner = (Spinner) findViewById(R.id.userSpinner);
        //mySpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, eList));

        Button changebutton = (Button) findViewById(R.id.changebutton);
        registerForContextMenu(changebutton);

        Button assignButton = (Button) findViewById(R.id.assignButton);
        assignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Employee e = (Employee) mySpinner.getTag();
                Employee.updateEmployee(e);*/
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

    // Download JSON file AsyncTask
    private class DownloadJSON extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String...urls) {
            // Locate the WorldPopulation Class
            eList = Role.listEmployeeByDeptId(DepartmentID);
            // Create an array to populate the spinner
            userNamesList = new ArrayList<String>();
            // JSON file URL address
            jsonobject = JSONParser
                    .getJSONFromUrl("http://172.23.134.192/InventoryService/Service.svc/Users/");

            try {
                // Locate the NodeList name
                jsonarray = jsonobject.getJSONArray("DepartmentID");
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonobject = jsonarray.getJSONObject(i);

                    Employee employee = new Employee();

                    employee.setUserID(Integer.parseInt(jsonobject.optString("UserID")));
                    employee.setFirstName(jsonobject.optString("FirstName"));
                    employee.setEmail(jsonobject.optString("Email"));
                    employee.setLastName(jsonobject.optString("LastName"));
                    employee.setDepartmentID(Integer.parseInt(jsonobject.optString("DepartmentID")));
                    employee.setDepartmentName(jsonobject.optString("DepartmentName"));
                    employee.setRoleID(Integer.parseInt(jsonobject.optString("RoleID")));
                    employee.setRoleName(jsonobject.optString("RoleName"));
                    eList.add(employee);

                    // Populate spinner with country names
                    userNamesList.add(jsonobject.optString("RoleName"));

                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Locate the spinner in activity_main.xml
            final Spinner mySpinner = (Spinner) findViewById(R.id.userSpinner);
            // Spinner adapter
            mySpinner
                    .setAdapter(new ArrayAdapter<String>(ManageRole.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            userNamesList));

            // Spinner on item click listener
            mySpinner
                    .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> arg0,
                                                   View arg1, int position, long arg3) {
                            // TODO Auto-generated method stub
                            // Locate the textviews in activity_main.xml
                            /*TextView txtrank = (TextView) findViewById(R.id.rank);
                            TextView txtcountry = (TextView) findViewById(R.id.country);
                            TextView txtpopulation = (TextView) findViewById(R.id.population);

                            // Set the text followed by the position
                            txtrank.setText("Rank : "
                                    + eList.get(position).getRank());
                            txtcountry.setText("Country : "
                                    + eList.get(position).getCountry());
                            txtpopulation.setText("Population : "
                                    + eList.get(position).getPopulation());*/
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            mySpinner.setPrompt("Select");
                        }
                    });
        }
    }

}