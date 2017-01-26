package com.android.ad.mycart.logicuty_clerk.Model;

import java.util.HashMap;

/**
 * Created by rajeev on 25/1/2017.
 */

public class Clerk_Item extends HashMap<String,String>{
    private String supplier;
    private String category;
    private String item;
    private String quantity;
    private String itemCode;
    private String description;
    private String reqId;
    private String defaultStr;

    @Override
    public String toString() {
        return "Item{" +
                "supplier='" + supplier + '\'' +
                ", category='" + category + '\'' +
                ", item='" + item + '\'' +
                ", quantity='" + quantity + '\'' +
                ", description='" + description + '\'' +
                ", itemCode='" + itemCode + '\'' +
                '}';
    }



    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Clerk_Item(String supplier, String category, String item, String quantity) {
        this.supplier = supplier;
        this.category = category;
        this.item = item;
        this.quantity = quantity;
    }

    public Clerk_Item(String reqId, String itemCode, String description, String quantity, String defaultStr ) {
        this.reqId = reqId;
        this.itemCode = itemCode;
        this.description = description;
        this.quantity = quantity;
        this.defaultStr = defaultStr;
    }

    public String getSupplier() {

        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getReqId() {
        return reqId;
    }
}


