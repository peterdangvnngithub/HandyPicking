package com.example.handypicking.activity.picking.pickingDetail;

import android.util.Log;
import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.handypicking.api.ApiClient;
import com.example.handypicking.api.ApiInterface;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.model.handy_detail;

import java.util.List;
import java.io.IOException;

import okio.Buffer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class PickingDetailPresenter {
    private static final String TAG     = "InventoryPresenter";
    private boolean isPickingDetailNull = false;

    public void check_Exists_Data_Handy_Detail(String series, PickingDetailView callback) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
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
                callback.isPickingDetailNull(isPickingDetailNull);
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
