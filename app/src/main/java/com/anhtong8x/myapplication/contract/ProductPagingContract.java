package com.anhtong8x.myapplication.contract;

import com.anhtong8x.myapplication.model.ProductPagingRequest;
import com.anhtong8x.myapplication.model.ProductResult;

public interface ProductPagingContract {
    interface View {
        void getPagingSuccess(ProductResult productResult);

        void getPagingFailure(String mError);
    }

    interface Presenter{
        void handlePaging(ProductPagingRequest productPagingRequest, String token);

    }
}
