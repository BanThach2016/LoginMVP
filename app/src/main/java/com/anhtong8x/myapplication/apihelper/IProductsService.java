package com.anhtong8x.myapplication.apihelper;

import com.anhtong8x.myapplication.model.ProductResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IProductsService {

    //http://192.168.0.175:8088/api/Products/vi-VN?CategoryId=1&PageIndex=1&PageSize=1
    @GET("Products/vi-VN")
    Call<ProductResult> getPaging(@Query("CategoryId") int CategoryId,
                                  @Query("PageIndex") int PageIndex,
                                  @Query("PageSize") int PageSize);
}
