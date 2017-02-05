package com.android.ad.mycart.logicuty_clerk.Model;

import java.util.List;

/**
 * Created by rajeev on 2/2/2017.
 */

public class Supplier {

    private String SupplierCode;
    private  int SupplierId;

    public String getSupplierCode() {
        return SupplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        SupplierCode = supplierCode;
    }

    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        SupplierId = supplierId;
    }

    public Supplier(String supplierCode, int supplierId) {

        SupplierCode = supplierCode;
        SupplierId = supplierId;
    }

    public List<SupplierItem> Items;
}
