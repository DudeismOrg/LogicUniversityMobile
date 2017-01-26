package com.android.ad.mycart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rajeev on 24/1/2017.
 */

public class BooksAdapter extends ArrayAdapter<Books> {

    public BooksAdapter(Context context, ArrayList<Books> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Books currentBook = getItem(position);

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.description_text_view);
        defaultTextView.setText(currentBook.getDescriptionOfProduct());


        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.productname_text_view);
        miwokTextView.setText(currentBook.getNameOfProduct());
        return listItemView;
    }
}
