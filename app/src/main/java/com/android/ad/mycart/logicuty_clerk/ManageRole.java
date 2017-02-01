package com.android.ad.mycart.logicuty_clerk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.ad.mycart.R;

import java.util.ArrayList;
import java.util.List;

public class ManageRole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerole);


        List<String> schoolNames = new ArrayList<String>();
        schoolNames = Role.listRole();

        Spinner mySpinner = (Spinner)findViewById(R.id.userSpinner);
        mySpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, schoolNames));

        TextView userRole =  (TextView) findViewById(R.id.userRole);

        Button changebutton = (Button) findViewById(R.id.changebutton);
        /*changebutton.setOnClickListener(
                new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        final Dialog d = new Dialog(ManageRole.this);
                        d.setTitle("Hello User !");
                        //d.setContentView(android.R.layout.simple_list_item_1);
                        d.setCancelable(false);
                        //TextView t = (TextView) d.findViewById(R.id.textView1);
                        String[] values = {"Apple", "Blackberry", "Cherry", "Dragon Fruit", "Grape"};
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManageRole.this,
                                android.R.layout.simple_list_item_1, values);
                        setListAdapter(adapter);
                    }

                    @Override
                    protected void onListItemClick(ListView l, View v,
                                                   int position, long id) {
                        String item = (String) getListAdapter().getItem(position);
                        Toast.makeText(ManageRole.this, item + "selected", Toast.LENGTH_LONG).show();
                    }

                    d.show();

                }
        );*/


        Button assignButton = (Button) findViewById(R.id.assignButton);


}
}