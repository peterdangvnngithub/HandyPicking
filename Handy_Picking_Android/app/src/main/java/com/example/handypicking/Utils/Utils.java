package com.example.handypicking.Utils;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.handypicking.R;
import com.example.handypicking.api.ApiClient;
import com.example.handypicking.api.ApiInterface;
import com.example.handypicking.model.handy_detail;
import com.example.handypicking.model.handy_ms;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {
    private Context mContext;
    private boolean isServerRunning = false;
    private static final String TAG = "Utils";
    public Utils(Context context) {
        this.mContext = context;
    }

    public void displayDialogNotification(String title, String message){
        //TODO: Display dialog notification
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notification);

        TextView txtTitleNotification, txtNotificationContent;
        Button btnNotificationOk;

        txtTitleNotification    = dialog.findViewById(R.id.txtTitleNotification);
        txtNotificationContent  = dialog.findViewById(R.id.txtNotificationContent);
        btnNotificationOk       = dialog.findViewById(R.id.btnNotificationOk);

        // Set title
        txtTitleNotification.setText(title);

        // Set message
        txtNotificationContent.setText(message);

        btnNotificationOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void isServerRunning(UtilsView callback) {
        try {
            Log.d(TAG, "Executing network request to check server status...");
            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<Void> call = apiInterface.checkApiStatus();

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 200) {
                        isServerRunning = true;
                        Log.d(TAG, "response.code(): " + response.code() + ". isServerRunning: " + isServerRunning);
                    } else {
                        isServerRunning = false;
                        Log.d(TAG, "response.code(): " + response.code() + ". isServerRunning: " + isServerRunning);
                    }
                    callback.onServerStatusReceived(isServerRunning);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Handle the error
                    isServerRunning = false;
                    Log.d(TAG, "onFailure: " + isServerRunning);
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    callback.onServerStatusReceived(isServerRunning);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error occurred while checking server status: " + e.getMessage());
            e.printStackTrace();
            callback.onServerStatusReceived(isServerRunning);
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
