package com.anhtong8x.myapplication.presenter;

import com.anhtong8x.myapplication.service.UsersService;
import com.anhtong8x.myapplication.config.Message;
import com.anhtong8x.myapplication.contract.UserLoginContract;
import com.anhtong8x.myapplication.contract.UserLoginCallBack;
import com.anhtong8x.myapplication.model.UserLoginRequest;
import com.anhtong8x.myapplication.model.UserResult;

public class UserLoginPresenter implements UserLoginContract.Presenter {
    private String TAG = UserLoginPresenter.class.getSimpleName();

    private UsersService mUserService;

    private UserLoginContract.View mView;
    public void setmView(UserLoginContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void loginHandle(UserLoginRequest loginRequest) {
        mUserService = new UsersService(loginRequest);
        mUserService.login(new UserLoginCallBack() {
            @Override
            public void onFetchSuccess(UserResult loginResult) {
                if(!loginResult.getSuccessed()){
                    mView.loginFailure(loginResult.getMessage());
                    return;
                }
                // save global variable
                mView.loginSuccess(loginResult.getResultObj());

            }

            @Override
            public void onFetchFault(Exception e) {
                mView.loginFailure(Message.MSG_LOGIN_ERR);
            }
        });
    }
}