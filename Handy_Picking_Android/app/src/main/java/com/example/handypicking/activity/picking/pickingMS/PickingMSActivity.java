package com.example.handypicking.activity.picking.pickingMS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handypicking.R;
import com.example.handypicking.Utils.Utils;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.activity.menu.MenuActivity;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.activity.picking.pickingDetail.PickingDetailActivity;

import java.util.Calendar;

public class PickingMSActivity extends AppCompatActivity {
    Button btnNext, btnCancel;
    TextView txtCustomerCode, txtEmpCode ;
    EditText edCustomerCode, edPLNumber, edDeliveryAddress, edEmpCode;
    PickingMSPresenter pickingMSPresenter;
    private handy_ms handy_ms;
    final String TABLE_HANDY_MS = "handy_ms";
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking_ms);

        // Label
        txtCustomerCode     = findViewById(R.id.txtCustomerCode);
        txtEmpCode          = findViewById(R.id.txtEmpCode);

        // Value
        edCustomerCode      = findViewById(R.id.edCustomerCode);
        edPLNumber          = findViewById(R.id.edPLNumber);
        edDeliveryAddress   = findViewById(R.id.edDeliveryAddress);
        edEmpCode           = findViewById(R.id.edEmpCode);

        pickingMSPresenter  = new PickingMSPresenter();
        utils               = new Utils(this);

        txtCustomerCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(edCustomerCode.getText()))
                {
                    edCustomerCode.setText("");
                    edPLNumber.setText("");
                    edDeliveryAddress.setText("");

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
                if(qrCodeValue1.length() == 96)
                {
                    String customerCode = qrCodeValue1.substring(0, 6).trim();
                    String address      = qrCodeValue1.substring(6, 66).trim();
                    String PLNumber     = qrCodeValue1.substring(66, 96).trim();

                    pickingMSPresenter.check_Exists_Data_Handy_MS(PLNumber, new PickingMSView() {
                        @Override
                        public void isPickingMSNull(boolean isPickingNull) {
                            if(isPickingNull)
                            {
                                if(!ignoreChange)
                                {
                                    ignoreChange = true;

                                    txtCustomerCode.setTextColor(Color.parseColor("#FF0000"));

                                    edCustomerCode.setText(customerCode);
                                    edPLNumber.setText(PLNumber);
                                    edDeliveryAddress.setText(address);

                                    edEmpCode.requestFocus();

                                    ignoreChange = false;
                                }
                            } else {
                                String message = "Packing List: " + PLNumber + " đã tồn tại trong hệ thống";
                                utils.displayDialogNotification("Cảnh báo",message);
                                edCustomerCode.setText("");
                            }
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

                    txtEmpCode.setTextColor(Color.parseColor("#FF0000"));
                    edEmpCode.setText(edEmpCode.getText().toString().trim());

                    ignoreChange = false;
                }

            }
        });

/*        txtSaleOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(edSaleOrder.getText()))
                {
                    edSaleOrder.setText("");
                    edItem.setText("");
                    edLotID.setText("");
                    edQuantity.setText("");

                    txtSaleOrder.setTextColor(Color.parseColor("#000000"));

                    edSaleOrder.requestFocus();
                }
            }
        });*/

        /*edSaleOrder.addTextChangedListener(new TextWatcher() {
            boolean ignoreChange = false;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String qrCodeValue2 = edSaleOrder.getText().toString();
                if(qrCodeValue2.length() == 61)
                {
                    String item = qrCodeValue2.substring(0,20).trim();
                    String saleOrder = qrCodeValue2.substring(20, 40).trim();
                    String lotID = qrCodeValue2.substring(40, 53).trim();
                    String quantity = qrCodeValue2.substring(53, 61).trim();

                    if(!ignoreChange)
                    {
                        ignoreChange = true;

                        txtSaleOrder.setTextColor(Color.parseColor("#FF0000"));

                        edItem.setText(item);
                        edSaleOrder.setText(saleOrder);
                        edLotID.setText(lotID);
                        edQuantity.setText(quantity);

                        edEmpCode.requestFocus();
                        ignoreChange = false;
                    }
                }
            }
        });*/

        btnNext     = findViewById(R.id.btnNext);
        btnCancel   = findViewById(R.id.btnCancel);

        // Disable keypad when click
        edCustomerCode.setShowSoftInputOnFocus(false);

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
                            edDeliveryAddress.getText().toString().trim(),
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
                        Toast.makeText(PickingMSActivity.this, "Failed", Toast.LENGTH_SHORT).show();
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
        if(String.valueOf(edCustomerCode.getText()).length() == 0)
        {
            Toast.makeText(PickingMSActivity.this, "Input Customer Code", Toast.LENGTH_SHORT).show();
            edCustomerCode.requestFocus();
            return false;
        }

        if(String.valueOf(edEmpCode.getText()).length() == 0)
        {
            Toast.makeText(PickingMSActivity.this, "Input Employee Code", Toast.LENGTH_SHORT).show();
            edEmpCode.requestFocus();
            return false;
        }

        return true;
    }
}