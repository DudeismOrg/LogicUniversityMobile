package com.android.ad.mycart.logicuty_clerk.Model;

/**
 * Created by rajeev on 1/2/2017.
 */

public class OutstandingClass_Clerk {


    private String departmentName;
    private String itemName;
    private String quantity;

    // private String mUrl;


    public OutstandingClass_Clerk(String depttName, String item, String quantity) {
        this.departmentName= depttName;
        this.itemName = item;
        this.quantity = quantity;

    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getQuantity() {
        return quantity;
    }
}
