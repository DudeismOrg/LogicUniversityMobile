package com.android.ad.mycart;

/**
 * Created by rajeev on 24/1/2017.
 */

public class Books {

    private String nameOfProduct;
    private String descriptionOfProduct;


    public Books(String productName, String productDescription){
        this.nameOfProduct= productName;
        this.descriptionOfProduct= productDescription;
    }

    public String getNameOfProduct(){
        return nameOfProduct;
    }

    public String getDescriptionOfProduct(){
        return descriptionOfProduct;
    }
}

