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

public class RetrivalItemAdapter extends ArrayAdapter<RetrivalClass_Clerk> {


    public RetrivalItemAdapter(Context context, List<RetrivalClass_Clerk> retrivals) {
        super(context, 0, retrivals);
    }


    private static class ViewHolder {
        TextView depName;
        TextView itemName;
        TextView quantity;


    }

    public RetrivalItemAdapter(Context context, ArrayList<RetrivalClass_Clerk> items) {
        super(context, 0, items);
    }

    public static List<Integer> positions;


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RetrivalClass_Clerk item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
            viewHolder.depName = (TextView) convertView.findViewById(R.id.text1);
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.text2);
            viewHolder.quantity = (TextView) convertView.findViewById(R.id.text3);

            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.depName.setText(item.getDeptName());
        viewHolder.itemName.setText(item.getItemName());
        viewHolder.quantity.setText(item.getQuantity());

        return convertView;
    }
}
