package com.anhtong8x.myapplication.model;

public class LoginRequest {
    String UserName, Password;
    Boolean RememberMe;

    public LoginRequest(String userName, String password, Boolean rememberMe) {
        UserName = userName;
        Password = password;
        RememberMe = rememberMe;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean getRememberMe() {
        return RememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        RememberMe = rememberMe;
    }
}
