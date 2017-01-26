package com.android.ad.mycart.logicuty_clerk.Model;

import java.util.HashMap;

/**
 * Created by rajeev on 26/1/2017.
 */

public class RequisitionClass_Clerk extends HashMap<String,String> {

    public String reqId;
    public String dept;
    public String reqDate;
    public String defaultStr;

    public RequisitionClass_Clerk(String reqId, String dept, String reqDate,String defaultStr) {
        this.reqId = reqId;
        this.dept = dept;
        this.reqDate = reqDate;
        this.defaultStr =defaultStr;

    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }
}

