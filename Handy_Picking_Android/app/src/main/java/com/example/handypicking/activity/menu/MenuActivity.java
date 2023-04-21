package com.example.handypicking.activity.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.handypicking.R;
import com.example.handypicking.activity.historyPicking.HistoryPickingActivity;
import com.example.handypicking.activity.picking.pickingMS.PickingMSActivity;
import com.example.handypicking.activity.setting.SettingActivity;

public class MenuActivity extends AppCompatActivity {
    Button btnPicking, btnHistory, btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPicking = findViewById(R.id.btnPicking);
        btnHistory = findViewById(R.id.btnHistory);
        btnSetting = findViewById(R.id.btnSetting);

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

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}