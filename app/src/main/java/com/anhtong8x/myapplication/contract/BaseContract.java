package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.UserCreateRequest;

public interface BaseContract {
    interface View<M, E>{
        void createSuccess(M msg);
        void createFailure(E msg);
    }
    interface Presenter<T, C, U, D, G, F>{
        void createHandle(C request, T token);
        void updateHandle(U request, T token);
        void deleteHandle(D request, T token);
        void getHandle(G request, T token);
        void getsHandle(F request, T token);
    }
}
