package com.example.pasindusenarathne.mobile_app;

/**
 * Created by Pasindu Senarathne on 9/21/2018.
 */

public class ItemsModel {

    public String unitCode;
    public String itemname;
    public String discription;
    public String price;
    public String available;
    public String seller;

    public static abstract class ItemEntry{

        public static final String T1COL1 = "unitCode";
        public static final String T1COL2 = "name";
        public static final String T1COL3 = "seller";
        public static final String T1COL4 = "discription";
        public static final String T1COL5 = "price";
        public static final String T1COL6 = "availability";


    }

    public String getUnitCode() {
        return unitCode;
    }

    public String getItemname() {
        return itemname;
    }

    public String getDiscription() {
        return discription;
    }

    public String getPrice() {
        return price;
    }

    public String getAvailable() {
        return available;
    }

    public String getSeller() {
        return seller;

    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
