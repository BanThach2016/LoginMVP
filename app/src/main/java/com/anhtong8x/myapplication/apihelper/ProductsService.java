package com.anhtong8x.myapplication.apihelper;

import com.anhtong8x.myapplication.contract.ProductPagingCallBack;
import com.anhtong8x.myapplication.model.ProductPagingRequest;
import com.anhtong8x.myapplication.model.ProductResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsService {
    IProductsService mIProductsService;
    ProductPagingRequest mProductPagingRequest;

    public ProductsService(ProductPagingRequest mProductPagingRequest, String token) {
        this.mProductPagingRequest = mProductPagingRequest;
        this.mIProductsService = ApiService.getClientAuthorization(token).create(IProductsService.class);
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
}
