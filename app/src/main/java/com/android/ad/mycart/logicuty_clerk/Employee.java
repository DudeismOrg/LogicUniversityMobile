package com.android.ad.mycart.logicuty_clerk;

import com.android.ad.mycart.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Durden on 1/26/2017.
 */

public class Employee extends java.util.HashMap<String,String> {

    final static String host = "http://192.168.20.172:8090/Customer/Service.svc";

    public Employee(int UserID, String FirstName, String Email, String LastName, int DepartmentID, String DepartmentName, int RoleID, String RoleName)
    {
        put("UserID",String.valueOf(UserID));
        put("FirstName", FirstName);
        put("Email", Email);
        put("LastName", LastName);
        put("DepartmentID", String.valueOf(DepartmentID));
        put("DepartmentName", DepartmentName);
        put("RoleID", String.valueOf(RoleID));
        put("RoleName", RoleName);
    }

    public Employee(){}

    public static List<String> listCustomer() {
        List<String> list = new ArrayList<String>();
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host+"/Customer");
            for (int i=0; i<a.length(); i++) {
                String c = a.getString(i);
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static Employee getCustomer(String id) {
        Employee emp = null;
        try {
            JSONObject c = JSONParser.getJSONFromUrl(host+"/Customer/"+id);
            emp = new Employee(Integer.parseInt(c.getString("UserID")),c.getString("FirstName"),c.getString("Email"),c.getString("LastName"),
                    Integer.parseInt(c.getString("DepartmentID")),c.getString("DepartmentName"),Integer.parseInt(c.getString("RoleID")),c.getString("RoleName"));
        } catch (Exception e) {
        }
        return emp;
    }
}