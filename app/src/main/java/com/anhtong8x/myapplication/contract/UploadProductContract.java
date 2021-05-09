package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.UploadProductRequest;

public interface UploadProductContract {
    interface View{
        void createSuccess(String msg);
        void createFailure(String msg);
    }
    interface Presenter{
        void uploadHandle(UploadProductRequest uploadProductRequest, String token);
    }
}
