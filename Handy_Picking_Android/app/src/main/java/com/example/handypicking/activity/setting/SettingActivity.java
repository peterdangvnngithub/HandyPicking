package com.example.handypicking.activity.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handypicking.R;
import com.example.handypicking.preferences.AppPreferences;

public class SettingActivity extends AppCompatActivity {
    TextView txtAPI;
    private AppPreferences appPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        txtAPI = findViewById(R.id.txtAPI);

        appPreferences = AppPreferences.getInstance(this);

        // Retrieve the saved API setting from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(appPreferences.getPreferencesName(), Context.MODE_PRIVATE);
        String apiSetting = sharedPreferences.getString(appPreferences.getApiSettingKey(), "");

        txtAPI.setText(apiSetting);

        txtAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_setting_set_web_api);

                Window window = dialog.getWindow();
                if(window == null)
                {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                EditText edAPI;
                Button btnOk, btnCancel;

                edAPI = dialog.findViewById(R.id.edQuantity);
                btnOk = dialog.findViewById(R.id.btnOk);
                btnCancel = dialog.findViewById(R.id.btnCancel);

                edAPI.setText(txtAPI.getText().toString());

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String apiSetting = edAPI.getText().toString().trim();

                        // Save the API setting to SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("apiSetting", apiSetting);
                        editor.apply();

                        txtAPI.setText(apiSetting);
                        dialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                edAPI.requestFocus();

                dialog.show();
            }
        });
    }
}