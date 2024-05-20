package com.example.handypicking.activity.dataServer.dataServerMS;

import android.content.Context;
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

public class DataServerMSPresenter {
    private Context mContext;
    private PickingDatabaseHelper myDB;
    private List<handy_ms> handyMS;
    private handy handyData;
    private AppPreferences appPreferences;
    private DataServerMSView view;
    private static final String TAG = "DataServerMSPresenter";

    public DataServerMSPresenter(Context mContext, AppPreferences appPreferences, DataServerMSView view) {
        this.mContext       = mContext;
        this.appPreferences = appPreferences;
        this.view           = view;
    }

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

    public void getData_Handy_By_Picking_List(String plNo)
    {
        //Request to server
        Log.d(TAG, "getData_Handy_By_Picking_List");
        ApiInterface apiInterface = ApiClient.getApiClient(appPreferences).create( ApiInterface.class );
        Call<handy> call = apiInterface.getHandyDataByPickingList(plNo);

        call.enqueue( new Callback<handy>() {
            @Override
            public void onResponse(@NonNull Call<handy> call, @NonNull Response<handy> response) {
                if (response.isSuccessful() && response.body() != null) {
                    handyData = (handy) response.body().get_ListHandyDetail();
                    logLargeString(String.valueOf(handyData));
                    view.onInsertDataToLocalTable(handyData);
                } else {
                    Log.d(TAG, "response.isSuccessful(): " + String.valueOf(response.isSuccessful()) +  " - response.code: " + String.valueOf(response.errorBody()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<handy> call, @NonNull Throwable t) {
                Log.d(TAG, t.getLocalizedMessage());
            }
        } );
    }

    public void logLargeString(String str) {
        if (str.length() > 3000) {
            Log.i(TAG, str.substring(0, 3000));
            logLargeString(str.substring(3000));
        } else {
            Log.i(TAG, str);
        }
    }
}
