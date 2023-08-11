package com.example.handypicking.activity.picking.pickingMS;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.graphics.Color;

import com.example.handypicking.R;
import com.example.handypicking.Utils.Utils;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.activity.menu.MenuActivity;
import com.example.handypicking.preferences.AppPreferences;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.activity.picking.pickingDetail.PickingDetailActivity;

import java.util.Calendar;

public class PickingMSActivity extends AppCompatActivity {
    Button btnNext, btnCancel;
    TextView txtCustomerCode, txtEmpCode ;
    EditText edCustomerCode, edPLNumber, edDeliveryAddressCode, edDeliveryAddressName, edEmpCode;
    PickingMSPresenter pickingMSPresenter;
    AppPreferences appPreferences;
    private handy_ms handy_ms;
    final String TABLE_HANDY_MS = "handy_ms";
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking_ms);

        // Label
        txtCustomerCode         = findViewById(R.id.txtCustomerCode);
        txtEmpCode              = findViewById(R.id.txtEmpCode);

        // Value
        edCustomerCode          = findViewById(R.id.edCustomerCode);
        edPLNumber              = findViewById(R.id.edPLNumber);
        edDeliveryAddressCode   = findViewById(R.id.edDeliveryAddressCode);
        edDeliveryAddressName   = findViewById(R.id.edDeliveryAddressName);
        edEmpCode               = findViewById(R.id.edEmpCode);

        appPreferences          = new AppPreferences(PickingMSActivity.this);
        utils                   = new Utils(PickingMSActivity.this, appPreferences);
        pickingMSPresenter      = new PickingMSPresenter(PickingMSActivity.this, appPreferences, utils);

        txtCustomerCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(edCustomerCode.getText()))
                {
                    edCustomerCode.setText("");
                    edPLNumber.setText("");
                    edDeliveryAddressName.setText("");
                    edDeliveryAddressCode.setText("");

                    txtCustomerCode.setTextColor(Color.parseColor("#000000"));

                    edCustomerCode.requestFocus();
                }
            }
        });

        edCustomerCode.addTextChangedListener(new TextWatcher() {
            boolean ignoreChange = false;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String qrCodeValue1 = edCustomerCode.getText().toString();
                if(qrCodeValue1.length() == 126)
                {
                    String customerCode = qrCodeValue1.substring(0, 6).trim();
                    String addressName  = qrCodeValue1.substring(6, 66).trim();
                    String PLNumber     = qrCodeValue1.substring(66, 96).trim();
                    String addressCode  = qrCodeValue1.substring(96, 126).trim();

                    pickingMSPresenter.check_Exists_Data_Handy_MS(PLNumber, new PickingMSView() {
                        @Override
                        public void isPickingMSNull(boolean isPickingNull) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (isPickingNull) {
                                        if (!ignoreChange) {
                                            ignoreChange = true;

                                            txtCustomerCode.setTextColor(Color.parseColor("#FF0000"));

                                            edCustomerCode.setText(customerCode);
                                            edPLNumber.setText(PLNumber);
                                            edDeliveryAddressName.setText(addressName);
                                            edDeliveryAddressCode.setText(addressCode);

                                            edEmpCode.requestFocus();

                                            ignoreChange = false;
                                        }
                                    } else {
                                        String message = "Packing List: " + PLNumber + " đã tồn tại trong hệ thống";
                                        utils.displayDialogNotification("Cảnh báo", message);
                                        edCustomerCode.setText("");
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });

        txtEmpCode.setOnClickListener   (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(edEmpCode.getText()))
                {
                    edEmpCode.setText("");
                    txtEmpCode.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        edEmpCode.addTextChangedListener(new TextWatcher() {
            boolean ignoreChange = false;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!ignoreChange)
                {
                    ignoreChange = true;

                    if(edEmpCode.getText().toString().length() == 20) {
                        edEmpCode.setText(edEmpCode.getText().toString().trim());
                        txtEmpCode.setTextColor(Color.parseColor("#FF0000"));

                        btnNext.callOnClick();
                    } else {
                        String title = "Lỗi";
                        String messageError = "Scan mã số nhân viên không đúng định dạng";
                        utils.displayDialogNotification(title, messageError);
                    }

                    ignoreChange = false;
                }

            }
        });

        btnNext     = findViewById(R.id.btnNext);
        btnCancel   = findViewById(R.id.btnCancel);

        // Disable keypad when click
        edCustomerCode.setShowSoftInputOnFocus(false);
        edEmpCode.setShowSoftInputOnFocus(false);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkError())
                {
                    PickingDatabaseHelper myDB = new PickingDatabaseHelper(PickingMSActivity.this);
                    String PLNumber = edPLNumber.getText().toString().trim();

                    // Get data
                    handy_ms = new handy_ms(
                            edCustomerCode.getText().toString().trim(),
                            PLNumber,
                            edDeliveryAddressCode.getText().toString().trim(),
                            edDeliveryAddressName.getText().toString().trim(),
                            edEmpCode.getText().toString().trim(),
                            String.valueOf(Calendar.getInstance().getTime()),   // CREATE_DATE
                            edEmpCode.getText().toString().trim(),              // CREATE_BY
                            null,                                               // EDIT_DATE
                            null,                                               // EDIT_BY
                            0,                                                  // STATUS
                            null,                                               // COLUMN1
                            null,                                               // COLUMN2
                            null,                                               // COLUMN3
                            null,                                               // COLUMN4
                            null                                                // COLUMN5
                    );

                    // Delete all data before add new
                    myDB.deleteData(TABLE_HANDY_MS);

                    long result = myDB.addHandyMS(TABLE_HANDY_MS ,handy_ms);

                    if(result == -1)
                    {
                        String title = "Lỗi";
                        String message = "Phát sinh lỗi";
                        utils.displayDialogNotification(title, message);
                    } else {
                        Intent intent = new Intent(PickingMSActivity.this, PickingDetailActivity.class);
                        intent.putExtra("handyMS", handy_ms);
                        startActivity(intent);
                    }
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickingMSActivity.this, MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // Set focus 1 edittext
        edCustomerCode.requestFocus();
    }

    private boolean checkError() {
        String title = "Lỗi";
        String message = "";
        if(String.valueOf(edCustomerCode.getText()).length() == 0)
        {
            message = "Chưa nhập mã khách hàng";
            utils.displayDialogNotification(title, message);
            edCustomerCode.requestFocus();
            return false;
        }

        if(String.valueOf(edEmpCode.getText()).length() == 0)
        {
            message = "Chưa nhập mã nhân viên";
            utils.displayDialogNotification(title, message);
            edEmpCode.requestFocus();
            return false;
        }

        return true;
    }
}