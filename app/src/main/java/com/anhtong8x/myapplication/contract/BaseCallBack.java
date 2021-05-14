package com.anhtong8x.myapplication.contract;

public interface BaseCallBack<T,E> {
    void onFetchSuccess(T result);
    void onFetchFault(E error);
}
