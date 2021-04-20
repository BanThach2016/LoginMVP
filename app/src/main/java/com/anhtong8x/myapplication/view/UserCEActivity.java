package com.anhtong8x.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anhtong8x.myapplication.R;
import com.anhtong8x.myapplication.apihelper.ApiService;
import com.anhtong8x.myapplication.apihelper.IUserService;
import com.anhtong8x.myapplication.config.GlobalVariableApp;
import com.anhtong8x.myapplication.contract.UserCreateContract;
import com.anhtong8x.myapplication.model.UserCreateRequest;
import com.anhtong8x.myapplication.model.UserResult;
import com.anhtong8x.myapplication.presenter.UserCreatePresenter;
import com.anhtong8x.myapplication.presenter.UserLoginPresenter;

import java.time.LocalDate;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserCEActivity extends AppCompatActivity implements View.OnClickListener, UserCreateContract.View {

    private EditText eFirstName,eLastName,eDob,eEmail,ePhoneNumber,eUserName,ePassword,eConfirmPassword;
    private Button btnSave;
    private UserCreatePresenter userCreatePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);

        int action = getIntent().getIntExtra("action",0);

        initView();

        initPresenter();

        registerListener();

    }

    // init view
    private void initView() {
        eFirstName = findViewById(R.id.eFirstName);
        eLastName = findViewById(R.id.eLastName);
        eDob = findViewById(R.id.eDob);
        eEmail = findViewById(R.id.eEmail);
        ePhoneNumber = findViewById(R.id.ePhoneNumber);
        eUserName = findViewById(R.id.eUserName);
        ePassword = findViewById(R.id.ePassword);
        eConfirmPassword = findViewById(R.id.eConfirmPassword);
        btnSave = findViewById(R.id.btnSave);
    }

    // init event click
    private void initPresenter() {
        btnSave.setOnClickListener(this);
    }

    // init presenter
    private void registerListener() {
        userCreatePresenter = new UserCreatePresenter();
        userCreatePresenter.setmView(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                create();
                break;
            default:
                break;
        }
    }

    @Override
    public void createSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void createFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void create(){
        // 1. check input data
        String sFirName = eFirstName.getText().toString();
        String sLastName = eLastName.getText().toString();
        String sDob = eDob.getText().toString();
        String sEmail = eEmail.getText().toString();
        String sPhoneNumber = ePhoneNumber.getText().toString();
        String sUserName = eUserName.getText().toString();
        String sPassword = ePassword.getText().toString();
        String sConfirmPassword = eConfirmPassword.getText().toString();
        // 2. call presenter save data
        UserCreateRequest userCreateRequest = new UserCreateRequest(
                sFirName,sLastName,sDob,
                sEmail,sPhoneNumber, sUserName,
                sPassword,sConfirmPassword);
        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String token = globalVariableApp.getmToken();

        userCreatePresenter.createHandle(userCreateRequest, token);
    }

    void create1(){
        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String tk = globalVariableApp.getmToken();
        IUserService client = ApiService.getClient().create(IUserService.class);
        Call<UserResult> res = client.Create("Firtname66","Lastname6","2/2/2021",
                "haiboi2316@gmail.com","90909090",
                "haiboi26","HaiBoi@123","HaiBoi@123",
                "Bearer " + tk);
        res.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                //
                UserResult urs = response.body();
                Log.d("Tag","thong bao:    " + urs.getMessage() );
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                Log.d("Tag","not ok");
            }
        });
    }

}