package com.example.handypicking.api;

import com.example.handypicking.model.handy_detail;
import com.example.handypicking.model.handy_ms;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/checkApiStatus")
    Call<Void> checkApiStatus();

    @GET("handyPickingMS")
    Call<List<handy_ms>> getHandyMS();

    @GET("handyPickingMS/{plNo}")
    Call<List<handy_ms>> check_Exists_HandyPicking_MS(@Path("plNo") String plNo);

    @GET("handyPickingDetail/{series}")
    Call<List<handy_detail>> check_Exists_HandyPicking_Detail(@Path("series") String series);

    @POST("handyPickingMS")
    Call<ResponseBody> send_Handy_MS(@Body RequestBody requestBody);

    @POST("handyPickingDetail")
    Call<ResponseBody> send_Handy_Detail(@Body RequestBody requestBody);
}