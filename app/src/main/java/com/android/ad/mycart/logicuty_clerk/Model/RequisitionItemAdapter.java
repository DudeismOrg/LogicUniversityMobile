package com.android.ad.mycart.logicuty_clerk.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.ad.mycart.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeev on 26/1/2017.
 */

public class RequisitionItemAdapter extends ArrayAdapter<RequisitionClass_Clerk> {

   /* private static class ViewHolder {
        TextView reqId;
        TextView dept;
        TextView date;
        CheckBox checkBox;

    }

    public RequisitionItemAdapter(Context context, ArrayList<RequisitionClass_Clerk> items) {
        super(context, 0, items);
    }

    public static List<Integer> positions;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RequisitionClass_Clerk item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
            viewHolder.reqId = (TextView) convertView.findViewById(R.id.text1);
            viewHolder.dept = (TextView) convertView.findViewById(R.id.text2);
            viewHolder.date = (TextView) convertView.findViewById(R.id.text3);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.check);


            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.reqId.setText(item.getReqId());
        viewHolder.dept.setText(item.getDept());
        viewHolder.date.setText(item.getReqDate());
        viewHolder.checkBox.setTag(position);
        // viewHolder.date.setText(item.get);



        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                CheckBox cb = (CheckBox) view;
                if (positions == null)
                    positions = new ArrayList<Integer>();
                if (cb.isChecked()) {
                    positions.add((Integer) view.getTag());
                    //Log.i("positions2", String.valueOf(cb.getTag()));
                } else {
                    positions.remove(cb.getTag());
                }
                for (Integer val : positions) {
                    Log.i("positions", String.valueOf(val));
                }
                // int position = ;
            }
        });

        //viewHolder.checkBox.setOnCheckedChangeListener({});
        return convertView;
    }


} */

    public RequisitionItemAdapter(Context context, List<RequisitionClass_Clerk> requisitions) {
        super(context, 0, requisitions);
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_requisition, parent, false);
        }


        RequisitionClass_Clerk currentrequisition = getItem(position);


        TextView approvedByView = (TextView) listItemView.findViewById(R.id.requisitionid);
       approvedByView.setText(currentrequisition.getRequisitionId());

        TextView departmentCodeView =(TextView) listItemView.findViewById(R.id.departmentcode);
        departmentCodeView.setText(currentrequisition.getDepartmentCode());

      TextView approvedDateView = (TextView) listItemView.findViewById(R.id.approveddate);
       approvedDateView.setText(currentrequisition.getApprovedDate());



        return listItemView;
    }*/

    //New Change made
    private static class ViewHolder {
        TextView reqId;
        TextView dept;
        TextView date;
        CheckBox checkBox;

    }

    public RequisitionItemAdapter(Context context, ArrayList<RequisitionClass_Clerk> items) {
        super(context, 0, items);
    }

    public static List<Integer> positions;


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RequisitionClass_Clerk item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
            viewHolder.reqId = (TextView) convertView.findViewById(R.id.text1);
            viewHolder.dept = (TextView) convertView.findViewById(R.id.text2);
            viewHolder.date = (TextView) convertView.findViewById(R.id.text3);
          //  viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.check);


            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.reqId.setText(item.getDepartmentCode());
        viewHolder.dept.setText(item.getRequisitionId());
        viewHolder.date.setText(item.getApprovedDate());
//        viewHolder.checkBox.setTag(position);
        // viewHolder.date.setText(item.get);



/*        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                CheckBox cb = (CheckBox) view;
                if (positions == null)
                    positions = new ArrayList<Integer>();
                if (cb.isChecked()) {
                    positions.add((Integer) view.getTag());
                    //Log.i("positions2", String.valueOf(cb.getTag()));
                } else {
                    positions.remove(cb.getTag());
                }
                for (Integer val : positions) {
                    Log.i("positions", String.valueOf(val));
                }
                // int position = ;
            }
        }); */

        //viewHolder.checkBox.setOnCheckedChangeListener({});
        return convertView;
    }
}
