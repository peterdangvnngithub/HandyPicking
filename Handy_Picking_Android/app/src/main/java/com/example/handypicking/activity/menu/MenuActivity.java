package com.example.handypicking.activity.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.handypicking.activity.syncData.SyncDataActivity;
import com.example.handypicking.preferences.AppPreferences;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import com.example.handypicking.R;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.activity.setting.SettingActivity;
import com.example.handypicking.activity.picking.pickingMS.PickingMSActivity;
import com.example.handypicking.activity.historyPicking.HistoryPickingActivity;

import com.example.handypicking.model.handy_ms;
import com.example.handypicking.model.handy_detail;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    Button btnPicking, btnHistory,btnSync , btnSetting;
    ExtendedFloatingActionButton extendedFloatButton;
    private PickingDatabaseHelper myDB;
    private AppPreferences appPreferences;
    int countBackup = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPicking          = findViewById(R.id.btnPicking);
        btnHistory          = findViewById(R.id.btnHistory);
        btnSync             = findViewById(R.id.btnSync);
        btnSetting          = findViewById(R.id.btnSetting);
        extendedFloatButton = findViewById(R.id.extendedFloatButton);

        appPreferences = AppPreferences.getInstance(this);

        myDB = new PickingDatabaseHelper(MenuActivity.this);

        countBackup = myDB.countBackupData();
        extendedFloatButton.setText(String.valueOf(countBackup));

        // Setting init value AppReferences
        if (appPreferences.getApiSetting().isEmpty()) {
            appPreferences.setApiSetting("http://192.168.12.7:3000/");
        }

        if(countBackup > 0)
        {
            btnSync.setEnabled(true);
        } else {
            btnSync.setEnabled(false);
        }

        btnPicking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check exists data picking master in SQLite
                Intent intent = new Intent(MenuActivity.this, PickingMSActivity.class);
                startActivity(intent);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HistoryPickingActivity.class);
                startActivity(intent);
            }
        });

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SyncDataActivity.class);
                startActivity(intent);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}