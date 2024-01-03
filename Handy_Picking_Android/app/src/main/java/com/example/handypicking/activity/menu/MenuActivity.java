package com.example.handypicking.activity.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import com.example.handypicking.R;
import com.example.handypicking.preferences.AppPreferences;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.activity.setting.SettingActivity;
import com.example.handypicking.activity.picking.pickingMS.PickingMSActivity;
import com.example.handypicking.activity.historyPicking.HistoryPickingActivity;

public class MenuActivity extends AppCompatActivity {
    Button btnPicking, btnHistory, btnData, btnSetting;
    /*ExtendedFloatingActionButton extendedFloatButton;*/
    private PickingDatabaseHelper myDB;
    private AppPreferences appPreferences;
    int countBackup = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPicking  = findViewById(R.id.btnPicking);
        btnHistory  = findViewById(R.id.btnHistory);
        btnData     = findViewById(R.id.btnData);
        btnSetting  = findViewById(R.id.btnSetting);
        /*extendedFloatButton = findViewById(R.id.extendedFloatButton);*/

        appPreferences = AppPreferences.getInstance(this);

        myDB = new PickingDatabaseHelper(MenuActivity.this);

        countBackup = myDB.countBackupData();
        /*extendedFloatButton.setText(String.valueOf(countBackup));*/

        // Setting init value AppReferences
        if (appPreferences.getApiSetting().isEmpty()) {
            appPreferences.setApiSetting("http://192.168.12.7:3000/");
        }

        btnPicking.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, PickingMSActivity.class);
            startActivity(intent);
        });

        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, HistoryPickingActivity.class);
            startActivity(intent);
        });

        btnData.setOnClickListener(v -> {
            /*Intent intent = new Intent(MenuActivity.this, DataLocalActivity.class);
            startActivity(intent);*/
        });

        btnSetting.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, SettingActivity.class);
            startActivity(intent);
        });
    }
}