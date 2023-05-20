package com.example.handypicking.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://192.168.13.231:4545/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl( BASE_URL )
                    .client(okHttpClient)
                    .addConverterFactory( new NullOnEmptyConverterFactory() )
                    .addConverterFactory( GsonConverterFactory.create( gson ) )
                    .build();
        }

        return retrofit;
    }
}
