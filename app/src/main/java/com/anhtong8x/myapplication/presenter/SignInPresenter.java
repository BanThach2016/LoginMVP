package com.anhtong8x.myapplication.presenter;

import com.anhtong8x.myapplication.contract.SignInContract;

public class SignInPresenter implements SignInContract.Presenter {
    private SignInContract.View mView;

    public void setmView(SignInContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void handleSignIn(String mUserName, String mPassWord) {
        if (mUserName.equals("huy") && mPassWord.equals("123") ){
            mView.signInSuccess();
            return;
        }
        mView.signInFailure("Username or Password not true!");
    }
}
