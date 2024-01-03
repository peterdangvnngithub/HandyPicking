package com.example.handypicking.activity.syncData;

import android.content.Context;
import android.util.Log;

import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.model.handy_ms;

import java.util.ArrayList;
import java.util.List;

public class DataPresenter {
    private Context mContext;
    private PickingDatabaseHelper myDB;
    private List<handy_ms> handyMS;
    private static final String TAG = "SyncDataPresenter";

    public DataPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public List<handy_ms> getData_HandyMS_Local(String tableName)
    {
        myDB = new PickingDatabaseHelper(mContext);
        handyMS = new ArrayList<>();
        try {
            handyMS = myDB.getAllData_HandyMS(tableName);
        } catch (Exception e)
        {
            Log.e(TAG, "An error occurred: " + e.getMessage());
        }
        return handyMS;
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
