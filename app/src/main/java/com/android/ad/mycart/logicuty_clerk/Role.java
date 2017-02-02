package com.android.ad.mycart.logicuty_clerk;

import com.android.ad.mycart.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Durden on 1/26/2017.
 */


public class Role extends java.util.HashMap<String,String> {

    final static String host = "http://172.23.134.192/InventoryService/Service.svc/Users";

    public Role(String id, String code, String name) {
        put("RoleID", id);
        put("RoleCode", code);
        put("RoleName", name);
    }

    public Role(){}

    public static List<String> listRole() {
        List<String> list = new ArrayList<String>();
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host);
            for (int i=0; i<a.length(); i++) {
                String c = a.getString(i);
                list.add(c);
                }
        } catch (Exception e) {
        }
        return list;
    }

    public static Role getRole(String id) {
        Role role = null;
        try {
            JSONObject c = JSONParser.getJSONFromUrl(host);
            role = new Role(c.getString("RoleID"),
                    c.getString("RoleCode"),
                    c.getString("RoleName"));
        } catch (Exception e) {
        }
        return role;
    }


    public static List<Employee> listEmployeeByDeptId(int deptId) {

        List<Employee> list = new ArrayList<Employee>();
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host + "/Users/" + deptId);

            for (int i=0; i < a.length(); i++) {
                JSONObject obj = a.getJSONObject(i);
                list.add(new Employee(obj.getInt("UserID"),
                        obj.getString("FirstName"),
                        obj.getString("Email"),
                        obj.getString("LastName"),
                        obj.getInt("DepartmentID"),
                        obj.getString("DepartmentName"),
                        obj.getInt("RoleID"),
                        obj.getString("RoleName")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}