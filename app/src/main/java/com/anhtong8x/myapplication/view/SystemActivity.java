package com.anhtong8x.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anhtong8x.myapplication.R;
import com.anhtong8x.myapplication.apihelper.ApiService;
import com.anhtong8x.myapplication.apihelper.IProductsService;
import com.anhtong8x.myapplication.config.GlobalVariableApp;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class SystemActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreate, btnEdit, btnDelete, btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        initView();

        initPresenter();

    }

    // init view
    private void initView() {
        btnCreate = findViewById(R.id.btnCreate);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnTest = findViewById(R.id.btnTest);
    }

    // init event click
    private void initPresenter() {
        btnCreate.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreate:
                CallActivity(1);
                break;
            case R.id.btnEdit:
                CallActivity(0);
                break;
            case R.id.btnDelete:
                startActivity(new Intent(this, ProductActivity.class));
                break;
            case R.id.btnTest:
                testFunction();
                break;
            default:
                break;
        }
    }

    private void testFunction() {
        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String token = globalVariableApp.getmToken();
        IProductsService iProductsService = ApiService.getClient().create(IProductsService.class);

        String Caption = "Caption hello, this is description speaking";
        Boolean IsDefault = Boolean.TRUE;
        Integer SortOrder = 1;



    }

    private void CallActivity(int action){
        Intent intent = new Intent(this, UserCEActivity.class);
        intent.putExtra("action", action);
        startActivity(intent);
    }
}