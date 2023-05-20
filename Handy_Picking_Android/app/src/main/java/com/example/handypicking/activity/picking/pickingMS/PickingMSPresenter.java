package com.example.handypicking.activity.picking.pickingMS;

import android.util.Log;

import com.example.handypicking.api.ApiClient;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.api.ApiInterface;

import java.util.List;
import java.io.IOException;

import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickingMSPresenter {
    private boolean isPickingMSNull = false;
    private static final String TAG = "PickingMSPresenter";
    public void check_Exists_Data_Handy_MS(String plNo,PickingMSView callback) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
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
                callback.isPickingMSNull(false);
                Log.d(TAG,"Error:" + t.getLocalizedMessage());
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
