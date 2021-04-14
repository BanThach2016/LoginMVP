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
import com.anhtong8x.myapplication.contract.SignInContract;
import com.anhtong8x.myapplication.presenter.SignInPresenter;

public class SignInActivity extends AppCompatActivity implements SignInContract.View, View.OnClickListener {
    private EditText mTextUserName;
    private EditText mTextPassWord;
    private Button mButtonSignIn;
    private TextView mTextViewSignUp;
    private SignInPresenter mSignInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initView();

        initPresenter();

        registerListener();

    }

    private void initView() {
        mTextPassWord = findViewById(R.id.text_password);
        mTextUserName = findViewById(R.id.text_username);
        mButtonSignIn = findViewById(R.id.button_sign_in);
        mTextViewSignUp = findViewById(R.id.button_sign_up);
    }

    private void initPresenter() {
        mButtonSignIn.setOnClickListener(this);
        mTextViewSignUp.setOnClickListener(this);
    }

    private void registerListener() {
        mSignInPresenter = new SignInPresenter();
        mSignInPresenter.setmView(this);
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

    private void login() {
        String u = mTextUserName.getText().toString();
        String p = mTextPassWord.getText().toString();

        if(u.isEmpty() || p.isEmpty()){
            Toast.makeText(this, "UserName or PassWord is empty!", Toast.LENGTH_LONG).show();
            return;
        }

        mSignInPresenter.handleSignIn(u,p);
    }

    @Override
    public void signInSuccess() {
        Toast.makeText(this, "SignIn Success!!!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void signInFailure(String mError) {
        Toast.makeText(this, mError, Toast.LENGTH_LONG).show();
    }

}