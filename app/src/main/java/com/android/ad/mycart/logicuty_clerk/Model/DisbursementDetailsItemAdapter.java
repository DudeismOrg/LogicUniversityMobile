package com.android.ad.mycart.logicuty_clerk.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.ad.mycart.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeev on 26/1/2017.
 */

public class DisbursementDetailsItemAdapter extends ArrayAdapter<DisbursementDetailsClass_Clerk> {

    public DisbursementDetailsItemAdapter(Context context, List<DisbursementDetailsClass_Clerk> disbursements) {
        super(context, 0, disbursements);
    }


    private static class ViewHolder {
        TextView ItemName;
        TextView Quantity;
    }

    public DisbursementDetailsItemAdapter(Context context, ArrayList<DisbursementDetailsClass_Clerk> items) {
        super(context, 0, items);
    }

    public static List<Integer> positions;


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DisbursementDetailsClass_Clerk item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.disbursementdetails_clerk, parent, false);
            viewHolder.ItemName = (TextView) convertView.findViewById(R.id.itemName_Disbursement);
            viewHolder.Quantity = (TextView) convertView.findViewById(R.id.quantityOrdered_disbursement);
         //viewHolder.date = (TextView) convertView.findViewById(R.id.text3);

            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ItemName.setText(item.getItemName());
        viewHolder.Quantity.setText(item.getQuantity());
     //   viewHolder.date.setText(item.getDate());

        return convertView;
    }
}
