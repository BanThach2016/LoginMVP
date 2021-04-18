package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.LoginRequest;

public interface LoginContract {
    interface View {
        void loginSuccess(String token);
        void loginFailure(String mError);
    }

    interface Presenter{
        void loginHandle(LoginRequest loginRequest);
    }
}
