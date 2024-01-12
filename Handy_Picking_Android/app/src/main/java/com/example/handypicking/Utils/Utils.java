package com.example.handypicking.Utils;

import android.util.Log;
import android.app.Dialog;
import android.os.AsyncTask;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.handypicking.R;
import com.example.handypicking.api.ApiClient;
import com.example.handypicking.api.ApiInterface;
import com.example.handypicking.model.handy;
import com.example.handypicking.model.handy_detail;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.preferences.AppPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {
    private Context mContext;
    private AppPreferences appPreferences;
    private boolean isServerRunning = false;
    private static final String TAG = "Utils";

    public Utils(Context mContext, AppPreferences appPreferences) {
        this.mContext       = mContext;
        this.appPreferences = appPreferences;
    }

    public void displayDialogNotification(String title, String message){
        // Display dialog notification
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

    public void checkApiConnection(String apiUrl, final validateApiConnection isApiConnectionValid) {
        Log.d(TAG, apiUrl);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                isApiConnectionValid.isApiConnectionValid(true);
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                isApiConnectionValid.isApiConnectionValid(false);
            }
        });
    }

    public interface validateApiConnection {
        void isApiConnectionValid(boolean isConnected);
    }

    // Kiểm tra kết nối đến API Node.js
    public static void checkNodeApiConnection(String apiUrl, final NodeApiConnectionListener listener) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    URL url = new URL(apiUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000); // Timeout kết nối (5 giây)
                    int responseCode = connection.getResponseCode();
                    return (responseCode == HttpURLConnection.HTTP_OK);
                } catch (IOException e) {
                    Log.e(TAG, "Error checking Node.js API connection", e);
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean isConnected) {
                listener.onConnectionResult(isConnected);
            }
        }.execute();
    }

    // Interface that listen for Node.js API connection results
    public interface NodeApiConnectionListener {
        void onConnectionResult(boolean isConnected);
    }

    public void onSendDataHandy(List<handy_ms> list_handyMS, List<handy_detail> list_handyDetail)
    {
        /*handy listHandy = new handy(list_handyMS, list_handyDetail);
        Gson gson   = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(list_handyMS);

        Log.d(TAG, "onSendDataHandy");

        //Request to server
        RequestBody body            = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        ApiInterface apiInterface   = ApiClient.getApiClient(appPreferences).create(ApiInterface.class);
        Call<Void> call             = apiInterface.send_Handy_Data(listHandy);

        Log.d(TAG, bodyToString(body));

        call.enqueue( new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                try {
                    if (response.isSuccessful()) {
                        Log.d(TAG,"Success:" + response.body().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.d(TAG,"Error:" + t.getLocalizedMessage());
            }
        } );*/
    }

    public void onSendDataHandyMS(List<handy_ms> list_handyMS) {
        Gson gson   = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(list_handyMS);

        Log.d(TAG, "onSendHandyMSData");

        //Request to server
        RequestBody body            = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        ApiInterface apiInterface   = ApiClient.getApiClient(appPreferences).create(ApiInterface.class);
        Call<ResponseBody> call     = apiInterface.send_Handy_MS(body);

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
        Gson gson   = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(list_handyDetail);

        Log.d(TAG, "onSendHandyDetailData");

        //Request to server
        RequestBody body            = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        ApiInterface apiInterface   = ApiClient.getApiClient(appPreferences).create(ApiInterface.class);
        Call<ResponseBody> call     = apiInterface.send_Handy_Detail(body);

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

    Integer countExistsHandyPickingDetail = 0;
    public void check_Exists_List_HandyPicking_Detail(List<handy_detail> list_handyDetail, UtilsView callback) {
        Gson gson   = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(list_handyDetail);

        Log.d(TAG, "Start: check_Exists_List_HandyPicking_Detail");

        //Request to server
        RequestBody body            = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        ApiInterface apiInterface   = ApiClient.getApiClient(appPreferences).create(ApiInterface.class);
        Call<Integer> call          = apiInterface.check_Exists_List_HandyPicking_Detail(body);

        call.enqueue( new Callback<Integer>() {
            @Override
            public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        countExistsHandyPickingDetail = response.body().intValue();
                        Log.d(TAG,"Count:" + countExistsHandyPickingDetail);
                        callback.countDataPickingDetail(countExistsHandyPickingDetail);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                callback.countDataPickingDetail(-1);
                Log.d(TAG,"Error:" + t.getLocalizedMessage());
            }
        } );
        Log.d(TAG, "End: check_Exists_List_HandyPicking_Detail");
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
