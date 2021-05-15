package com.anhtong8x.myapplication.service;

import com.anhtong8x.myapplication.model.ProductImageResult;
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
import retrofit2.http.Url;

public interface IProductsService {

    //...api/Products/vi-VN?CategoryId=1&PageIndex=1&PageSize=1
    @GET("api/Products/{languageId}")
    Call<ProductResult> getPaging(@Path("languageId") String languageId,
                                  @Query("CategoryId") Integer CategoryId,
                                  @Query("PageIndex") Integer PageIndex,
                                  @Query("PageSize") Integer PageSize,
                                  @Header("Authorization") String token
                                  );

    //.../api/Products/{productId}/images
    @Multipart
    @POST("api/Products/{productId}/images")
    Call<ResponseBody> uploadFile(
            @Path("productId") Integer productId,
            @Part("Caption") RequestBody Caption,
            @Part("IsDefault") RequestBody IsDefault,
            @Part("SortOrder") RequestBody SortOrder,
            @Part MultipartBody.Part ImageFile,
            @Header("Authorization") String token
    );

    //.../api​/Products​/{productId}​/images​/{imageId}
    @GET("/api/Products/{productId}/images/{imageId}")
    Call<ProductImageResult> getImageProduct(
            @Path("productId") Integer productId,
            @Path("imageId") Integer imageId,
            @Header("Authorization") String token
    );

    @GET
    Call<ResponseBody> downloadImageProduct(
            @Url String fileUrl,
            @Header("Authorization") String token);

}
