package com.android.ad.mycart.logicuty_clerk.Model;

import java.util.HashMap;

/**
 * Created by rajeev on 1/2/2017.
 */

public class DisbursementDetailsClass_Clerk extends HashMap<String,String>{


    private String DeptName;
    private String ItemName;
    private String Quantity;

    public DisbursementDetailsClass_Clerk(String DeptName, String ItemName, String Quantity) {
        this.DeptName= DeptName;
        this.ItemName = ItemName;
        this.Quantity = Quantity;

        put("DeptName",DeptName);
        put("ItemName",ItemName);
        put("Quantity",Quantity);
    }

    public String getDepartmentName() {
        return DeptName;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getQuantity() {
        return Quantity;
    }

}
