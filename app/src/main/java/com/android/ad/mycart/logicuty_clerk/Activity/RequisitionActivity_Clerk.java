package com.android.ad.mycart.logicuty_clerk.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.Clerk_Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rajeev on 26/1/2017.
 */

public class RequisitionActivity_Clerk extends Activity {

    private List<Clerk_Item> itemList=new ArrayList<>();
    private void getData(){
        itemList.add(new Clerk_Item("reqId1","itemCode1","description1","1"));
        itemList.add(new Clerk_Item("reqId2","itemCode2","description2","2"));
        itemList.add(new Clerk_Item("reqId3","itemCode3","description3","3"));
        itemList.add(new Clerk_Item("reqId4","itemCode4","description4","4"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisitionclerk);
        getData();
        Bundle b = getIntent().getExtras();
        HashMap<String,String> item = (HashMap<String,String>) b.get("value");


        TableLayout table = (TableLayout) findViewById(R.id.tableId);
        TableRow row = (TableRow) findViewById(R.id.reqRowId);
        TextView tv = (TextView)findViewById(R.id.reqID);
        tv.setText(item.get("reqId"));

        TableRow tableText = (TableRow) findViewById(R.id.gridviewTable);

        GridView tgrid = (GridView)findViewById(R.id.gridview);

        tgrid.setAdapter(new SimpleAdapter
                (this, itemList, R.layout.row, new String[]{"itemCode", "description", "quantity"},
                        new int[]{ R.id.text1, R.id.text2, R.id.text3}));

    }


}

