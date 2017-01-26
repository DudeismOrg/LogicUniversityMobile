package com.android.ad.mycart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by rajeev on 19/1/2017.
 */

public class CartItemAdapter extends BaseAdapter {
    private static final String TAG = "CartItemAdapter";

    private List<CartItem> cartItems = Collections.emptyList();

    private final Context context;

    public CartItemAdapter(Context context) {
        this.context = context;
    }

    public void updateCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public CartItem getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView tvName;
        TextView tvQuantity;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.adapter_cart_item, parent, false);
            tvName = (TextView) convertView.findViewById(R.id.tvCartItemName);

            tvQuantity = (TextView) convertView.findViewById(R.id.tvCartItemQuantity);

            convertView.setTag(new ViewHolder(tvName,tvQuantity));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            tvName = viewHolder.tvCartItemName;
            tvQuantity = viewHolder.tvCartItemQuantity;

        }

        final Cart cart = CartHelper.getCart();
        final CartItem cartItem = getItem(position);
        tvName.setText(cartItem.getProduct().getName());
        tvQuantity.setText(String.valueOf(cartItem.getQuantity()));
        return convertView;
    }

    private static class ViewHolder {
        public final TextView tvCartItemName;
        public final TextView tvCartItemQuantity;

        public ViewHolder(TextView tvCartItemName,TextView tvCartItemQuantity) {
            this.tvCartItemName = tvCartItemName;
            this.tvCartItemQuantity = tvCartItemQuantity;
        }
    }
}
