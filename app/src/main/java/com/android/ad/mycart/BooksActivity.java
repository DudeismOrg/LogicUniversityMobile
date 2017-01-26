package com.android.ad.mycart;

import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by rajeev on 24/1/2017.
 */

public class BooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        ArrayList<Books> books = new ArrayList<Books>();
        books.add(new Books("One", "dudeism"));
        books.add(new Books("Two", "adijada1"));
        books.add(new Books("Three", "adijada"));
        books.add(new Books("Four", "pinalla"));
        books.add(new Books("Five", "adijada2"));
        books.add(new Books("Six", "adijada3"));
        books.add(new Books("Seven", "adijada4"));
        books.add(new Books("Eight", "adijada5"));
        books.add(new Books("Nine", "adijada6"));
        books.add(new Books("Ten", "adijada7"));

        BooksAdapter adapter = new BooksAdapter(this, books);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);}

    public void openOrderBook(View view) {
        Intent i = new Intent(BooksActivity.this, OrderBooksActivity.class);
        startActivity(i);
    }

}