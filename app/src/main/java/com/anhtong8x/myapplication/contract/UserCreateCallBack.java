package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.UserResult;

public interface UserCreateCallBack {
    void onFetchSuccess(UserResult userResult);
    void onFetchFault(Exception e);
}
