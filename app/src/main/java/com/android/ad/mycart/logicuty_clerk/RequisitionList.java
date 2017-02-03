package com.android.ad.mycart.logicuty_clerk;

import com.android.ad.mycart.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Durden on 2/3/2017.
 */

public class RequisitionList extends java.util.HashMap<String, String> {

    final static String host = "http://172.23.134.192/LogicUniversityStore/InventoryService/Service.svc/ApproveRequisition/";

    private String ReqId;
    private String ReqNum;
    private String RequestedBy;
    private String RequestedDate;

    public String getReqId() {
        return ReqId;
    }

    public void setReqId(String reqId) {
        ReqId = reqId;
    }

    public String getReqNum() {
        return ReqNum;
    }

    public void setReqNum(String reqNum) {
        ReqNum = reqNum;
    }

    public String getRequestedBy() {
        return RequestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        RequestedBy = requestedBy;
    }

    public String getRequestedDate() {
        return RequestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        RequestedDate = requestedDate;
    }

    public RequisitionList(String reqId, String reqNum, String requestedBy, String requestedDate){
        this.ReqId = reqId;
        this.ReqNum = reqNum;
        this.RequestedBy =requestedBy;
        this.RequestedDate=requestedDate;

        put("RequisitionId",reqId);
        put("RequisitionNum",reqNum);
        put("CreatedBy",requestedBy);
        put("RequisitionDate",requestedDate);
    }

    public static List<RequisitionList> getRequisitionsList(String deptId) {
        List<RequisitionList> list = new ArrayList<RequisitionList>();
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host + deptId);
            for (int i = 0; i < a.length(); i++) {
                JSONObject obj = a.getJSONObject(i);
                RequisitionList objReq = new RequisitionList(obj.getString("RequisitionId"),obj.getString("RequisitionNum"),obj.getString("CreatedBy"),obj.getString("RequisitionDate"));
                list.add(objReq);
            }
        } catch (Exception e) {
        }
        return list;
    }

    /*public static Employee getEmployee(String id) {
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

    public static void updateEmployeeRole(String RoleCode) {
        JSONObject jemployee = new JSONObject();
        try {
            jemployee.put("RoleCode", RoleCode);
        } catch (Exception e) {
        }
        String result = JSONParser.postStream(host + "/Update", jemployee.toString());
    }*/

}