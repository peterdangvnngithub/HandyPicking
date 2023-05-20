package com.example.handypicking.activity.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import com.example.handypicking.R;
import com.example.handypicking.Utils.Utils;
import com.example.handypicking.Utils.UtilsView;
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
    PickingDatabaseHelper myDB;
    Utils utils;
    List<handy_ms> list_handyMs             = new ArrayList<>();
    List<handy_detail> list_handyDetails    = new ArrayList<>();
    final String TABLE_HANDY_MS_LOCAL       = "handy_ms_local";
    final String TABLE_HANDY_DETAIL_LOCAL   = "handy_detail_local";
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

        myDB = new PickingDatabaseHelper(MenuActivity.this);

        countBackup = myDB.countBackupData();
        extendedFloatButton.setText(String.valueOf(countBackup));

        utils = new Utils(MenuActivity.this);

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
                //TODO: Dong bo du lieu back up xuong SQL Server
                list_handyMs        = myDB.getAllData_From_HandyMS(TABLE_HANDY_MS_LOCAL);
                list_handyDetails   = myDB.getAllData_From_HandyDetail(TABLE_HANDY_DETAIL_LOCAL);

                utils.isServerRunning(new UtilsView() {
                    @Override
                    public void onServerStatusReceived(boolean isServerRunning) {
                        if(isServerRunning)
                        {
                            utils.onSendDataHandyMS(list_handyMs);
                            utils.onSendDataHandyDetail(list_handyDetails);

                            myDB.deleteData(TABLE_HANDY_MS_LOCAL);
                            myDB.deleteData(TABLE_HANDY_DETAIL_LOCAL);

                            String title = "Thông báo";
                            String message = "Đồng bộ dữ liệu thành công";
                            utils.displayDialogNotification(title, message);

                            btnSync.setEnabled(false);
                            extendedFloatButton.setText("0");
                        } else {
                            String title = "Cảnh báo";
                            String message = "Không thể kết nối đến server. Xin hãy đồng bộ sau";
                            utils.displayDialogNotification(title, message);
                        }
                    }
                });
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