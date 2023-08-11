package com.example.handypicking.activity.historyPicking;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.handypicking.api.ApiClient;
import com.example.handypicking.api.ApiInterface;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.preferences.AppPreferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPickingPresenter {
    private AppPreferences appPreferences;
    private final HistoryPickingView view;
    private static final String TAG = "HistoryPickingPresenter";

    public HistoryPickingPresenter(AppPreferences appPreferences, HistoryPickingView view) {
        this.appPreferences = appPreferences;
        this.view = view;
    }

    public void getDataHandyMS() {
        //Request to server
        Log.d(TAG, "getData HandyMS");
        ApiInterface apiInterface = ApiClient.getApiClient(appPreferences).create( ApiInterface.class );
        Call<List<handy_ms>> call = apiInterface.getHandyMS();

        call.enqueue( new Callback<List<handy_ms>>() {
            @Override
            public void onResponse(@NonNull Call<List<handy_ms>> call, @NonNull Response<List<handy_ms>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    logLargeString(String.valueOf(response.body()));
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<handy_ms>> call, @NonNull Throwable t) {
                Log.d(TAG, t.getLocalizedMessage());
            }
        } );
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