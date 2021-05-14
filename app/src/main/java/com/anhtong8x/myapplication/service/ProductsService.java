package com.anhtong8x.myapplication.service;

import com.anhtong8x.myapplication.apihelper.ApiService;
import com.anhtong8x.myapplication.contract.BaseCallBack;
import com.anhtong8x.myapplication.contract.UploadProductCallBack;
import com.anhtong8x.myapplication.model.ProductPagingRequest;
import com.anhtong8x.myapplication.model.ProductResult;
import com.anhtong8x.myapplication.model.UploadProductRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsService {
    IProductsService mIProductsService;

    ProductPagingRequest mProductPagingRequest;
    UploadProductRequest mUploadProductRequest;

    // contractors
    // contractor getPaging
    public ProductsService(ProductPagingRequest mProductPagingRequest) {
        this.mProductPagingRequest = mProductPagingRequest;
        this.mIProductsService = ApiService.getClient().create(IProductsService.class);
    }

    // contractor upload file
    public ProductsService( UploadProductRequest mUploadProductRequest) {
        this.mIProductsService = ApiService.getClient().create(IProductsService.class);
        this.mUploadProductRequest = mUploadProductRequest;
    }

    // gets product
    public void getsPaging(String token, final BaseCallBack<ProductResult, Exception> dataCallBack){
        Call<ProductResult> res = mIProductsService.getPaging(
                mProductPagingRequest.getLanguageId(),
                mProductPagingRequest.getCategoryId(),
                mProductPagingRequest.getPageIndex(),
                mProductPagingRequest.getPageSize(),
                "Bearer " + token);
        res.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {
                try {
                    dataCallBack.onFetchSuccess(response.body());
                } catch (Exception e) {
                    dataCallBack.onFetchFault(e);
                }
            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {
                dataCallBack.onFetchFault(new Exception(t));
            }
        });
    }

    // upload image product
    public void uploadFile(String token, final UploadProductCallBack dataCallBack){
        Call<ResponseBody> res = mIProductsService.uploadFile(
                mUploadProductRequest.getProductId(),
                mUploadProductRequest.getmCaption(),
                mUploadProductRequest.getmIsDefault(),
                mUploadProductRequest.getmSortOrder(),
                mUploadProductRequest.getRequestFile(),
                "Bearer " + token
                );
        res.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    dataCallBack.onFetchSuccess(response);
                } catch (Exception e) {
                    dataCallBack.onFetchFault(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dataCallBack.onFetchFault(new Exception(t));
            }
        });

    }
}
