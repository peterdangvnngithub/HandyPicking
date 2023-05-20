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
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handypicking.R;
import com.example.handypicking.Utils.Utils;
import com.example.handypicking.Utils.UtilsView;
import com.example.handypicking.activity.menu.MenuActivity;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.model.handy_detail;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class PickingDetailActivity extends AppCompatActivity{
    TextView txtLabelItem_A, txtItem, txtQuantity, txtLabelPalletNo, txtSaleOrder, txtLotID, txtPalletNo, txtTotalQRCodeScan,txtQuantityDetailTotal;
    Button  btnPost, btnCancel;
    RecyclerView recyclerView;
    PickingDatabaseHelper myDB;
    PickingDetailAdapter.ItemClickListener itemClickListener;
    final String TABLE_HANDY_MS = "handy_ms";
    final String TABLE_HANDY_DETAIL = "handy_detail";
    final String TABLE_HANDY_MS_LOCAL = "handy_ms_local";
    final String TABLE_HANDY_DETAIL_LOCAL = "handy_detail_local";
    private PickingDetailAdapter pickingDetailAdapter;
    private PickingDetailPresenter pickingDetailPresenter = new PickingDetailPresenter();

    private Utils utils;
    handy_ms handyMS;
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
        txtTotalQRCodeScan      = findViewById(R.id.txtTotalQRCodeScan);
        txtQuantityDetailTotal  = findViewById(R.id.txtQuantityDetailTotal);

        recyclerView            = findViewById(R.id.recyclerView);

        btnPost                 = findViewById(R.id.btnPost);
        btnCancel               = findViewById(R.id.btnCancel);

        myDB                    = new PickingDatabaseHelper(PickingDetailActivity.this);

        utils                   = new Utils(PickingDetailActivity.this);

        if(getIntent().getExtras() != null) {
            handyMS = (handy_ms) getIntent().getSerializableExtra("handyMS");
            //TODO
            /*txtItem.setText(handyMS.getITEM_CODE());
            txtQuantity.setText(String.valueOf(handyMS.getQUANTITY()));*/
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

                        DecimalFormat formatter = new DecimalFormat("###,###,###");
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
            alert.setTitle("Confirm");
            alert.setMessage("Bạn muốn xóa:\nItem: '"
                    + list_handyDetail.get(position).getCUS_ITEM_CODE()
                    + "'\nSeries: '"
                    + list_handyDetail.get(position).getSERIES()
                    + "'\nQuantity: '"
                    + list_handyDetail.get(position).getQUANTITY()
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

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.isServerRunning(new UtilsView() {
                    @Override
                    public void onServerStatusReceived(boolean isServerRunning) {
                        // Do something with the result
                        if(isServerRunning) {
                            //TODO: Test web API is running
                            Toast.makeText(PickingDetailActivity.this, "Result On", Toast.LENGTH_SHORT).show();

                            if(checkError())
                            {
                                try {
                                    //Send data picking to Web API
                                    List<handy_ms> list_handyMS = new ArrayList<>();
                                    list_handyMS.add(handyMS);
                                    utils.onSendDataHandyMS(list_handyMS);
                                    utils.onSendDataHandyDetail(list_handyDetail);

                                    //TODO: Ask to end post or Post new pallet. Display dialog confirm
                                    final Dialog dialog = new Dialog(PickingDetailActivity.this);
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.setContentView(R.layout.dialog_confirm_add_new_pallet);

                                    TextView txtMessage;
                                    Button btnNextPicking, btnCancel;

                                    txtMessage      = dialog.findViewById(R.id.txtMessage);
                                    btnNextPicking  = dialog.findViewById(R.id.btnNextPicking);
                                    btnCancel       = dialog.findViewById(R.id.btnCancel);

                                    txtMessage.setText("Lưu PL: '" + handyMS.getPICKING_LIST_NO() + "' thành công!\nBấm 'Next' để tiếp tục picking.\nBấm Cancel để về menu.");

                                    btnNextPicking.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // Clear local data
                                            myDB.deleteData(TABLE_HANDY_MS);
                                            myDB.deleteData(TABLE_HANDY_DETAIL);
                                            list_handyDetail.clear();

                                            // Refresh recyclerView
                                            pickingDetailAdapter.notifyDataSetChanged();

                                            /*txtPalletNo.setText("");*/
                                            txtQuantityDetailTotal.setText("0");
                                            txtTotalQRCodeScan.setText("0");

                                            // Clear item
                                            txtLabelItem_A.performClick();

                                            dialog.dismiss();
                                        }
                                    });

                                    btnCancel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();

                                            // Clear local data
                                            myDB.deleteData(TABLE_HANDY_MS);
                                            myDB.deleteData(TABLE_HANDY_DETAIL);

                                            // Back to packing MS
                                            Intent intent = new Intent(PickingDetailActivity.this, MenuActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }
                                    });

                                    dialog.show();
                                } catch (Exception ex) {
                                    Log.d(TAG, ex.getMessage());
                                }
                            }
                        } else {
                            //TODO: Test web API not running
                            Toast.makeText(PickingDetailActivity.this, "Không thể kết nối đến server", Toast.LENGTH_SHORT).show();

                            //TODO: Display dialog confirm save data local
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

                                    // Clear local data
                                    myDB.deleteData(TABLE_HANDY_MS);
                                    myDB.deleteData(TABLE_HANDY_DETAIL);

                                    // Back to packing MS
                                    Intent intent = new Intent(PickingDetailActivity.this, MenuActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                            });

                            btnSaveLocal.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // TODO: Save to the local SQLite table and return to main menu
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

                                    txtPalletNo.setText("");
                                    txtQuantityDetailTotal.setText("0");

                                    dialog.dismiss();
                                }
                            });

                            dialog.show();
                        }
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickingDetailActivity.this, MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
                //TODO: Check format pallet No scan
                if(palletNo.length() == 20) {
                    txtPalletNo.setText(palletNo.trim());
                    txtLabelPalletNo.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    Toast.makeText(PickingDetailActivity.this, "Scan số pallet không đúng định dạng", Toast.LENGTH_SHORT).show();
                }
            } else if(txtItem.getText().equals("")) // Check if Itemcode is empty
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
                    Toast.makeText(PickingDetailActivity.this, "Scan thông tin Item không đúng định dạng", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(event.getCharacters().length() == 114)
                {
                    handy_detail handyDetail = SplitString(event.getCharacters());
                    handyDetail.setPALLET_NO(txtPalletNo.getText().toString());

                    if(check_Error_Picking_Detail(handyDetail, list_handyDetail))
                    {
                        if(handyDetail.getQUANTITY().equals(0))
                        {
                            //TODO: Call dialog Input quantity
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
                                        Log.d(TAG, "2.Total: " + quantityTotal + ".Detail: " + quantityDetail);
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

                    /*
                    else {
                        //TODO: Check info item code detail with header
                        if (!checkExistData(handyDetail, list_handyDetail)) {
                            handyDetail.setPALLET_NO(txtPalletNo.getText().toString());
                            list_handyDetail.add(handyDetail);

                            myDB.addHandyDetail(handyDetail);

                            int quantityTotal   = Integer.parseInt((txtQuantityDetailTotal.getText().toString()).replace(",", ""));
                            int quantityDetail  = handyDetail.getQUANTITY();

                            DecimalFormat formatter = new DecimalFormat("###,###,###");
                            txtQuantityDetailTotal.setText(formatter.format(quantityTotal + quantityDetail));

                            // Refresh recyclerView
                            pickingDetailAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(PickingDetailActivity.this, "Item đã có trong danh sách", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }*/
                } else {
                    String title = "Lỗi";
                    String message = "Scan thông tin Handy Picking Detail không đúng định dạng";
                    utils.displayDialogNotification(title, message);
                }
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

        //TODO: Check exists item in database SQL Server
        pickingDetailPresenter.check_Exists_Data_Handy_Detail(handyDetail.getSERIES(), new PickingDetailView() {
            @Override
            public void isPickingDetailNull(boolean isPickingDetailNull) {
                if(!isPickingDetailNull)
                {
                    String message = "Item: " + handyDetail.getTVC_ITEM_CODE() + "\nSeries: " + handyDetail.getSERIES() + " đã tồn tại trong hệ thống";
                    utils.displayDialogNotification(title,message);
                    txtItem.setText("");
                }
            }
        });

        //TODO: Check info item code detail with header
        /*if(!handyDetail.getTVC_ITEM_CODE().equals(txtItem.getText().toString()))
        {
            message = "Item Code Detail không trùng với Item Code Header."
                    + "\nHeader: "
                    + txtItem.getText().toString()
                    + "\nDetail: "
                    + handyDetail.getTVC_ITEM_CODE();

            utils.displayDialogNotification(title,message);
            return false;
        }*/

        //TODO: Check exists item in list
        if(checkExistData(handyDetail, list_handyDetail))
        {
            message = "Item" + handyDetail.getTVC_ITEM_CODE() + " đã có trong danh sách";
            utils.displayDialogNotification(title, message);
            return false;
        }

        return true;
    }

    private handy_detail SplitString(String qrCodeValue)
    {
        handy_detail handyDetail = new handy_detail();
        if(checkError())
        {
            String customerItemCode = qrCodeValue.substring(0,18).trim();
            String lineCode         = qrCodeValue.substring(18, 26).trim();
            Integer quantity        = Integer.parseInt(qrCodeValue.substring(26, 34).trim().replace(".00",""));
            String tvcItemCode      = qrCodeValue.substring(34, 52).trim();
            String series           = qrCodeValue.substring(52, 70).trim();

            Log.d(TAG, "customerItemCode: " + customerItemCode + ".TvcItemCode: " + tvcItemCode + ".Quantity: " + quantity + ".Series: " + series);

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
                    Float.parseFloat("0") ,
                    Float.parseFloat("0") ,
                    Float.parseFloat("0") ,
                    null,
                    Calendar.getInstance().getTime().toString(),
                    handyMS.getEMPLOYEE_CODE(),
                    null,
                    null,
                    0,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }

        return handyDetail;
    }

    private boolean checkError() {
        if(txtPalletNo.getText().toString().matches(""))
        {
            Toast.makeText(PickingDetailActivity.this, "Input Pallet No", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
}