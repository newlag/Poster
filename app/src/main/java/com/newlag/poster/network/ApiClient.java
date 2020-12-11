package com.newlag.poster.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofitInstance = null;
    private static final String BASE_URL = "https://newlag.myarena.site/";

    public static Retrofit getInstance() {
        if (retrofitInstance == null) {
            Gson gson = new GsonBuilder()
                .setLenient().
                create();
            retrofitInstance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        }
        return retrofitInstance;
    }
}
