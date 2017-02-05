package com.android.ad.mycart.logicuty_hod;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.RequisitionClass_Clerk;
import com.android.ad.mycart.logicuty_clerk.RequisitionList;

import java.util.List;

public class ApproveCancel extends ListActivity {
    int DepartmentID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences
                        (getApplicationContext());
        DepartmentID = pref.getInt("DepartmentID", 1);
        getData();
        if(lstRequisitions.size() == 0)
        {
            Toast.makeText(ApproveCancel.this, "No pending requisitions!!!",
                    Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            setListAdapter(new SimpleAdapter
                    (this, lstRequisitions, android.R.layout.simple_list_item_2,
                            new String[]{"RequisitionNum", "RequisitionDate"},
                            new int[]{android.R.id.text1, android.R.id.text2}));


            registerForContextMenu(getListView());
        }
    }

    List<RequisitionList> lstRequisitions;

    private void getData() {

        lstRequisitions = RequisitionList.getRequisitionsList(String.valueOf(DepartmentID));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        /*RequisitionClass_Clerk s = (RequisitionClass_Clerk) getListAdapter().getItem(position);
        */

        Intent intent = new Intent(this, ApproveCancelDetails.class);
        intent.putExtra("RequisitionId",lstRequisitions.get(position).getReqId());
        Toast.makeText(getApplicationContext(), lstRequisitions.get(position).getReqNum() + " selected",
                Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(lstRequisitions.get(info.position).getReqNum());
            String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i < menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

}