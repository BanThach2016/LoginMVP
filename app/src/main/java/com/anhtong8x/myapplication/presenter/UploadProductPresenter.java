package com.anhtong8x.myapplication.presenter;

import com.anhtong8x.myapplication.apihelper.ProductsService;
import com.anhtong8x.myapplication.contract.UploadProductCallBack;
import com.anhtong8x.myapplication.contract.UploadProductContract;
import com.anhtong8x.myapplication.model.UploadProductRequest;
import com.anhtong8x.myapplication.model.UserCreateRequest;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class UploadProductPresenter implements UploadProductContract.Presenter {
    private String TAG = UploadProductPresenter.class.getSimpleName();

    private UploadProductContract.View mView;

    private ProductsService mProductsService;

    public void setmView(UploadProductContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void uploadHandle(UploadProductRequest uploadProductRequest, String token) {
        mProductsService = new ProductsService(uploadProductRequest, token);
        mProductsService.uploadFile(token, new UploadProductCallBack() {
            @Override
            public void onFetchSuccess(Response<ResponseBody> response) {
                mView.createSuccess("Upload response: " + response.message() + response.code());
            }

            @Override
            public void onFetchFault(Exception e) {
                mView.createFailure("Upload error: " + e.toString());
            }
        });

    }
}
