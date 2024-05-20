package com.example.handypicking.activity.dataLocal;

import com.example.handypicking.model.handy_ms;

import java.util.List;

public interface DataLocalView {
    void showLoading();

    void hideLoading();

    void onGetResult(List<handy_ms> resultModels);

    void onErrorLoading(String message);
}
