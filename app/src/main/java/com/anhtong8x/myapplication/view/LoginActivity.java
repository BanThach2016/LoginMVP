package com.anhtong8x.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anhtong8x.myapplication.R;
import com.anhtong8x.myapplication.utility.GlobalVariableApp;
import com.anhtong8x.myapplication.contract.UserLoginContract;
import com.anhtong8x.myapplication.model.UserLoginRequest;
import com.anhtong8x.myapplication.presenter.UserLoginPresenter;

import static com.anhtong8x.myapplication.config.Message.MSG_EMPTY_USER_PASS;

public class LoginActivity extends AppCompatActivity implements UserLoginContract.View, View.OnClickListener {
    private EditText mTextUserName;
    private EditText mTextPassWord;
    private Button mButtonSignIn;
    private TextView mTextViewSignUp;
    private UserLoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        initPresenter();

        registerListener();

    }

    // init view
    private void initView() {
        mTextPassWord = findViewById(R.id.text_password);
        mTextUserName = findViewById(R.id.text_username);
        mButtonSignIn = findViewById(R.id.button_sign_in);
        mTextViewSignUp = findViewById(R.id.button_sign_up);
    }

    // init event click
    private void initPresenter() {
        mButtonSignIn.setOnClickListener(this);
        mTextViewSignUp.setOnClickListener(this);
    }

    // init presenter
    private void registerListener() {
        mLoginPresenter = new UserLoginPresenter();
        mLoginPresenter.setmView(this);
    }

    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()){
            case R.id.button_sign_in:
                login();
                break;
            case R.id.button_sign_up:
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccess(String token) {
        Toast.makeText(this, token, Toast.LENGTH_LONG).show();
        if(token.isEmpty() == false){
            final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
            globalVariableApp.setmToken(token);
            //final String tk = globalVariableApp.getmToken();
            startActivity(new Intent(this, SystemActivity.class));
            //startActivity(new Intent(this, UpLoadProductActivity.class));
        }

    }

    @Override
    public void loginFailure(String mError) {
        Toast.makeText(this, mError, Toast.LENGTH_LONG).show();
    }

    private void login() {
        String u = mTextUserName.getText().toString();
        String p = mTextPassWord.getText().toString();

        if(u.isEmpty() || p.isEmpty()){
            Toast.makeText(this, MSG_EMPTY_USER_PASS, Toast.LENGTH_LONG).show();
            return;
        }

        mLoginPresenter.loginHandle(new UserLoginRequest(u,p,true));
    }

}