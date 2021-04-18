package com.anhtong8x.myapplication.apihelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.anhtong8x.myapplication.config.Constant.URL_BASE;

public class ApiService {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
