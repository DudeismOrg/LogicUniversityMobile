package com.android.ad.mycart.logicuty_clerk;

import com.android.ad.mycart.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Durden on 1/26/2017.
 */

public class Employee extends java.util.HashMap<String, String> {

    final static String host = "http://172.23.200.42/LogicUniversityStore/InventoryService/Service.svc/operations/ValidateUser";
    final static String ManageRoleUrl = "http://172.23.200.42/LogicUniversityStore/InventoryService/Service.svc/ManageRole";

    private int UserID;
    private String FirstName;
    private String Email;
    private String LastName;
    private int DepartmentID;
    private String DepartmentName;
    private int RoleID;
    private String RoleName;
    private String RoleCode;


    public Employee(int UserID, String FirstName, String Email, String LastName, int DepartmentID, String DepartmentName, int RoleID, String RoleName, String RoleCode) {
        put("UserID", String.valueOf(UserID));
        put("FirstName", FirstName);
        put("Email", Email);
        put("LastName", LastName);
        put("DepartmentID", String.valueOf(DepartmentID));
        put("DepartmentName", DepartmentName);
        put("RoleID", String.valueOf(RoleID));
        put("RoleName", RoleName);
        put("RoleCode", RoleCode);

        this.UserID = UserID;
        this.FirstName = FirstName;
        this.Email = Email;
        this.LastName = LastName;
        this.DepartmentID = DepartmentID;
        this.DepartmentName = DepartmentName;
        this.RoleID = RoleID;
        this.RoleName = RoleName;
        this.RoleCode = RoleCode;
    }

    public Employee() {
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int departmentID) {
        DepartmentID = departmentID;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getRoleCode() {
        return RoleCode;
    }

    public void setRoleCode(String RoleCode) {
        RoleCode = RoleCode;
    }


    public static List<String> listCustomer() {
        List<String> list = new ArrayList<String>();
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host + "/Employee");
            for (int i = 0; i < a.length(); i++) {
                String c = a.getString(i);
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static Employee getEmployee(String id) {
        Employee emp = null;
        try {
            JSONObject c = JSONParser.getJSONFromUrl(host + "/Employee/" + id);
            emp = new Employee(Integer.parseInt(c.getString("UserID")), c.getString("FirstName"), c.getString("Email"), c.getString("LastName"),
                    Integer.parseInt(c.getString("DepartmentID")), c.getString("DepartmentName"), Integer.parseInt(c.getString("RoleID")), c.getString("RoleName"), c.getString("RoleCode"));
        } catch (Exception e) {
        }
        return emp;
    }

    public static void updateEmployee(Employee emp) {
        JSONObject jemployee = new JSONObject();
        try {
            jemployee.put("UserID", Integer.parseInt(emp.get("UserID")));
            jemployee.put("FirstName", emp.get("FirstName"));
            jemployee.put("Email", emp.get("Email"));
            jemployee.put("LastName", emp.get("LastName"));
            jemployee.put("DepartmentID", Integer.parseInt(emp.get("DepartmentID")));
            jemployee.put("DepartmentName", emp.get("DepartmentName"));
            jemployee.put("RoleID", Integer.parseInt(emp.get("RoleID")));
            jemployee.put("RoleName", emp.get("RoleName"));
        } catch (Exception e) {
        }
        String result = JSONParser.postStream(host + "/Update", jemployee.toString());
    }

    public static void updateEmployeeRole(String RoleCode, int changeUserId) {
        JSONObject jemployee = new JSONObject();
        try {
            jemployee.put("RoleCode", RoleCode);
            jemployee.put("UserId", changeUserId);
            String result = JSONParser.postStream(ManageRoleUrl, jemployee.toString());
        } catch (Exception e) {
        }
    }

}