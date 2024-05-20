package com.example.handypicking.activity.historyPicking;

import android.util.Log;

import com.example.handypicking.preferences.AppPreferences;

public class DataLocalPresenter {
    private AppPreferences appPreferences;
    private final DataLocalView view;
    private static final String TAG = "HistoryPickingPresenter";

    public DataLocalPresenter(AppPreferences appPreferences, DataLocalView view) {
        this.appPreferences = appPreferences;
        this.view = view;
    }

    public void logLargeString(String str) {
        if (str.length() > 3000) {
            Log.i(TAG, str.substring(0, 3000));
            logLargeString(str.substring(3000));
        } else {
            Log.i(TAG, str);    // Continuation
        }
    }
}