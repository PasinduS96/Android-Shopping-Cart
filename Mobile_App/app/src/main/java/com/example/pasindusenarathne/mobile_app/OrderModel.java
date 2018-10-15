package com.example.pasindusenarathne.mobile_app;

/**
 * Created by Pasindu Senarathne on 9/24/2018.
 */

public class OrderModel {

    public String userID;
    private String unitCode;
    private String customer;
    private String quantity;
    private String date;
    private String seller;
    private String total;

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public String getUserID() {
        return userID;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public String getCustomer() {
        return customer;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public String getSeller() {
        return seller;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
