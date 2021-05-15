package com.anhtong8x.myapplication.presenter;

import android.util.Log;

import com.anhtong8x.myapplication.config.Message;
import com.anhtong8x.myapplication.contract.BaseCallBack;
import com.anhtong8x.myapplication.contract.ProductDownloadContract;
import com.anhtong8x.myapplication.model.ProductDownloadImageRequest;
import com.anhtong8x.myapplication.model.ProductImageResult;
import com.anhtong8x.myapplication.service.ProductsService;
import com.anhtong8x.myapplication.utility.WriteFileToDisk;

import okhttp3.ResponseBody;

public class ProductDownloadPresenter implements ProductDownloadContract.Presenter<String, ProductDownloadImageRequest>,
    ProductDownloadContract.PresenterDownload<String, String>
{
    String TAG = ProductDownloadPresenter.class.getSimpleName();

    ProductsService mProductsService;

    // View process get product image
    private ProductDownloadContract.View<ProductImageResult, String> mView;
    public void setmView(ProductDownloadContract.View<ProductImageResult, String> mView) {
        this.mView = mView;
    }

    // View process download product image
    private ProductDownloadContract.ViewDownload<String, String> mViewDownload;
    public void setmViewDownload(ProductDownloadContract.ViewDownload<String, String> mViewDownload) {
        this.mViewDownload = mViewDownload;
    }

    @Override
    public void getProductImageHandle(ProductDownloadImageRequest request, String token) {
        mProductsService = new ProductsService(request);
        mProductsService.getProductImage(token, new BaseCallBack<ProductImageResult, Exception>() {
            @Override
            public void onFetchSuccess(ProductImageResult result) {
                if(result == null){
                    mView.createFailure(Message.MSG_ERROR );
                    return;
                }
                Log.d(TAG, "" + result);
                mView.createSuccess(result);
            }

            @Override
            public void onFetchFault(Exception error) {
                mView.createFailure(Message.MSG_ERROR + error.toString());
            }
        });
    }

    @Override
    public void downloadProductImageHandle(String token, String urlFile) {
        mProductsService = new ProductsService();
        mProductsService.downloadProductImage(token, urlFile, new BaseCallBack<ResponseBody, Exception>() {
            @Override
            public void onFetchSuccess(ResponseBody result) {
                boolean writeToDisk = new WriteFileToDisk().writeToDisk(result);
                mViewDownload.downloadSuccess("download file thanh cong " + writeToDisk);
            }

            @Override
            public void onFetchFault(Exception error) {
                mViewDownload.downloadFailure("Error download " + error.toString());
            }
        });
    }
}
