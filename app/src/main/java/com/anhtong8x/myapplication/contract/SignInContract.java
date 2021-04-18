package com.anhtong8x.myapplication.contract;

public interface SignInContract {

    interface View {
        void signInSuccess();

        void signInFailure(String mError);
    }

    interface Presenter{
        void handleSignIn(String mUserName, String mPassWord);


    }

}
