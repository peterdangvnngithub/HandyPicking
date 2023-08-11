package com.example.handypicking.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.handypicking.preferences.AppPreferences;

public class ApiClient {
    private static Retrofit retrofit;
    private static String TAG = "ApiClient";

    public static Retrofit getApiClient(AppPreferences appPreferences) {
        String BASE_URL = appPreferences.getApiSetting();
        Log.d(TAG, BASE_URL);
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
