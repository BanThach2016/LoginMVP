package com.anhtong8x.myapplication.apihelper;

import com.anhtong8x.myapplication.contract.ProductPagingCallBack;
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

    public ProductsService(ProductPagingRequest mProductPagingRequest, String token) {
        this.mProductPagingRequest = mProductPagingRequest;
        this.mIProductsService = ApiService.getClientAuthorization(token).create(IProductsService.class);
    }

    public ProductsService( UploadProductRequest mUploadProductRequest, String token) {
        this.mIProductsService = ApiService.getClient().create(IProductsService.class);
        this.mUploadProductRequest = mUploadProductRequest;
    }

    public void getPaging(final ProductPagingCallBack dataCallBack){
        /*Call<ProductResult> res = mIProductsService.getPaging(mProductPagingRequest.getCategoryId(),
                mProductPagingRequest.getPageIndex(), mProductPagingRequest.getPageSize());


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
        });*/
    }

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
