package com.example.handypicking.activity.picking.pickingMS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handypicking.R;
import com.example.handypicking.activity.menu.MenuActivity;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.activity.picking.pickingDetail.PickingDetailActivity;

import java.util.Calendar;

public class PickingMSActivity extends AppCompatActivity {
    Button btnNext, btnCancel;
    EditText edCustomerCode, edPLNumber, edDeliveryAddress, edItem, edQuantity, edSaleOrder, edLotID, edEmpCode;
    TextView txtCustomerCode, txtSaleOrder, txtEmpCode ;
    private handy_ms handy_ms;
    final String TABLE_HANDY_MS = "handy_ms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking_ms);

        // Label
        txtCustomerCode = findViewById(R.id.txtCustomerCode);
        txtSaleOrder = findViewById(R.id.txtSaleOrder);
        txtEmpCode = findViewById(R.id.txtEmpCode);

        // Value
        edCustomerCode = findViewById(R.id.edCustomerCode);
        edPLNumber = findViewById(R.id.edPLNumber);
        edDeliveryAddress = findViewById(R.id.edDeliveryAddress);
        edSaleOrder = findViewById(R.id.edSaleOrder);
        edItem = findViewById(R.id.edItem);
        edLotID = findViewById(R.id.edLotID);
        edQuantity = findViewById(R.id.edQuantity);
        edEmpCode = findViewById(R.id.edEmpCode);

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
                    String address = qrCodeValue1.substring(6, 66).trim();
                    String PLNumber = qrCodeValue1.substring(66, 96).trim();
                    if(!ignoreChange)
                    {
                        ignoreChange = true;

                        txtCustomerCode.setTextColor(Color.parseColor("#FF0000"));

                        edCustomerCode.setText(customerCode);
                        edPLNumber.setText(PLNumber);
                        edDeliveryAddress.setText(address);

                        edSaleOrder.requestFocus();
                        ignoreChange = false;
                    }
                }
            }
        });

        txtSaleOrder.setOnClickListener(new View.OnClickListener() {
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
        });

        edSaleOrder.addTextChangedListener(new TextWatcher() {
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
        });

        txtEmpCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(edEmpCode.getText()))
                {
                    edEmpCode.setText("");

                    txtEmpCode.setTextColor(Color.parseColor("#000000"));

                    edEmpCode.requestFocus();
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
                String qrCodeValue3 = edEmpCode.getText().toString();
                if(qrCodeValue3.length() == 20)
                {
                    if(!ignoreChange)
                    {
                        ignoreChange = true;

                        txtEmpCode.setTextColor(Color.parseColor("#FF0000"));

                        edEmpCode.setText(qrCodeValue3.trim());

                        ignoreChange = false;
                    }
                }
            }
        });

        btnNext = findViewById(R.id.btnNext);
        btnCancel = findViewById(R.id.btnCancel);

        // Disable keypad when click
        edCustomerCode.setShowSoftInputOnFocus(false);
        edSaleOrder.setShowSoftInputOnFocus(false);
        edEmpCode.setShowSoftInputOnFocus(false);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkError())
                {
                    PickingDatabaseHelper myDB = new PickingDatabaseHelper(PickingMSActivity.this);
                    String PLNumber = edPLNumber.getText().toString().trim();
                    String item = edItem.getText().toString().trim();
                    Integer quantity = Integer.valueOf(edQuantity.getText().toString().trim());

                    // Get data
                    handy_ms = new handy_ms(
                            edCustomerCode.getText().toString().trim(),
                            PLNumber,
                            edDeliveryAddress.getText().toString().trim(),
                            edSaleOrder.getText().toString().trim(),
                            item,
                            edLotID.getText().toString().trim(),
                            quantity,
                            edEmpCode.getText().toString().trim(),
                            null,
                            String.valueOf(Calendar.getInstance().getTime()),   // CREATE_DATE
                            edEmpCode.getText().toString().trim(),              // CREATE
                            null,                                               // EDIT_DATE
                            null,                                               // EDIT_BY
                            null,                                               // COLUMN1
                            null,                                               // COLUMN2
                            null,                                               // COLUMN3
                            null,                                               // COLUMN4
                            null                                                // COLUMN5
                    );

                    // Delete all data before add new
                    myDB.deleteData(TABLE_HANDY_MS);

                    long result = myDB.addHandyMS(handy_ms);

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

        if(edSaleOrder.getText().toString().matches(""))
        {
            Toast.makeText(PickingMSActivity.this, "Input Sale Order", Toast.LENGTH_SHORT).show();
            edSaleOrder.requestFocus();
            return false;
        }

        if(edEmpCode.getText().toString().matches(""))
        {
            Toast.makeText(PickingMSActivity.this, "Input Employee Code", Toast.LENGTH_SHORT).show();
            edEmpCode.requestFocus();
            return false;
        }

        return true;
    }
}