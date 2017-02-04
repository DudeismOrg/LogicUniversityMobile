package com.android.ad.mycart.logicuty_clerk.Model;

/**
 * Created by rajeev on 2/2/2017.
 */

public class SupplierItem {

    private int itemId;
    private String ItemName;

    public SupplierItem(int itemId, String itemName) {
        this.itemId = itemId;
        ItemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }
}
