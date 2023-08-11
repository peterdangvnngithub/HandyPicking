package com.example.handypicking.activity.picking.pickingDetail;

import android.app.Activity;
import android.util.Log;

import com.example.handypicking.Utils.Utils;
import com.example.handypicking.api.ApiClient;
import com.example.handypicking.api.ApiInterface;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.model.handy_detail;
import com.example.handypicking.preferences.AppPreferences;

import java.util.List;
import java.io.IOException;

import okio.Buffer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.RequestBody;

public class PickingDetailPresenter {
    private static final String TAG = "InventoryPresenter";
    private boolean isPickingDetailNull = true;
    private AppPreferences appPreferences;
    private Utils utils;

    public PickingDetailPresenter(AppPreferences appPreferences, Utils utils) {
        this.appPreferences = appPreferences;
        this.utils = utils;
    }

    public void check_Exists_Data_Handy_Detail(String series, PickingDetailView callback) {
        utils.checkApiConnection(appPreferences.getApiSetting(), new Utils.validateApiConnection() {
            @Override
            public void isApiConnectionValid(boolean isConnected) {
                if(isConnected)
                {
                    ApiInterface apiInterface = ApiClient.getApiClient(appPreferences).create(ApiInterface.class);
                    Call<List<handy_detail>> call = apiInterface.check_Exists_HandyPicking_Detail(series);

                    call.enqueue(new Callback<List<handy_detail>>() {
                        @Override
                        public void onResponse(Call<List<handy_detail>> call, Response<List<handy_detail>> response) {
                            if(response.isSuccessful() && response.body() != null) {
                                if (response.body().size() > 0) {
                                    isPickingDetailNull = false;
                                    Log.d(TAG, "Success:" + response.body().size());
                                } else {
                                    isPickingDetailNull = true;
                                }
                            }
                            callback.isPickingDetailNull(isPickingDetailNull);
                        }

                        @Override
                        public void onFailure(Call<List<handy_detail>> call, Throwable t) {
                            callback.isPickingDetailNull(true);
                            Log.d(TAG,"Error:" + t.getLocalizedMessage());
                        }
                    });
                } else {
                    callback.isPickingDetailNull(true);
                }
            }
        });

    }

    public Call<List<handy_ms>> get_Data_Handy_MS_By_PLNo(String plNo) {
        ApiInterface apiInterface = ApiClient.getApiClient(appPreferences).create(ApiInterface.class);
        return apiInterface.check_Exists_HandyPicking_MS(plNo);
    }

    public void logLargeString(String str) {
        if (str.length() > 3000) {
            Log.i("RESPONSE_BODY_AAA", str.substring(0, 3000));
            logLargeString(str.substring(3000));
        } else {
            Log.i("RESPONSE_BODY_AAA", str);    // Continuation
        }
    }

    private String bodyToString(final RequestBody request){
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if(copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }
}
