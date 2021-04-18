package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.LoginResult;

public interface LoginResultCallBack {
    void onFetchSuccess(LoginResult loginResult);
    void onFetchFault(Exception e);
}
