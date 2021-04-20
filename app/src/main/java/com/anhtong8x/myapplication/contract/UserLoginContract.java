package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.UserLoginRequest;

public interface UserLoginContract {
    interface View {
        void loginSuccess(String token);
        void loginFailure(String mError);
    }

    interface Presenter{
        void loginHandle(UserLoginRequest userLoginRequest);
    }
}
