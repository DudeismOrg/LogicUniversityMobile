package com.android.ad.mycart.logicuty_clerk.Model;

import java.util.HashMap;

/**
 * Created by rajeev on 26/1/2017.
 */

public class DisbursementClass_Clerk extends HashMap<String,String> {

    private String reqId;
    private String reqNum;
    private String disId;
    private String date;


    public DisbursementClass_Clerk(String reqId, String reqNum, String disId, String date) {
        this.reqId= reqId;
        this.reqNum= reqNum;
        this.disId = disId;
        this.date = date;
    }

    public String getReqId() {
        return reqId;
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

