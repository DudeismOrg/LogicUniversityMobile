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

public class DisbursementItemAdapter extends ArrayAdapter<DisbursementClass_Clerk> {

    public DisbursementItemAdapter(Context context, List<DisbursementClass_Clerk> disbursements) {
        super(context, 0, disbursements);
    }


    private static class ViewHolder {
        TextView reqNum;
        TextView disId;
        TextView date;
    }

    public DisbursementItemAdapter(Context context, ArrayList<DisbursementClass_Clerk> items) {
        super(context, 0, items);
    }

    public static List<Integer> positions;


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DisbursementClass_Clerk item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
            viewHolder.reqNum = (TextView) convertView.findViewById(R.id.text1);
            viewHolder.disId = (TextView) convertView.findViewById(R.id.text2);
            viewHolder.date = (TextView) convertView.findViewById(R.id.text3);

            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.reqNum.setText(item.getReqNum());
        viewHolder.disId.setText(item.getDisId());
        viewHolder.date.setText(item.getDate());

        return convertView;
    }
}
