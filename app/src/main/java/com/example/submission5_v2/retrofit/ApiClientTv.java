package com.example.submission5_v2.retrofit;

import com.example.submission5_v2.API.ApiTvInterface;
import com.example.submission5_v2.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientTv {

    // Base URL The TV Show DB
    private static final String BASE_URL = BuildConfig.BASE_URL;

    private ApiTvInterface api;

    private static ApiClientTv instance = null;

    private ApiClientTv() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ApiTvInterface.class);
    }

    public static ApiClientTv getInstance() {
        if (instance == null) {
            instance = new ApiClientTv();
        }
        return instance;
    }

    public ApiTvInterface getApi() {
        return api;
    }

}
