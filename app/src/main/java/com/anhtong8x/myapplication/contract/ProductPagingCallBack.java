package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.ProductResult;

public interface ProductPagingCallBack {
    void onFetchSuccess(ProductResult userResult);
    void onFetchFault(Exception e);
}
