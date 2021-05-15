package com.anhtong8x.myapplication.contract;

public interface ProductDownloadContract {
    interface View<M, E>{
        void createSuccess(M msg);
        void createFailure(E msg);
    }

    interface Presenter<T, C>{
        void getProductImageHandle(C request, T token);
    }

    interface ViewDownload<M,E>{
        void downloadSuccess(M msg);
        void downloadFailure(E e);
    }

    interface PresenterDownload<T, C>{
        void downloadProductImageHandle(T token, C urlFile);
    }

}
