package com.example.handypicking.activity.dataServer;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.handypicking.model.handy;
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

public class DataServerPresenter {
    private PickingDatabaseHelper myDB;
    private List<handy_ms> handyMS;
    private AppPreferences appPreferences;
    private static final String TAG = "DataPresenter";

    public DataServerPresenter(AppPreferences appPreferences, DataServerView view) {
        this.appPreferences = appPreferences;
    }

    public List<handy_ms> getData_Server()
    {
        handyMS = new ArrayList<>();
        //Request to server
        Log.d(TAG, "getData_Server");
        ApiInterface apiInterface = ApiClient.getApiClient(appPreferences).create( ApiInterface.class );
        Call<handy> call = apiInterface.getDataServer();

        call.enqueue( new Callback<handy>() {
            @Override
            public void onResponse(@NonNull Call<handy> call, @NonNull Response<handy> response) {
                if (response.isSuccessful() && response.body() != null) {
                    logLargeString(String.valueOf(response.body()));
                    myDB.syncDataServer((handy) call);
                }
            }

            @Override
            public void onFailure(@NonNull Call<handy> call, @NonNull Throwable t) {
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
