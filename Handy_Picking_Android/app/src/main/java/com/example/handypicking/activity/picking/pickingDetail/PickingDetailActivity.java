package com.example.handypicking.activity.picking.pickingDetail;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Bundle;
import android.app.Dialog;
import android.text.TextUtils;
import android.graphics.Color;
import android.content.Intent;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.view.KeyEvent;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handypicking.R;
import com.example.handypicking.Utils.Utils;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.model.handy_detail;
import com.example.handypicking.activity.menu.MenuActivity;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.preferences.AppPreferences;

import java.util.List;
import android.util.Log;
import java.util.Calendar;
import java.util.ArrayList;
import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickingDetailActivity extends AppCompatActivity{
    TextView txtLabelPalletNo, txtPalletNo, txtLabelItem_A, txtItem, txtQuantity, txtSaleOrder, txtLotID;
    TextView labelEmployeeCode, txtEmployeeCode , txtTotalQRCodeScan,txtQuantityDetailTotal;
    Button  btnCancel, /*btnPost,*/ btnAdd;
    RecyclerView recyclerView;
    PickingDatabaseHelper myDB;
    PickingDetailAdapter.ItemClickListener itemClickListener;
    final String TABLE_HANDY_MS = "handy_ms";
    final String TABLE_HANDY_DETAIL = "handy_detail";
    final String TABLE_HANDY_MS_LOCAL = "handy_ms_local";
    final String TABLE_HANDY_DETAIL_LOCAL = "handy_detail_local";
    private PickingDetailAdapter pickingDetailAdapter;
    private PickingDetailPresenter pickingDetailPresenter;
    private AppPreferences appPreferences;
    private Utils utils;
    private handy_ms handyMS;
    private List<handy_detail> list_handyDetail = new ArrayList<>();
    private static final String TAG = "PickingDetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking_detail);

        txtLabelPalletNo        = findViewById(R.id.txtLabelPalletNo);
        txtPalletNo             = findViewById(R.id.txtPalletNo);
        txtLabelItem_A          = findViewById(R.id.txtLabelItem_A);
        txtItem                 = findViewById(R.id.txtItem);
        txtQuantity             = findViewById(R.id.txtQuantity);
        txtSaleOrder            = findViewById(R.id.txtSaleOrder);
        txtLotID                = findViewById(R.id.txtLotID);
        labelEmployeeCode       = findViewById(R.id.labelEmployeeCode);
        txtEmployeeCode         = findViewById(R.id.txtEmployeeCode);
        txtTotalQRCodeScan      = findViewById(R.id.dataServer_txtTotalQRCodeScan);
        txtQuantityDetailTotal  = findViewById(R.id.txtQuantityDetailTotal);

        recyclerView            = findViewById(R.id.recyclerView);

        /*btnPost                 = findViewById(R.id.btnPost);*/
        btnCancel               = findViewById(R.id.btnCancel);
        btnAdd                  = findViewById(R.id.btnAdd);

        appPreferences          = new AppPreferences(PickingDetailActivity.this);
        utils                   = new Utils(PickingDetailActivity.this, appPreferences);

        myDB                    = new PickingDatabaseHelper(PickingDetailActivity.this);
        pickingDetailPresenter  = new PickingDetailPresenter(appPreferences, utils);

        Intent intent = getIntent();

        if(intent != null)
        {
            handyMS = (handy_ms) intent.getSerializableExtra("handyMS");

            setTitle(handyMS.getPICKING_LIST_NO());
        }

        itemClickListener = ((view, position) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Delete data in local SQLite
                    if(myDB.deleteHandyDetailByCode(list_handyDetail.get(position)) == 1) {
                        int quantityTotal           = Integer.parseInt((txtQuantityDetailTotal.getText().toString()).replace(",", ""));
                        int quantityDetail          = list_handyDetail.get(position).getQTY_TOTAL();
                        int quantityTotalQRCodeScan = Integer.parseInt(txtTotalQRCodeScan.getText().toString()) - 1;
                        DecimalFormat formatter     = new DecimalFormat("###,###,###");

                        Log.d(TAG, "1.Total: " + quantityTotal + ".Detail: " + quantityDetail);
                        txtQuantityDetailTotal.setText(formatter.format(quantityTotal - quantityDetail));
                        txtTotalQRCodeScan.setText(formatter.format(quantityTotalQRCodeScan));

                        list_handyDetail.remove(position);

                        // Refresh recyclerView
                        pickingDetailAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(PickingDetailActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });

            AlertDialog alert = builder.create();
            alert.setTitle("Xác nhận");
            alert.setMessage("Bạn muốn xóa:\nItem: '"
                    + list_handyDetail.get(position).getCUS_ITEM_CODE()
                    + "'\nSeries: '"
                    + list_handyDetail.get(position).getSERIES()
                    + "'\nQuantity: '"
                    + list_handyDetail.get(position).getQTY_TOTAL()
                    + "'");

            alert.show();
        });

        txtLabelPalletNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(txtPalletNo.getText()))
                {
                    txtPalletNo.setText("");
                    txtLabelPalletNo.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        txtLabelItem_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(txtItem.getText()))
                {
                    txtLabelItem_A.setTextColor(Color.parseColor("#000000"));
                    txtItem.setText("");
                    txtSaleOrder.setText("");
                    txtLotID.setText("");
                    txtQuantity.setText("");
                }
            }
        });

        labelEmployeeCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(txtEmployeeCode.getText()))
                {
                    labelEmployeeCode.setTextColor(Color.parseColor("#000000"));
                    txtEmployeeCode.setText("");
                }
            }
        });

        /*btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.checkApiConnection(appPreferences.getApiSetting(), new Utils.validateApiConnection() {
                    @Override
                    public void isApiConnectionValid(boolean isConnected) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(isConnected) {
                                    if(checkError_Post())
                                    {
                                        try {
                                            //Send data picking to Web API
                                            List<handy_ms> list_handyMS = new ArrayList<>();
                                            list_handyMS.add(handyMS);
                                            utils.onSendDataHandy(list_handyMS, list_handyDetail);
                                        } catch (Exception ex) {
                                            Log.d(TAG, ex.getMessage());
                                        }
                                    }
                                } else {
                                    // Display dialog confirm save data local
                                    final Dialog dialog = new Dialog(PickingDetailActivity.this);
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.setContentView(R.layout.dialog_confirm_save_data_local);

                                    Button btnCancel, btnSaveLocal;

                                    btnCancel       = dialog.findViewById(R.id.btnCancel);
                                    btnSaveLocal    = dialog.findViewById(R.id.btnSaveLocal);

                                    btnCancel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });

                                    btnSaveLocal.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // Save to the local SQLite table and return to main menu
                                            // Save the data to the temporary table
                                            myDB.addHandyMS(TABLE_HANDY_MS_LOCAL, handyMS);
                                            myDB.addListHandyDetails(TABLE_HANDY_DETAIL_LOCAL, list_handyDetail);

                                            // Delete data in main table
                                            myDB.deleteData(TABLE_HANDY_MS);
                                            myDB.deleteData(TABLE_HANDY_DETAIL);

                                            // Clear list data
                                            list_handyDetail.clear();

                                            // Refresh recyclerView
                                            pickingDetailAdapter.notifyDataSetChanged();

                                            txtItem.setText("");
                                            txtQuantity.setText(String.valueOf(0));
                                            txtSaleOrder.setText("");
                                            txtLotID.setText("");
                                            txtTotalQRCodeScan.setText(String.valueOf(0));
                                            txtQuantityDetailTotal.setText(String.valueOf(0));

                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        });
                    }
                });
            }
        });*/

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickingDetailActivity.this, MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh_layout();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pickingDetailAdapter = new PickingDetailAdapter(this, list_handyDetail, itemClickListener);
        recyclerView.setAdapter(pickingDetailAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    /***
     * When press Scan button
     * @param event The key event.
     *
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        if (event.getCharacters() != null && !event.getCharacters().isEmpty())
        {
            // Check if palletNo is empty
            if(txtPalletNo.getText().equals("")) {
                String palletNo = event.getCharacters();
                // Check format pallet No scan
                if(palletNo.length() == 20) {
                    txtPalletNo.setText(palletNo.trim().replaceAll("\\s+", ""));
                    Toast.makeText(this, "\'" + txtPalletNo.getText() + "\'" + "-" + txtPalletNo.getText().length(),Toast.LENGTH_SHORT).show();
                    txtLabelPalletNo.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    String title = "Lỗi";
                    String messageError = "Scan số pallet không đúng định dạng";
                    utils.displayDialogNotification(title, messageError);
                    return false;
                }
            }

            if(txtItem.getText().equals("")) // Check if Item code is empty
            {
                String Item = event.getCharacters();

                if(Item.length() == 61)
                {
                    String item         = Item.substring(0,20).trim();
                    String saleOrder    = Item.substring(20, 40).trim();
                    String lotID        = Item.substring(40, 53).trim();
                    String quantity     = Item.substring(53, 61).trim();

                    txtLabelItem_A.setTextColor(Color.parseColor("#FF0000"));

                    txtItem.setText(item);
                    txtSaleOrder.setText(saleOrder);
                    txtLotID.setText(lotID);
                    txtQuantity.setText(quantity);
                } else {
                    String title = "Lỗi";
                    String message = "Scan thông tin Item không đúng định dạng";
                    utils.displayDialogNotification(title, message);
                    return false;
                }
            }

            if(txtEmployeeCode.getText().equals("")) // Check if Employee code is empty
            {
                String employeeCode = event.getCharacters();
                // Check format Employee Code scan
                if(employeeCode.length() == 20) {
                    txtEmployeeCode.setText(employeeCode.trim());
                    labelEmployeeCode.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    String title = "Lỗi";
                    String messageError = "Scan mã số nhân viên không đúng định dạng";
                    utils.displayDialogNotification(title, messageError);
                    return false;
                }
            }

            if(event.getCharacters().length() == 114)
            {
                handy_detail handyDetail = SplitString(event.getCharacters());
                handyDetail.setPALLET_NO(txtPalletNo.getText().toString());

                // Check exists item in database SQL Server
                pickingDetailPresenter.check_Exists_Data_Handy_Detail(handyDetail.getSERIES(), new PickingDetailView() {
                    @Override
                    public void isPickingDetailNull(boolean isPickingDetailNull) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(!isPickingDetailNull)
                                {
                                    String title = "Lỗi";
                                    String message = "Item: " + handyDetail.getTVC_ITEM_CODE() + "\nSeries: " + handyDetail.getSERIES() + "\nđã tồn tại trên SQL Server.";
                                    utils.displayDialogNotification(title, message);
                                } else {
                                    if(check_Error_Picking_Detail(handyDetail, list_handyDetail))
                                    {
                                        if(handyDetail.getQTY_TOTAL().equals(0))
                                        {
                                            // Call dialog Input quantity
                                            final Dialog dialog = new Dialog(PickingDetailActivity.this);
                                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            dialog.setContentView(R.layout.dialog_set_quantity);

                                            EditText edQuantity;
                                            Button btnOk, btnCancel;

                                            edQuantity  = dialog.findViewById(R.id.edQuantity);
                                            btnOk       = dialog.findViewById(R.id.btnOk);
                                            btnCancel   = dialog.findViewById(R.id.btnCancel);

                                            btnOk.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    int quantityDetail          = Integer.parseInt(edQuantity.getText().toString());
                                                    int quantityTotal           = Integer.parseInt((txtQuantityDetailTotal.getText().toString()).replace(",", ""));
                                                    int quantityTotalQRCodeScan = Integer.parseInt(txtTotalQRCodeScan.getText().toString());

                                                    if (quantityDetail > 0) {
                                                        handyDetail.setQTY_TOTAL(quantityDetail);
                                                        quantityTotalQRCodeScan++;

                                                        list_handyDetail.add(handyDetail);
                                                        myDB.addHandyDetail(TABLE_HANDY_DETAIL, handyDetail);

                                                        DecimalFormat formatter = new DecimalFormat("###,###,###");
                                                        /*Log.d(TAG, "2.Total: " + quantityTotal + ".Detail: " + quantityDetail);*/
                                                        txtQuantityDetailTotal.setText(formatter.format(quantityTotal + quantityDetail));
                                                        txtTotalQRCodeScan.setText(formatter.format(quantityTotalQRCodeScan));

                                                        // Refresh recyclerView
                                                        pickingDetailAdapter.notifyDataSetChanged();

                                                        dialog.dismiss();
                                                    } else {
                                                        Toast.makeText(PickingDetailActivity.this, "Hãy nhập số lượng lớn hơn 0", Toast.LENGTH_SHORT).show();
                                                        edQuantity.setText("");
                                                        edQuantity.requestFocus();
                                                    }
                                                }
                                            });

                                            btnCancel.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v)
                                                {
                                                    dialog.dismiss();
                                                }
                                            });

                                            edQuantity.requestFocus();

                                            dialog.show();
                                        } else {
                                            try {
                                                list_handyDetail.add(handyDetail);
                                                myDB.addHandyDetail(TABLE_HANDY_DETAIL, handyDetail);

                                                int quantityDetail          = handyDetail.getQTY_TOTAL();
                                                int quantityTotal           = Integer.parseInt((txtQuantityDetailTotal.getText().toString()).replace(",", ""));
                                                int quantityTotalQRCodeScan = Integer.parseInt(txtTotalQRCodeScan.getText().toString()) + 1;

                                                DecimalFormat formatter = new DecimalFormat("###,###,###");
                                                Log.d(TAG, "3.Total: " + quantityTotal + ".Detail: " + quantityDetail);
                                                txtQuantityDetailTotal.setText(formatter.format(quantityTotal + quantityDetail));
                                                txtTotalQRCodeScan.setText(formatter.format(quantityTotalQRCodeScan));

                                                // Refresh recyclerView
                                                pickingDetailAdapter.notifyDataSetChanged();
                                            } catch(Exception e)
                                            {
                                                Log.d(TAG, e.getMessage());
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
            } else {
                String title = "Lỗi";
                String message = "Scan thông tin Handy Picking Detail không đúng định dạng";
                utils.displayDialogNotification(title, message);
            }
        }

        return super.dispatchKeyEvent(event);
    }

    private boolean check_Error_Picking_Detail(handy_detail handyDetail, List<handy_detail> list_handyDetails){
        String title = "Lỗi";
        String message ="";

        if(handyDetail.getCUS_ITEM_CODE().equals(""))
        {
            message = "Tem thiếu thông tin Customer Item Code";
            utils.displayDialogNotification(title, message);
            return false;
        }

        if(handyDetail.getTVC_ITEM_CODE().equals(""))
        {
            message = "Tem thiếu thông tin TVC Item Code";
            utils.displayDialogNotification(title, message);
            return false;
        }

        // Check info item code detail with header
        if(!handyDetail.getTVC_ITEM_CODE().equals(txtItem.getText().toString()))
        {
            message = "Item Header khác Item Detail"
                    + "\nHeader: "
                    + txtItem.getText().toString()
                    + "\nDetail: "
                    + handyDetail.getTVC_ITEM_CODE();

            utils.displayDialogNotification(title,message);
            return false;
        }

        // Check exists item in list
        if(checkExistData(handyDetail, list_handyDetail))
        {
            message = "Item: "
                    + handyDetail.getTVC_ITEM_CODE()
                    + "\nSeries: "
                    + handyDetail.getSERIES()
                    + " đã có trong danh sách";
            utils.displayDialogNotification(title, message);
            return false;
        }

        return true;
    }

    private boolean checkError_Post()
    {
        String title = "Lỗi";
        String message = "";

        if(txtPalletNo.getText().toString().trim().equals(""))
        {
            message = "Chưa nhập PalletNo";
            utils.displayDialogNotification(title, message);
            return false;
        }

        if(txtItem.getText().toString().trim().equals(""))
        {
            message = "Chưa nhập Item";
            utils.displayDialogNotification(title, message);
            return false;
        }

        if(txtEmployeeCode.getText().toString().trim().equals(""))
        {
            message = "Chưa nhập Mã nhân viên";
            utils.displayDialogNotification(title, message);
            return false;
        }

        if(list_handyDetail.size() == 0)
        {
            message = "Chưa nhập Picking Detail";
            utils.displayDialogNotification(title, message);
            return false;
        }

        return true;
    }

    private handy_detail SplitString(String qrCodeValue)
    {
        handy_detail handyDetail = new handy_detail();

        String customerItemCode = qrCodeValue.substring(0,18).trim();
        String lineCode         = qrCodeValue.substring(18, 26).trim();
        Integer quantity        = Integer.parseInt(qrCodeValue.substring(26, 34).trim().replace(".00",""));
        String tvcItemCode      = qrCodeValue.substring(34, 52).trim();
        String series           = qrCodeValue.substring(52, 70).trim();
        String lotNo            = qrCodeValue.substring(103, 114).trim();

        Log.d(TAG, "customerItemCode: " + customerItemCode + ".TvcItemCode: " + tvcItemCode + ".Quantity: " + quantity + ".Series: " + series +  ".LotNo: " + lotNo);

        handyDetail = new handy_detail(
                handyMS.getPICKING_LIST_NO(),
                null,
                txtSaleOrder.getText().toString().trim(),           // Item header
                txtItem.getText().toString().trim(),                // Item header
                txtLotID.getText().toString().trim(),               // Item header
                Integer.parseInt(txtQuantity.getText().toString()), // Item header
                txtPalletNo.getText().toString().trim(),
                series,
                customerItemCode,
                tvcItemCode,
                null,
                0,
                0,
                quantity,
                Float.parseFloat("0"),
                Float.parseFloat("0"),
                Float.parseFloat("0"),
                lotNo,
                Calendar.getInstance().getTime().toString(),
                txtEmployeeCode.getText().toString().trim(),         // Item header
                null,
                null,
                0,
                null,
                null,
                null,
                null,
                null
        );

        return handyDetail;
    }

    private boolean checkExistData(handy_detail handyDetail, List<handy_detail> listHandyDetail)
    {
        for(handy_detail item : listHandyDetail)
        {
            if(item.getCUS_ITEM_CODE().equals(handyDetail.getCUS_ITEM_CODE())
                && item.getSERIES().equals(handyDetail.getSERIES()))
            {
                return true;
            }
        }
        return false;
    }

    private void refresh_layout()
    {
        txtPalletNo.setText("");
        txtLabelPalletNo.setTextColor(Color.parseColor("#000000"));

        txtItem.setText("");
        txtSaleOrder.setText("");
        txtLotID.setText("");
        txtQuantity.setText("");
        txtLabelItem_A.setTextColor(Color.parseColor("#000000"));

        txtEmployeeCode.setText("");
        labelEmployeeCode.setTextColor(Color.parseColor("#000000"));
    }
}