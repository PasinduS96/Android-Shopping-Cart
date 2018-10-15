package com.example.pasindusenarathne.mobile_app;

/**
 * Created by Pasindu Senarathne on 9/23/2018.
 */

public class SellerUserContract {

    private String userName;
    private String password;
    private String email;
    public static boolean loginStatus;
    public static String userID;

    public static class UserEntry{

        public static final String TABLE_NAME = "user";
        public static final String USER_ID = "userID";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

}
