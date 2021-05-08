package com.anhtong8x.myapplication.apihelper;

import com.anhtong8x.myapplication.model.ProductResult;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IProductsService {

    //http://192.168.0.175:8088/api/Products/vi-VN?CategoryId=1&PageIndex=1&PageSize=1
    //http://192.168.0.110:8089/api/Products/vi-VN?CategoryId=1&PageIndex=1&PageSize=1
    @GET("Products/{languageId}")
    Call<ProductResult> getPaging(@Path("languageId") String languageId,
                                  @Query("CategoryId") Integer CategoryId,
                                  @Query("PageIndex") Integer PageIndex,
                                  @Query("PageSize") Integer PageSize,
                                  @Header("Authorization") String token
                                  );

    //http://192.168.0.110:8089/api/Products/1/images
    @FormUrlEncoded
    @POST("Products/{productId}/images")
    Call<ResponseBody> upImageProduct(
            @Path("productId") Integer productId,
            @Body String body,
            @Header("Authorization") String token
            );


    @Multipart
    @POST("Products/{productId}/images")
    Call<ResponseBody> uploadFile(
            @Path("productId") Integer productId,
            @Part("Caption") RequestBody Caption,
            @Part("IsDefault") RequestBody IsDefault,
            @Part("SortOrder") RequestBody SortOrder,
            @Part MultipartBody.Part ImageFile,
            @Header("Authorization") String token
    );

}
