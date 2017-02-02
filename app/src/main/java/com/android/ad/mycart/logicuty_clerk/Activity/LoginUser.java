package com.android.ad.mycart.logicuty_clerk.Activity;

import android.util.Log;

import com.android.ad.mycart.JSONParser;
import com.android.ad.mycart.logicuty_clerk.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Durden on 1/31/2017.
 */

public class LoginUser extends java.util.HashMap<String,String> {

    final static String host = "http://172.23.134.192/InventoryService/Service.svc";

    public LoginUser(String UserName, String Password) {
        put("UserName", UserName);
        put("Password", Password);
    }

    public LoginUser(){}

    public static List<String> listUsers() {
        List<String> list = new ArrayList<String>();
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host+"/ValidateUser");
            for (int i=0; i<a.length(); i++) {
                String c = a.getString(i);
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static LoginUser getUsers(String uname) {
        LoginUser usr = null;
        try {
            JSONObject u = JSONParser.getJSONFromUrl(host+"/Validate/"+uname);
            usr = new LoginUser(u.getString("UserName"),
                    u.getString("Password"));
        } catch (Exception e) {
        }
        return usr;
    }

    public static Employee ValidateUser(LoginUser cus) {
        JSONObject jcustomer = new JSONObject();
        try {
            jcustomer.put("UserName", cus.get("UserName"));
            jcustomer.put("Password", cus.get("Password"));
        } catch (Exception e) {
        }
        String result = JSONParser.postStream(host+"/Validate", jcustomer.toString());
        Employee emp = null;
        try {
            JSONObject obj = new JSONObject(result);

            if(obj != null) {
                emp = new Employee(obj.getInt("UserID"), obj.getString("FirstName"), obj.getString("Email"), obj.getString("LastName"), obj.getInt("DepartmentID"),
                        obj.getString("DepartmentName"), obj.getInt("RoleID"), obj.getString("RoleName"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("debug",result);
        Log.i("Employee",String.valueOf(emp));
        return emp;
    }

}