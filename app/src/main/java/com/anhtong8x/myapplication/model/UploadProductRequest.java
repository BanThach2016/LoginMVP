package com.anhtong8x.myapplication.model;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadProductRequest {
    int productId;
    RequestBody mCaption;
    RequestBody mIsDefault;
    RequestBody mSortOrder;
    MultipartBody.Part requestFile;

    public UploadProductRequest(int productId, RequestBody mCaption, RequestBody mIsDefault, RequestBody mSortOrder, MultipartBody.Part requestFile) {
        this.productId = productId;
        this.mCaption = mCaption;
        this.mIsDefault = mIsDefault;
        this.mSortOrder = mSortOrder;
        this.requestFile = requestFile;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public RequestBody getmCaption() {
        return mCaption;
    }

    public void setmCaption(RequestBody mCaption) {
        this.mCaption = mCaption;
    }

    public RequestBody getmIsDefault() {
        return mIsDefault;
    }

    public void setmIsDefault(RequestBody mIsDefault) {
        this.mIsDefault = mIsDefault;
    }

    public RequestBody getmSortOrder() {
        return mSortOrder;
    }

    public void setmSortOrder(RequestBody mSortOrder) {
        this.mSortOrder = mSortOrder;
    }

    public MultipartBody.Part getRequestFile() {
        return requestFile;
    }

    public void setRequestFile(MultipartBody.Part requestFile) {
        this.requestFile = requestFile;
    }
}
