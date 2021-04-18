package com.anhtong8x.myapplication.presenter;

import com.anhtong8x.myapplication.apihelper.UserService;
import com.anhtong8x.myapplication.config.Message;
import com.anhtong8x.myapplication.contract.LoginContract;
import com.anhtong8x.myapplication.contract.LoginResultCallBack;
import com.anhtong8x.myapplication.model.LoginRequest;
import com.anhtong8x.myapplication.model.LoginResult;

public class LoginPresenter implements LoginContract.Presenter {
    private String TAG = LoginPresenter.class.getSimpleName();
    private LoginContract.View mView;

    private UserService mUserService;

    public void setmView(LoginContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void loginHandle(LoginRequest loginRequest) {
        mUserService = new UserService(loginRequest);
        mUserService.loginResult(new LoginResultCallBack() {
            @Override
            public void onFetchSuccess(LoginResult loginResult) {
                if(!loginResult.getSuccessed()){
                    mView.loginFailure(Message.MSG_LOGIN_ERROR);
                    return;
                }

                mView.loginSuccess("Save Token: " + loginResult.getResultObj());

            }

            @Override
            public void onFetchFault(Exception e) {
                mView.loginFailure(Message.MSG_LOGIN_ERROR);
            }
        });
    }

}