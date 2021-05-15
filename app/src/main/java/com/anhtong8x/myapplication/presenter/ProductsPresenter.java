package com.anhtong8x.myapplication.presenter;

import com.anhtong8x.myapplication.service.ProductsService;
import com.anhtong8x.myapplication.config.Message;
import com.anhtong8x.myapplication.contract.BaseCallBack;
import com.anhtong8x.myapplication.contract.BaseContract;
import com.anhtong8x.myapplication.model.ProductPagingRequest;
import com.anhtong8x.myapplication.model.ProductResult;

import java.util.ArrayList;

public class ProductsPresenter implements BaseContract.Presenter<String, String, String, String, String, ProductPagingRequest> {
    private String TAG = ProductsPresenter.class.getSimpleName();

    // view
    private BaseContract.View<ArrayList<ProductResult.Item>, String> mView;

    // services
    private ProductsService mProductsService;

    public void setmView(BaseContract.View<ArrayList<ProductResult.Item>, String> mView) {
        this.mView = mView;
    }

    @Override
    public void createHandle(String request, String token) {

    }

    @Override
    public void updateHandle(String request, String token) {

    }

    @Override
    public void deleteHandle(String request, String token) {

    }

    @Override
    public void getHandle(String request, String token) {

    }

    @Override
    public void getsHandle(ProductPagingRequest request, String token) {
        mProductsService = new ProductsService(request);
        mProductsService.getsPaging(token, new BaseCallBack<ProductResult, Exception>() {
            @Override
            public void onFetchSuccess(ProductResult result) {
                if(result == null){
                    mView.createFailure(Message.MSG_ERROR );
                    return;
                }
                mView.createSuccess(result.getItems());
            }

            @Override
            public void onFetchFault(Exception error) {
                mView.createFailure(Message.MSG_ERROR + error.toString());
            }
        });

    }

}
