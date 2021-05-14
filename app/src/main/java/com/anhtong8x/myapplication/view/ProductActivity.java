package com.anhtong8x.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anhtong8x.myapplication.R;
import com.anhtong8x.myapplication.utility.GlobalVariableApp;
import com.anhtong8x.myapplication.contract.BaseContract;
import com.anhtong8x.myapplication.model.ProductPagingRequest;
import com.anhtong8x.myapplication.model.ProductResult;
import com.anhtong8x.myapplication.presenter.ProductsPresenter;
import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener, BaseContract.View<ArrayList<ProductResult.Item>, String> {

    private Button btnGetPaging;
    private ProductsPresenter mProductsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initView();

        initPresenter();

        registerListener();

    }

    // init view
    private void initView() {
        btnGetPaging = findViewById(R.id.btnGetPaging);
    }

    // init event click
    private void initPresenter() {
        mProductsPresenter = new ProductsPresenter();
        mProductsPresenter.setmView(this);
    }

    // init presenter
    private void registerListener() {
        btnGetPaging.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGetPaging:
                getsPaging();
                break;
            default:
                break;
        }
    }

    @Override
    public void createSuccess(ArrayList<ProductResult.Item> msg) {
        for(int i = 0; i < msg.size(); i++){
            Toast.makeText(this, "" + msg.get(i).getDescription(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void createFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // code mvp
    void getsPaging(){
        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String token = globalVariableApp.getmToken();
        mProductsPresenter.getsHandle(new ProductPagingRequest("vi-VN",1,1,1), token);
    }

}