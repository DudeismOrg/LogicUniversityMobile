package com.android.ad.mycart.logicuty_clerk.Model;

import android.util.Log;

import com.android.ad.mycart.JSONParser;
import com.android.ad.mycart.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tyler Durden on 2/5/2017.
 */

public class ApproveItem extends HashMap<String, String> {

    public ApproveItem(String ItemName, String Quantity){
        this.ItemName = ItemName;
        this.Quantity = Quantity;
        put("ItemName",ItemName);
        put("Quantity", Quantity);
    }

    private String ItemName;
    private String Quantity;

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        this.ItemName = ItemName;
    }


    public static ArrayList<ApproveItem> list(String url) {
        ArrayList<ApproveItem> list = new ArrayList<ApproveItem>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(url);
        try {
            for (int i =0; i<a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new ApproveItem(b.getString("ItemName"), b.getString("Quantity")));
            }
        } catch (Exception e) {
            Log.e("Employee.list()", "JSONArray error");
        }
        return(list);
    }

    public  static boolean AckRequisition(String Url, String AcknowledgedBy, String Remarks,String ReqId, String Status){
        JSONObject obj = new JSONObject();
        try {
            obj.put("AcknowledgedBy",AcknowledgedBy);
            obj.put("Remarks",Remarks);
            obj.put("ReqId",ReqId);
            obj.put("Status",Status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String val = "";
        try{
        val = JSONParser.postStream(Url, obj.toString());
            Log.i("result",val);
        }
        catch (Exception ex){
            Log.i("result",val);
            Log.i("exc", ex.getMessage());
        }
        return val == "true" ? true : false;
    }
}
