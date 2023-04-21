package com.example.handypicking.api;

import com.example.handypicking.model.handy_ms;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/")
    Call<Void> getStatus();
    @GET("handyPickingMS")
    Call<List<handy_ms>> getHandyMS();

    @GET("handyPickingMS")
    Call<List<handy_ms>> check_Exists_Handy_MS(@Query("plNo") String plNo);

    @POST("handyPickingMS")
    Call<ResponseBody> send_Handy_MS(@Body RequestBody requestBody);

    @POST("handyPickingDetail")
    Call<ResponseBody> send_Handy_Detail(@Body RequestBody requestBody);
}
