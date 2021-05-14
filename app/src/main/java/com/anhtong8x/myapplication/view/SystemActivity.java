package com.anhtong8x.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anhtong8x.myapplication.R;

public class SystemActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreate, btnEdit, btnGets, btnUploadFile;

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
        btnGets = findViewById(R.id.btnGets);
        btnUploadFile = findViewById(R.id.btnUploadFile);
    }

    // init event click
    private void initPresenter() {
        btnCreate.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnGets.setOnClickListener(this);
        btnUploadFile.setOnClickListener(this);
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
            case R.id.btnGets:
                startActivity(new Intent(this, ProductActivity.class));
                break;
            case R.id.btnUploadFile:
                startActivity(new Intent(this, UpLoadProductActivity.class));
                break;
            default:
                break;
        }
    }

    private void CallActivity(int action){
        Intent intent = new Intent(this, UserCEActivity.class);
        intent.putExtra("action", action);
        startActivity(intent);
    }
}