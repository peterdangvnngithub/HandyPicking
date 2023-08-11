package com.example.handypicking.activity.picking.pickingMS;

import android.util.Log;
import android.content.Context;

import com.example.handypicking.Utils.Utils;
import com.example.handypicking.api.ApiClient;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.api.ApiInterface;
import com.example.handypicking.preferences.AppPreferences;

import java.util.List;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import okio.Buffer;
import okhttp3.RequestBody;

public class PickingMSPresenter {
    private Context mContext;
    private AppPreferences appPreferences;
    private boolean isPickingMSNull = true;
    private static final String TAG = "PickingMSPresenter";
    private Utils utils;

    public PickingMSPresenter(Context mContext, AppPreferences appPreferences, Utils utils) {
        this.mContext = mContext;
        this.appPreferences = appPreferences;
        this.utils = utils;
    }

    public void check_Exists_Data_Handy_MS(String plNo, PickingMSView callback) {
        utils.checkApiConnection(appPreferences.getApiSetting(), new Utils.validateApiConnection() {
            @Override
            public void isApiConnectionValid(boolean isConnected) {
                if(isConnected)
                {
                    Log.d(TAG, appPreferences.getApiSetting());
                    ApiInterface apiInterface = ApiClient.getApiClient(appPreferences).create(ApiInterface.class);
                    Call<List<handy_ms>> call = apiInterface.check_Exists_HandyPicking_MS(plNo);

                    call.enqueue(new Callback<List<handy_ms>>() {
                        @Override
                        public void onResponse(Call<List<handy_ms>> call, Response<List<handy_ms>> response) {
                            if(response.isSuccessful() && response.body() != null) {
                                if (response.body().size() > 0) {
                                    isPickingMSNull = false;
                                    Log.d(TAG, "Success:" + response.body().size());
                                } else {
                                    isPickingMSNull = true;
                                }
                            }
                            callback.isPickingMSNull(isPickingMSNull);
                        }

                        @Override
                        public void onFailure(Call<List<handy_ms>> call, Throwable t) {
                            callback.isPickingMSNull(true);
                            Log.d(TAG,"Error:" + t.getLocalizedMessage());
                        }
                    });
                } else {
                    callback.isPickingMSNull(true);
                }
            }
        });
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
