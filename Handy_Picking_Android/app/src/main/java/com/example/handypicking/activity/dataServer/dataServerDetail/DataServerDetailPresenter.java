package com.example.handypicking.activity.dataServer.dataServerDetail;

import android.util.Log;

import com.example.handypicking.api.ApiClient;
import com.example.handypicking.api.ApiInterface;
import com.example.handypicking.model.handy_detail;
import com.example.handypicking.preferences.AppPreferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataServerDetailPresenter {
    private final String TAG = "DataServerDetailPre";
    private AppPreferences appPreferences;
    DataServerDetailView view;

    public DataServerDetailPresenter(AppPreferences appPreferences, DataServerDetailView view) {
        this.appPreferences = appPreferences;
        this.view = view;
    }

    public void get_Data_Handy_Detail_By_PickingNo(String pickingNo) {
        ApiInterface apiInterface = ApiClient.getApiClient(appPreferences).create(ApiInterface.class);
        Call<List<handy_detail>> call = apiInterface.get_HandyPicking_Detail(pickingNo);

        call.enqueue(new Callback<List<handy_detail>>() {
            @Override
            public void onResponse(Call<List<handy_detail>> call, Response<List<handy_detail>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    if (response.body().size() > 0) {
                        view.onGetResult(response.body());
                    } else {
                    }
                }
            }

            @Override
            public void onFailure(Call<List<handy_detail>> call, Throwable t) {
                Log.d(TAG,"Error:" + t.getLocalizedMessage());
            }
        });
    }
}
