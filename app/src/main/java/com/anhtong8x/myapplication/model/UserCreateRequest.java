package com.anhtong8x.myapplication.model;

import java.util.Date;

public class UserCreateRequest {
    String uFirstName;
    String uLastName;
    String uDob;
    String uEmail;
    String uPhoneNumber;
    String uUserName;
    String uPassword;
    String uConfirmPassword;

    public UserCreateRequest(String uFirstName, String uLastName, String uDob, String uEmail, String uPhoneNumber, String uUserName, String uPassword, String uConfirmPassword) {
        this.uFirstName = uFirstName;
        this.uLastName = uLastName;
        this.uDob = uDob;
        this.uEmail = uEmail;
        this.uPhoneNumber = uPhoneNumber;
        this.uUserName = uUserName;
        this.uPassword = uPassword;
        this.uConfirmPassword = uConfirmPassword;
    }

    public UserCreateRequest() {
    }

    public String getuFirstName() {
        return uFirstName;
    }

    public void setuFirstName(String uFirstName) {
        this.uFirstName = uFirstName;
    }

    public String getuLastName() {
        return uLastName;
    }

    public void setuLastName(String uLastName) {
        this.uLastName = uLastName;
    }

    public String getuDob() {
        return uDob;
    }

    public void setuDob(String uDob) {
        this.uDob = uDob;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPhoneNumber() {
        return uPhoneNumber;
    }

    public void setuPhoneNumber(String uPhoneNumber) {
        this.uPhoneNumber = uPhoneNumber;
    }

    public String getuUserName() {
        return uUserName;
    }

    public void setuUserName(String uUserName) {
        this.uUserName = uUserName;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuConfirmPassword() {
        return uConfirmPassword;
    }

    public void setuConfirmPassword(String uConfirmPassword) {
        this.uConfirmPassword = uConfirmPassword;
    }
}
