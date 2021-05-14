package com.anhtong8x.myapplication.presenter;

import com.anhtong8x.myapplication.service.ProductsService;
import com.anhtong8x.myapplication.contract.ProductPagingContract;
import com.anhtong8x.myapplication.model.ProductPagingRequest;

public class ProductPagingPresenter implements ProductPagingContract.Presenter {
    private String TAG = ProductPagingPresenter.class.getSimpleName();
    private ProductPagingContract.View mView;

    private ProductsService mProductsService;

    public void setmView(ProductPagingContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void handlePaging(ProductPagingRequest productPagingRequest, String token) {
        mProductsService = new ProductsService(productPagingRequest);
    }
}
