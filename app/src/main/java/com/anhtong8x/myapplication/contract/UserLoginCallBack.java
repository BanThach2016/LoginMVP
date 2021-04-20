package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.UserResult;

public interface UserLoginCallBack {
    void onFetchSuccess(UserResult loginResult);
    void onFetchFault(Exception e);
}
