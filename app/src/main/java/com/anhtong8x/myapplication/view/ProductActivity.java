package com.anhtong8x.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anhtong8x.myapplication.R;
import com.anhtong8x.myapplication.apihelper.ApiService;
import com.anhtong8x.myapplication.apihelper.IProductsService;
import com.anhtong8x.myapplication.apihelper.IUsersService;
import com.anhtong8x.myapplication.config.GlobalVariableApp;
import com.anhtong8x.myapplication.contract.ProductPagingContract;
import com.anhtong8x.myapplication.model.ProductPagingRequest;
import com.anhtong8x.myapplication.model.ProductResult;
import com.anhtong8x.myapplication.model.UserResult;
import com.anhtong8x.myapplication.presenter.ProductPagingPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity implements ProductPagingContract.View, View.OnClickListener {

    private Button btnGetPaging;
    private ProductPagingPresenter productPagingPresenter;

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
        productPagingPresenter = new ProductPagingPresenter();
        productPagingPresenter.setmView(this);
    }

    // init presenter
    private void registerListener() {
        btnGetPaging.setOnClickListener(this);
    }


    private void getPagingProduct() {
        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String token = globalVariableApp.getmToken();
        productPagingPresenter.handlePaging( new ProductPagingRequest(1,1,1),token);
    }

    @Override
    public void getPagingSuccess(ProductResult productResult) {
        Log.d("Tag", productResult.getTotalRecord() + "");
    }

    @Override
    public void getPagingFailure(String mError) {
        Log.d("Tag", mError);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGetPaging:
                Log.d("Tagok", "ok click");
                final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
                final String token = globalVariableApp.getmToken();
               // productPagingPresenter.handlePaging(new ProductPagingRequest(1,1,1),token);
                //getPaging();
                upImageProduct();
                break;
            default:
                break;
        }
    }

    void getPaging(){
        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String token = globalVariableApp.getmToken();
        IProductsService iProductsService = ApiService.getClient().create(IProductsService.class);
        Call<ProductResult> res = iProductsService.getPaging("vi-VN",1,1,1,
                "Bearer " + token);
        res.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {
                Log.d("TagP", "So ban ghi: " + response.body().getTotalRecord());

                ProductResult productResult = response.body();
                ArrayList<ProductResult.Item> lstItem = productResult.getItems();
                for(int i = 0; i < lstItem.size(); i++){
                    Log.d("Tagp","" + lstItem.get(i).getDescription() );
                }

            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {
                Log.d("TagP", "Error" + t.toString());
            }
        });
    }

    void upImageProduct(){
        JSONObject jsParams = new JSONObject();
        try {
            jsParams.put("Caption",1);
            jsParams.put("IsDefault", true);
            jsParams.put("SortOrder", 1);
            jsParams.put("ImageFile","");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final GlobalVariableApp globalVariableApp = (GlobalVariableApp) this.getApplication();
        final String token = globalVariableApp.getmToken();
        IProductsService iProductsService = ApiService.getClient().create(IProductsService.class);
        Call<ResponseBody> res = iProductsService.upImageProduct(1,jsParams.toString());

        res.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TagP", "Error" + response.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TagP", "Error" + t.toString());
            }
        });


    }

}