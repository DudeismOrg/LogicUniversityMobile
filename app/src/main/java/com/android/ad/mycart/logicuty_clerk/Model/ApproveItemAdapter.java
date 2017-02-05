package com.android.ad.mycart.logicuty_clerk.Model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.ad.mycart.R;
import com.android.ad.mycart.logicuty_clerk.Model.ApproveItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rajeev on 19/1/2017.
 */

public class ApproveItemAdapter extends ArrayAdapter<ApproveItem> {


    public ApproveItemAdapter(Context context, ArrayList<ApproveItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApproveItem item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_cart_item, parent, false);
        }
        // Lookup view for data population
        TextView tvCartItemName = (TextView) convertView.findViewById(R.id.tvCartItemName);
        TextView tvCartItemQuantity = (TextView) convertView.findViewById(R.id.tvCartItemQuantity);
        // Populate the data into the template view using the data object
        tvCartItemName.setText(item.getItemName());
        tvCartItemQuantity.setText(item.getQuantity());
        // Return the completed view to render on screen
        return convertView;
    }
}
