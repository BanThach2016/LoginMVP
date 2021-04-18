package com.anhtong8x.myapplication.apihelper;

import com.anhtong8x.myapplication.contract.LoginResultCallBack;
import com.anhtong8x.myapplication.model.LoginRequest;
import com.anhtong8x.myapplication.model.LoginResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {
    IUserService mIUserService;
    LoginRequest mLoginRequest;

    public UserService(LoginRequest mLoginRequest) {
        this.mIUserService = ApiService.getClient().create(IUserService.class);
        this.mLoginRequest = mLoginRequest;
    }

    public void loginResult(final LoginResultCallBack dataCallBack){
        Call<LoginResult> res = mIUserService.Authenticate(mLoginRequest.getUserName(),
                mLoginRequest.getPassword(),
                mLoginRequest.getRememberMe());

        res.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                try {
                    dataCallBack.onFetchSuccess(new LoginResult(
                            response.body().getSuccessed(),
                            response.body().getMessage(),
                            response.body().getResultObj()));

                } catch (Exception e) {
                    dataCallBack.onFetchFault(e);
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                dataCallBack.onFetchFault(new Exception(t));
            }
        });
    }

}
