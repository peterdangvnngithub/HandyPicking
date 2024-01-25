package com.example.handypicking.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private static final String PREF_NAME = "AppPreferences";
    private static final String API_SETTING_KEY = "apiSetting";

    private static AppPreferences instance;
    private SharedPreferences sharedPreferences;

    public AppPreferences(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized AppPreferences getInstance(Context context) {
        if (instance == null) {
            instance = new AppPreferences(context);
        }
        return instance;
    }

    public void setApiSetting(String apiSetting) {
        if (apiSetting != null) {
            sharedPreferences.edit().putString(API_SETTING_KEY, apiSetting).apply();
        }
    }

    public String getApiSetting() {
        return sharedPreferences.getString(API_SETTING_KEY, null );
    }

    public String getPreferencesName() {
        return PREF_NAME;
    }

    public static String getApiSettingKey() {
        return API_SETTING_KEY;
    }
}
