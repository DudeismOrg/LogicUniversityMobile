package com.android.ad.mycart.logicuty_clerk.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.ad.mycart.R;

import java.util.List;

/**
 * Created by rajeev on 1/2/2017.
 */

public class OutstandingAdapter extends ArrayAdapter<OutstandingClass_Clerk> {


    public OutstandingAdapter(Context context, List<OutstandingClass_Clerk> outstandings) {
        super(context, 0, outstandings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_outstandinglist, parent, false);
        }


        OutstandingClass_Clerk currentoutstanding = getItem(position);


        TextView departmentView = (TextView) listItemView.findViewById(R.id.departmentnameoutstanding);
        departmentView.setText(currentoutstanding.getDepartmentName());

        TextView itemnameView = (TextView) listItemView.findViewById(R.id.itemcodeoutstanding);
        itemnameView.setText(currentoutstanding.getItemName());

        TextView quantityView = (TextView) listItemView.findViewById(R.id.quantityoutstanding);
        quantityView.setText(currentoutstanding.getQuantity());
        return listItemView;
    }


}
