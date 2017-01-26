package com.android.ad.mycart.logicuty_clerk.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.ad.mycart.R;

import java.util.ArrayList;

/**
 * Created by rajeev on 25/1/2017.
 */

public class CartItemAdapter_clerk extends ArrayAdapter<Clerk_Item> {
    public CartItemAdapter_clerk(Context context, ArrayList<Clerk_Item> objects) {
        super(context, 0, objects);
    }

    private ArrayList<Clerk_Item> items;

    public ArrayList<Clerk_Item> getItems(){
        return  items;
    }

    public void setItems(ArrayList<Clerk_Item> items){
        this.items = items;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Clerk_Item item = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_cart_item, parent, false);
        }
        TextView itemName = (TextView) convertView.findViewById(R.id.tvCartItemName);
        TextView itemQty = (TextView)convertView.findViewById(R.id.tvCartItemQuantity);
        itemName.setText(item.getItem());
        itemQty.setText(item.getQuantity());
        return convertView;
    }
}
