package com.example.handypicking.activity.picking.pickingDetail;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.handypicking.activity.picking.pickingMS.PickingMSActivity;
import com.example.handypicking.api.ApiClient;
import com.example.handypicking.api.ApiInterface;
import com.example.handypicking.model.handy_detail;
import com.example.handypicking.model.handy_ms;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okio.Buffer;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickingDetailPresenter{
    private static final String TAG = "InventoryPresenter";

    public boolean checkConnection() {
        try {
            ApiInterface apiInterface = ApiClient.getApiClient().create( ApiInterface.class );
            Response<Void> response = apiInterface.checkConnection().execute();
            return response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void getData() {
        //Request to server
        Log.d(TAG, "getData");
        ApiInterface apiInterface = ApiClient.getApiClient().create( ApiInterface.class );
        Call<List<handy_ms>> call = apiInterface.getHandyMS();

        call.enqueue( new Callback<List<handy_ms>>() {
            @Override
            public void onResponse(@NonNull Call<List<handy_ms>> call, @NonNull Response<List<handy_ms>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, String.valueOf(response.body()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<handy_ms>> call, @NonNull Throwable t) {
                Log.d(TAG, t.getLocalizedMessage());
            }
        } );
    }

    public List<handy_ms> check_Exists_Data_Handy_MS(String plNo) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<handy_ms>> call = apiInterface.check_Exists_Handy_MS(plNo);

        try {
            Response<List<handy_ms>> response = call.execute();

            List<handy_ms> handyMSList = new ArrayList<>();

            if (response.isSuccessful()) {
                handyMSList = response.body();
            }

            return handyMSList;
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch data from server.", e);
        }
    }

    public void onSendDataHandyMS(List<handy_ms> list_handyMS) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(list_handyMS);

        Log.d(TAG, "onSendHandyMSData");

        //Request to server
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.send_Handy_MS(body);

        Log.d(TAG, bodyToString(body));

        call.enqueue( new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                /*view.hideLoading();*/
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        /*view.insertSuccess();*/
                        Log.d(TAG,"Success:" + response.body().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                /*view.hideLoading();*/
                Log.d(TAG,"Error:" + t.getLocalizedMessage());
            }
        } );
    }

    public void onSendDataHandyDetail(List<handy_detail> list_handyDetail) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(list_handyDetail);

        Log.d(TAG, "onSendHandyDetailData");

        //Request to server
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.send_Handy_Detail(body);

        Log.d(TAG, bodyToString(body));

        call.enqueue( new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                /*view.hideLoading();*/
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        /*view.insertSuccess();*/
                        Log.d(TAG,"Success:" + response.body().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                /*view.hideLoading();*/
                Log.d(TAG,"Error:" + t.getLocalizedMessage());
            }
        } );
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
