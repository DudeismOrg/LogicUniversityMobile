package com.android.ad.mycart.logicuty_clerk.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

/**
 * Created by rajeev on 25/1/2017.
 */

public class ClerkCommon {
    public static void CreateSharedPreference(Context context, String key, String value, String dataType) {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences
                        (context);
        SharedPreferences.Editor editor = pref.edit();
        switch (dataType) {
            case "String":
                editor.putString(key, value);
                break;
            case "Boolean":
                editor.putBoolean(key, Boolean.valueOf(value));
                break;
            case "Integer":
                editor.putInt(key, Integer.parseInt(value));
                break;
        }
        editor.commit();
    }

    public static ArrayList<String> getSuppliers() {
        ArrayList<String> supplierList = new ArrayList<String>();
        supplierList.add("supplier1");
        supplierList.add("supplier2");
        supplierList.add("supplier3");
        return supplierList;
    }

    public static ArrayList<String> getCategoriesBySupplierId(String supplier) {
        ArrayList<String> categoryList = new ArrayList<String>();
        if (supplier.equals("supplier1"))
            categoryList.add("Category1");
        if (supplier.equals("supplier2"))
            categoryList.add("Category2");
        if (supplier.equals("supplier3"))
            categoryList.add("Category3");
        return categoryList;
    }

    public static ArrayList<String> getItemsByCategorySupplier(String supplier, String category) {
        ArrayList<String> itemList = new ArrayList<String>();
        if (category.equals("Category1"))
            itemList.add("Item1");
        if (category.equals("Category2"))
            itemList.add("Item2");
        if (category.equals("Category3"))
            itemList.add("Item3");
        return itemList;
    }


}
