package com.anhtong8x.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class UserResult {
    @SerializedName("isSuccessed")
    Boolean isSuccessed;
    @SerializedName("message")
    String message;
    @SerializedName("resultObj")
    String resultObj;

    public UserResult(Boolean isSuccessed, String message, String resultObj) {
        this.isSuccessed = isSuccessed;
        this.message = message;
        this.resultObj = resultObj;
    }

    public Boolean getSuccessed() {
        return isSuccessed;
    }

    public void setSuccessed(Boolean successed) {
        isSuccessed = successed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultObj() {
        return resultObj;
    }

    public void setResultObj(String resultObj) {
        this.resultObj = resultObj;
    }
}
