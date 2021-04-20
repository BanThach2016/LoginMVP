package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.UserCreateRequest;

public interface UserCreateContract {
    interface View{
        void createSuccess(String msg);
        void createFailure(String msg);
    }
    interface Presenter{
        void createHandle(UserCreateRequest userCreateRequest, String token);
    }
}
