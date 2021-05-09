package com.anhtong8x.myapplication.contract;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface UploadProductCallBack {
    void onFetchSuccess(Response<ResponseBody> response);
    void onFetchFault(Exception e);
}
