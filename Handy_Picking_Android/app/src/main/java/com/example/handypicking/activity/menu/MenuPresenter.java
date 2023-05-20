package com.example.handypicking.activity.menu;

import android.content.Context;

import com.example.handypicking.database.PickingDatabaseHelper;

public class MenuPresenter {
    private Context mContext;

    public MenuPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public int count_Data_Backup() {
        PickingDatabaseHelper myDB = new PickingDatabaseHelper(mContext);

        return 0;
    }
}
