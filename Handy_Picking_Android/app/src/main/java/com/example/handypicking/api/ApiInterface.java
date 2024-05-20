package com.example.handypicking.api;

import com.example.handypicking.model.ServerVersion;
import com.example.handypicking.model.handy;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.model.handy_detail;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    /*@GET("system")
    Call<Integer> getServer_VersionData();*/

    @GET("/checkApiStatus")
    Call<Void> checkApiStatus();

    @GET("handyPickingMS")
    Call<List<handy_ms>> getHandyMS();

    @GET("handy/{pickingNo}")
    Call<handy> getHandyDataByPickingList(@Path("pickingNo") String plNo);

    @GET("handyPickingMS/{pickingNo}")
    Call<List<handy_ms>> check_Exists_HandyPicking_MS(@Path("pickingNo") String plNo);

    @GET("handyPickingDetail/{series}")
    Call<List<handy_detail>> check_Exists_HandyPicking_Detail(@Path("series") String series);

    @GET("handyPickingDetail/{pickingNo}")
    Call<List<handy_detail>> get_HandyPicking_Detail(@Path("pickingNo") String pickingNo);

    @POST("checkExistsDetail")
    Call<Integer> check_Exists_List_HandyPicking_Detail(@Body RequestBody requestBody);

    @POST("handyPickingMS")
    Call<ResponseBody> send_Handy_MS(@Body RequestBody requestBody);

    @POST("handyPickingDetail")
    Call<ResponseBody> send_Handy_Detail(@Body RequestBody requestBody);

    @POST("handyPicking")
    Call<Void> send_Handy_Data(@Body handy handy_Data);
}
