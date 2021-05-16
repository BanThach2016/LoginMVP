package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.ProductUploadRequest;

public interface UploadProductContract {
    interface View{
        void createSuccess(String msg);
        void createFailure(String msg);
    }
    interface Presenter{
        void uploadHandle(ProductUploadRequest uploadProductRequest, String token);
    }
}
