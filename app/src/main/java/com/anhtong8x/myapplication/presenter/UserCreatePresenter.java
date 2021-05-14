package com.anhtong8x.myapplication.presenter;

import com.anhtong8x.myapplication.service.UsersService;
import com.anhtong8x.myapplication.config.Message;
import com.anhtong8x.myapplication.contract.UserCreateCallBack;
import com.anhtong8x.myapplication.contract.UserCreateContract;
import com.anhtong8x.myapplication.model.UserCreateRequest;
import com.anhtong8x.myapplication.model.UserResult;

public class UserCreatePresenter implements UserCreateContract.Presenter {
    private String TAG = UserCreatePresenter.class.getSimpleName();
    private UserCreateContract.View mView;

    private UsersService mUserService;

    public void setmView(UserCreateContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void createHandle(UserCreateRequest userCreateRequest, String token) {
        mUserService = new UsersService(userCreateRequest, token);
        mUserService.create1(new UserCreateCallBack() {
            @Override
            public void onFetchSuccess(UserResult userResult) {
                if(!userResult.getSuccessed()){
                    mView.createFailure(userResult.getMessage());
                    return;
                }
                mView.createSuccess(userResult.getMessage());

            }

            @Override
            public void onFetchFault(Exception e) {
                mView.createFailure(Message.MSG_CREATE_ERR);
            }
        });
    }
}