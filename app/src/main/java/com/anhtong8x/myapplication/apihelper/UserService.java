package com.anhtong8x.myapplication.apihelper;

import android.util.Log;

import com.anhtong8x.myapplication.contract.UserCreateCallBack;
import com.anhtong8x.myapplication.contract.UserLoginCallBack;
import com.anhtong8x.myapplication.model.UserCreateRequest;
import com.anhtong8x.myapplication.model.UserLoginRequest;
import com.anhtong8x.myapplication.model.UserResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {
    IUserService mIUserService;
    UserLoginRequest mLoginRequest;
    UserCreateRequest mUserCreateRequest;

    public void setmUserCreateRequest(UserCreateRequest mUserCreateRequest) {
        this.mUserCreateRequest = mUserCreateRequest;
    }

    public void setmLoginRequest(UserLoginRequest mLoginRequest) {
        this.mLoginRequest = mLoginRequest;
    }

    public UserService() {
        this.mIUserService = ApiService.getClient().create(IUserService.class);
    }

    public UserService(UserCreateRequest mUserCreateRequest) {
        this.mIUserService = ApiService.getClient().create(IUserService.class);
        this.mUserCreateRequest = mUserCreateRequest;
    }

    public UserService(UserLoginRequest mLoginRequest) {
        this.mIUserService = ApiService.getClient().create(IUserService.class);
        this.mLoginRequest = mLoginRequest;
    }

    public void login(final UserLoginCallBack dataCallBack){
        Call<UserResult> res = mIUserService.GetToken(mLoginRequest.getUserName(),
                mLoginRequest.getPassword(),
                mLoginRequest.getRememberMe());

        res.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                try {
                    dataCallBack.onFetchSuccess(response.body());

                } catch (Exception e) {
                    dataCallBack.onFetchFault(e);
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                dataCallBack.onFetchFault(new Exception(t));
            }
        });
    }

    public void create(String token, final UserCreateCallBack dataCallBack){
        Call<UserResult> res = mIUserService.Create(
                mUserCreateRequest.getuFirstName(),
                mUserCreateRequest.getuLastName(),
                mUserCreateRequest.getuDob(),
                mUserCreateRequest.getuEmail(),
                mUserCreateRequest.getuPhoneNumber(),
                mUserCreateRequest.getuUserName(),
                mUserCreateRequest.getuPassword(),
                mUserCreateRequest.getuConfirmPassword(),
                "Bearer " + token
                );
        res.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                try {
                    dataCallBack.onFetchSuccess(response.body());
                } catch (Exception e) {
                    dataCallBack.onFetchFault(e);
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                dataCallBack.onFetchFault(new Exception(t));
            }
        });

    }
}
