package com.anhtong8x.myapplication.apihelper;

import com.anhtong8x.myapplication.model.UserResult;

import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IUsersService {
    
    @FormUrlEncoded
    @POST("Users/authenticate")
    Call<UserResult> GetToken(@Field("UserName") String uName,
                              @Field("Password") String uPassword,
                              @Field("RememberMe") boolean uRemember);

    @FormUrlEncoded
    @POST("/api/Users")
    Call<UserResult> Create(@Field("FirstName") String uFirstName,
                            @Field("LastName") String uLastName,
                            @Field("Dob") String uDob,
                            @Field("Email") String uEmail,
                            @Field("PhoneNumber") String uPhoneNumber,
                            @Field("UserName") String uUserName,
                            @Field("Password") String uPassword,
                            @Field("ConfirmPassword") String uConfirmPassword,
                            @Header("Authorization") String token
                             );

    @FormUrlEncoded
    @POST("/api/Users")
    Call<UserResult> Create1(@Field("FirstName") String uFirstName,
                            @Field("LastName") String uLastName,
                            @Field("Dob") String uDob,
                            @Field("Email") String uEmail,
                            @Field("PhoneNumber") String uPhoneNumber,
                            @Field("UserName") String uUserName,
                            @Field("Password") String uPassword,
                            @Field("ConfirmPassword") String uConfirmPassword
    );

}
