package com.android.ad.mycart.logicuty_clerk.Model;

import java.io.Serializable;

/**
 * Created by rajeev on 25/1/2017.
 */

public class Clerk_User implements Serializable {

    public Clerk_User() {
        this.userID = 0;
        this.firstName = "vasanth";

    }

    private String userName;
    private String password;
    private int userID;
    private String firstName;
    private int deptId;


    public Clerk_User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean ValidateUser() {
        // Json request


        return userName.equals(password);
    }

    public Clerk_User GetUser() {
        return new Clerk_User();
    }

    public int getDeptId(){ return deptId;}

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userID=" + userID +
                ", firstName='" + firstName + '\'' +

                '}';
    }
}
