package com.anhtong8x.myapplication.utility;

import android.app.Application;

public class GlobalVariableApp extends Application {
    private String mToken;

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }
}
