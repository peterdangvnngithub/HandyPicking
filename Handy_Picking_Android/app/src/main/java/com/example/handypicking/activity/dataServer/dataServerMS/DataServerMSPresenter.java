package com.example.handypicking.activity.dataServer.dataServerMS;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.handypicking.model.handy_ms;
import com.example.handypicking.api.ApiClient;
import com.example.handypicking.api.ApiInterface;
import com.example.handypicking.preferences.AppPreferences;
import com.example.handypicking.database.PickingDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataServerMSPresenter {
    private Context mContext;
    private PickingDatabaseHelper myDB;
    private List<handy_ms> handyMS;
    private AppPreferences appPreferences;
    private DataServerMSView view;
    private static final String TAG = "DataPresenter";

    public DataServerMSPresenter(Context mContext, AppPreferences appPreferences, DataServerMSView view) {
        this.mContext       = mContext;
        this.appPreferences = appPreferences;
        this.view           = view;
    }

/*    public void checkServer_VersionData()
    {
        Log.d(TAG, "checkServer_VersionData");
        myDB = new PickingDatabaseHelper(mContext);

        //Request to server API
        ApiInterface apiInterface   = ApiClient.getApiClient(appPreferences).create(ApiInterface.class);
        Call<Integer> call          = apiInterface.getServer_VersionData();
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                logLargeString(String.valueOf(response.body()));
                if (response.isSuccessful() && response.body() != null) {
                    int versionResponse = response.body();
                  *//*  view.checkServer_VersionData(versionResponse.getVersion(), myDB.getLocalVersion());*//*
                } else {
                    Toast.makeText(mContext, "Not successful: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(mContext, "onFailure: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    public List<handy_ms> getData_Server()
    {
        handyMS = new ArrayList<>();
        //Request to server
        Log.d(TAG, "getData_Server");
        ApiInterface apiInterface = ApiClient.getApiClient(appPreferences).create( ApiInterface.class );
        Call<List<handy_ms>> call = apiInterface.getHandyMS();

        call.enqueue( new Callback<List<handy_ms>>() {
            @Override
            public void onResponse(@NonNull Call<List<handy_ms>> call, @NonNull Response<List<handy_ms>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    logLargeString(String.valueOf(response.body()));
                    handyMS = response.body();
                    view.onGetResult(handyMS);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<handy_ms>> call, @NonNull Throwable t) {
                Log.d(TAG, t.getLocalizedMessage());
            }
        } );

        return handyMS;
    }

    public void logLargeString(String str) {
        if (str.length() > 3000) {
            Log.i(TAG, str.substring(0, 3000));
            logLargeString(str.substring(3000));
        } else {
            Log.i(TAG, str);    // Continuation
        }
    }
}
