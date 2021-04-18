package com.anhtong8x.myapplication.apihelper;

import com.anhtong8x.myapplication.model.LoginResult;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IUserService {
    
    @FormUrlEncoded
    @POST("Users/authenticate")
    Call<LoginResult> Authenticate(@Field("UserName") String uName,
                                   @Field("Password") String uPassword,
                                   @Field("RememberMe") boolean uRemember);
}
