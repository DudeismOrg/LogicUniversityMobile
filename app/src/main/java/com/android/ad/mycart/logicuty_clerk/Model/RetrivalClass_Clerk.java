package com.android.ad.mycart.logicuty_clerk.Model;

import java.util.HashMap;

/**
 * Created by rajeev on 26/1/2017.
 */

public class RetrivalClass_Clerk extends HashMap<String,String> {

    private String deptName;
    private String itemName;
    private String quantity;

    public RetrivalClass_Clerk(String depttCode, String requisitionNum, String approveddate) {
        this.deptName= depttCode;
        this.itemName = requisitionNum;
        this.quantity = approveddate;


    }

    public String getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDeptName() {
        return deptName;
    }
}

