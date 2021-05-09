package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.UserCreateRequest;

public interface BaseContract {
    interface View<M, E>{
        void createSuccess(M msg);
        void createFailure(E msg);
    }
    interface Presenter<R, T>{
        void createHandle(R request, T token);
    }
}
