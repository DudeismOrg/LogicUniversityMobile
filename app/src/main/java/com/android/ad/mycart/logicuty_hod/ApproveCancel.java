package com.android.ad.mycart.logicuty_hod;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

public class ApproveCancel extends ListActivity {
    List<RequisitionClass_Clerk> requisitionHod;
    private void getData() {
        requisitionHod = new ArrayList<RequisitionClass_Clerk>();
        requisitionHod.add(new RequisitionClass_Clerk("departmentCode 1","requisitionId 1","approvedDate 1"));
        requisitionHod.add(new RequisitionClass_Clerk("departmentCode 2","requisitionId 2", "approvedDate 2"));
        requisitionHod.add(new RequisitionClass_Clerk("departmentCode 3","requisitionId 3", "approvedDate 3"));
        requisitionHod.add(new RequisitionClass_Clerk("departmentCode 4","requisitionId 4", "approvedDate 4"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListAdapter(new SimpleAdapter
                (this, requisitionHod, android.R.layout.simple_list_item_2,
                        new String[]{"requisitionId", "departmentCode"},
                        new int[]{ android.R.id.text1, android.R.id.text2}));

        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences
                        (getApplicationContext());
        int DepartmentID = pref.getInt("DepartmentID", 1);

        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        /*RequisitionClass_Clerk s = (RequisitionClass_Clerk) getListAdapter().getItem(position);
        Toast.makeText(getApplicationContext(), s.get("requisitionId") + " selected",
                Toast.LENGTH_LONG).show();*/

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(requisitionHod.get(info.position).getRequisitionId());
            String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

}