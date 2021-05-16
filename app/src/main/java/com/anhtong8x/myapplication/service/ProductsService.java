package com.anhtong8x.myapplication.service;

import com.anhtong8x.myapplication.apihelper.ApiService;
import com.anhtong8x.myapplication.contract.BaseCallBack;
import com.anhtong8x.myapplication.contract.UploadProductCallBack;
import com.anhtong8x.myapplication.model.ProductDownloadImageRequest;
import com.anhtong8x.myapplication.model.ProductImageResult;
import com.anhtong8x.myapplication.model.ProductPagingRequest;
import com.anhtong8x.myapplication.model.ProductResult;
import com.anhtong8x.myapplication.model.ProductUploadRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsService {
    IProductsService mIProductsService;

    ProductPagingRequest mProductPagingRequest;
    ProductUploadRequest mProductUploadRequest;
    ProductDownloadImageRequest mProductDownloadImageRequest;

    // contractors
    // contractor getPaging
    public ProductsService(ProductPagingRequest productPagingRequest) {
        this.mProductPagingRequest = productPagingRequest;
        this.mIProductsService = ApiService.getClient().create(IProductsService.class);
    }

    // contractor upload file
    public ProductsService( ProductUploadRequest uploadProductRequest) {
        this.mIProductsService = ApiService.getClient().create(IProductsService.class);
        this.mProductUploadRequest = uploadProductRequest;
    }

    // contractor get product image
    public ProductsService( ProductDownloadImageRequest productDownloadImageRequest) {
        this.mIProductsService = ApiService.getClient().create(IProductsService.class);
        this.mProductDownloadImageRequest = productDownloadImageRequest;
    }

    public ProductsService( ) {
        this.mIProductsService = ApiService.getClient().create(IProductsService.class);
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
                mProductUploadRequest.getProductId(),
                mProductUploadRequest.getmCaption(),
                mProductUploadRequest.getmIsDefault(),
                mProductUploadRequest.getmSortOrder(),
                mProductUploadRequest.getRequestFile(),
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

    // get product image
    public void getProductImage(String token, final  BaseCallBack<ProductImageResult, Exception> dataCallBack){
        Call<ProductImageResult> res = mIProductsService.getImageProduct(
                mProductDownloadImageRequest.getProductId(),
                mProductDownloadImageRequest.getImageId(),
                "Bearer " + token);
        res.enqueue(new Callback<ProductImageResult>() {
            @Override
            public void onResponse(Call<ProductImageResult> call, Response<ProductImageResult> response) {
                dataCallBack.onFetchSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProductImageResult> call, Throwable t) {
                dataCallBack.onFetchFault(new Exception(t));
            }
        });
    }

    // download product image
    public void downloadProductImage(String token, String fileUrl, BaseCallBack<ResponseBody, Exception> dataCallBack){
        Call<ResponseBody> res = mIProductsService.downloadImageProduct(fileUrl, "Bearer " + token);
        res.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dataCallBack.onFetchSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dataCallBack.onFetchFault(new Exception(t));
            }
        });
    }

}
