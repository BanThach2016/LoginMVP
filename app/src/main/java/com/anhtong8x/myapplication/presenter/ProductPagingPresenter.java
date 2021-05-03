package com.anhtong8x.myapplication.presenter;

import com.anhtong8x.myapplication.apihelper.ProductsService;
import com.anhtong8x.myapplication.config.Message;
import com.anhtong8x.myapplication.contract.ProductPagingCallBack;
import com.anhtong8x.myapplication.contract.ProductPagingContract;
import com.anhtong8x.myapplication.model.ProductPagingRequest;
import com.anhtong8x.myapplication.model.ProductResult;

public class ProductPagingPresenter implements ProductPagingContract.Presenter {
    private String TAG = ProductPagingPresenter.class.getSimpleName();
    private ProductPagingContract.View mView;

    private ProductsService mProductsService;

    public void setmView(ProductPagingContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void handlePaging(ProductPagingRequest productPagingRequest, String token) {
        mProductsService = new ProductsService(productPagingRequest, token);
        mProductsService.getPaging(new ProductPagingCallBack() {
            @Override
            public void onFetchSuccess(ProductResult result) {
                if(result.getTotalRecord() <= 0 ){
                    mView.getPagingFailure("Khong co du lieu");
                    return;
                }
                mView.getPagingSuccess(result);
            }

            @Override
            public void onFetchFault(Exception e) {
                mView.getPagingFailure(Message.MSG_CREATE_ERR + e.toString());
            }
        });
    }
}
