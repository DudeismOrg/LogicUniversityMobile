package com.android.ad.mycart.logicuty_clerk.Model;

import java.util.HashMap;

/**
 * Created by rajeev on 26/1/2017.
 */

public class DisbursementClass_Clerk extends HashMap<String,String> {


    private String reqNum;
    private String disId;
    private String date;

    public DisbursementClass_Clerk(String depttCode, String requisitionNum, String approveddate) {
        this.reqNum= depttCode;
        this.disId = requisitionNum;
        this.date = approveddate;


    }

    public String getDate() {
        return date;
    }

    public String getDisId() {
        return disId;
    }

    public String getReqNum() {
        return reqNum;
    }
}

