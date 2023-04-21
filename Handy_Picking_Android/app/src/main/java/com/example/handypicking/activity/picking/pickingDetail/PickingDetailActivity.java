package com.example.handypicking.activity.picking.pickingDetail;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handypicking.R;
import com.example.handypicking.activity.menu.MenuActivity;
import com.example.handypicking.activity.picking.pickingMS.PickingMSActivity;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.model.handy_detail;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PickingDetailActivity extends AppCompatActivity {
    TextView txtItem, txtQuantity, txtLabelPalletNo,txtPalletNo, txtQuantityDetailTotal;
    Button btnSave, btnPost, btnCancel;
    RecyclerView recyclerView;
    handy_ms handyMS;
    PickingDatabaseHelper myDB;
    PickingDetailAdapter.ItemClickListener itemClickListener;
    final String TABLE_HANDY_MS = "handy_ms";
    final String TABLE_HANDY_DETAIL = "handy_detail";
    private String TAG = "PickingDetailActivityDeBug";
    private PickingDetailAdapter pickingDetailAdapter;
    private PickingDetailPresenter pickingDetailPresenter = new PickingDetailPresenter();
    private List<handy_detail> list_handyDetail = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking_detail);

        txtItem =findViewById(R.id.txtItem);
        txtQuantity =findViewById(R.id.txtQuantity);
        txtLabelPalletNo =findViewById(R.id.txtLabelPalletNo);
        txtQuantityDetailTotal = findViewById(R.id.txtQuantityDetailTotal);

        txtPalletNo = findViewById(R.id.txtPalletNo);

        recyclerView = findViewById(R.id.recyclerView);

        btnSave = findViewById(R.id.btnSave);
        btnPost = findViewById(R.id.btnPost);
        btnCancel = findViewById(R.id.btnCancel);

        myDB = new PickingDatabaseHelper(PickingDetailActivity.this);

        if(getIntent().getExtras() != null) {
            handyMS = (handy_ms) getIntent().getSerializableExtra("handyMS");
            txtItem.setText(handyMS.getITEM_CODE());
            txtQuantity.setText(String.valueOf(handyMS.getQUANTITY()));
        }

        itemClickListener = ((view, position) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Delete data in local SQLite
                    if(myDB.deleteHandyDetailByCode(list_handyDetail.get(position)) == 1) {
                        int quantityTotal = Integer.parseInt((txtQuantityDetailTotal.getText().toString()).replace(",", ""));
                        int quantityDetail = list_handyDetail.get(position).getQUANTITY();

                        DecimalFormat formatter = new DecimalFormat("###,###,###");
                        txtQuantityDetailTotal.setText(formatter.format(quantityTotal - quantityDetail));

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
            alert.setMessage("Bạn muốn xóa:\nItem: '" + list_handyDetail.get(position).getCUSTOMER_ITEM_CODE() + "'\nSeries: '" + list_handyDetail.get(position).getSERIES() + "'\nQuantity: '" + list_handyDetail.get(position).getQUANTITY() + "'");

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

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Move to table backup
                myDB.backUpData();

                // Clear local data
                myDB.deleteData(TABLE_HANDY_MS);
                myDB.deleteData(TABLE_HANDY_DETAIL);

                // Back to main menu
                Intent intent = new Intent(PickingDetailActivity.this, MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkError())
                {
                    try {
                        //Send data picking to Web API
                        List<handy_ms> list_handyMS = new ArrayList<>();
                        list_handyMS.add(handyMS);
                        pickingDetailPresenter.onSendDataHandyMS(list_handyMS);
                        pickingDetailPresenter.onSendDataHandyDetail(list_handyDetail);

                        //TODO: Ask to end post or Post new pallet. Display dialog confirm
                        final Dialog dialog = new Dialog(PickingDetailActivity.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_confirm_add_new_pallet);

                        TextView txtMessage;
                        Button btnNew, btnCancel;

                        txtMessage = dialog.findViewById(R.id.txtMessage);
                        btnNew = dialog.findViewById(R.id.btnNew);
                        btnCancel = dialog.findViewById(R.id.btnCancel);

                        txtMessage.setText("Lưu PackingList: '" + handyMS.getPICKING_LIST_NO() + "' thành công!\nBấm new để nhập Pallet khác, bấm Cancel để thoát.");

                        btnNew.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Clear local data
                                myDB.deleteData(TABLE_HANDY_MS);
                                myDB.deleteData(TABLE_HANDY_DETAIL);
                                list_handyDetail.clear();

                                // Refresh recyclerView
                                pickingDetailAdapter.notifyDataSetChanged();

                                txtPalletNo.setText("");
                                txtQuantityDetailTotal.setText("0");

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

        recyclerView.setLayoutManager((new LinearLayoutManager((this))));
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
            if(txtPalletNo.getText().equals("")) {
                String palletNo = event.getCharacters();
                //TODO: Check format pallet No scan
                if(palletNo.length() == 20) {
                    txtPalletNo.setText(palletNo);
                    txtLabelPalletNo.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    Toast.makeText(PickingDetailActivity.this, "Scan số pallet không đúng định dạng", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(event.getCharacters().length() == 114)
                {
                    handy_detail handyDetail = SplitString(event.getCharacters());

                    if(handyDetail.getCUSTOMER_ITEM_CODE().equals(""))
                    {
                        Toast.makeText(PickingDetailActivity.this, "Tem thiếu thông tin Customer Item Code", Toast.LENGTH_SHORT).show();
                        return false;
                    } else if(handyDetail.getTVC_ITEM_CODE().equals(""))
                    {
                        Toast.makeText(PickingDetailActivity.this, "Tem thiếu thông tin TVC Item Code", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    //TODO: Check info item code detail with header
                    if(!handyDetail.getTVC_ITEM_CODE().equals(txtItem.getText().toString()))
                    {
                        Toast.makeText(
                                PickingDetailActivity.this,
                                "Item Code Detail không trùng với Item Code header.\nHeader: " + txtItem.getText().toString() + "\nDetail:  " + handyDetail.getTVC_ITEM_CODE(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return false;
                    }

                    //TODO: Check exists item in list
                    if (!checkExistData(handyDetail, list_handyDetail)) {
                        handyDetail.setPALLET_NO(txtPalletNo.getText().toString());

                        if(handyDetail.getQUANTITY().equals(0))
                        {
                            //TODO: Input quantity
                            final Dialog dialog = new Dialog(PickingDetailActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialog_set_quantity);

                            EditText edQuantity;
                            Button btnOk, btnCancel;

                            edQuantity = dialog.findViewById(R.id.edQuantity);
                            btnOk = dialog.findViewById(R.id.btnOk);
                            btnCancel = dialog.findViewById(R.id.btnCancel);

                            btnOk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int quantityDetail = Integer.parseInt(edQuantity.getText().toString());
                                    int quantityTotal = Integer.parseInt((txtQuantityDetailTotal.getText().toString()).replace(",", ""));

                                    if (quantityDetail > 0) {
                                        handyDetail.setQUANTITY(quantityDetail);

                                        list_handyDetail.add(handyDetail);
                                        myDB.addHandyDetail(handyDetail);

                                        DecimalFormat formatter = new DecimalFormat("###,###,###");
                                        txtQuantityDetailTotal.setText(formatter.format(quantityTotal + quantityDetail));

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
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                            edQuantity.requestFocus();

                            dialog.show();
                        } else {
                            list_handyDetail.add(handyDetail);

                            myDB.addHandyDetail(handyDetail);

                            int quantityTotal = Integer.parseInt((txtQuantityDetailTotal.getText().toString()).replace(",", ""));
                            int quantityDetail = handyDetail.getQUANTITY();

                            DecimalFormat formatter = new DecimalFormat("###,###,###");
                            txtQuantityDetailTotal.setText(formatter.format(quantityTotal + quantityDetail));

                            // Refresh recyclerView
                            pickingDetailAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(PickingDetailActivity.this, "Item đã có trong danh sách", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    /*else {
                        //TODO: Check info item code detail with header
                        if (!checkExistData(handyDetail, list_handyDetail)) {
                            handyDetail.setPALLET_NO(txtPalletNo.getText().toString());
                            list_handyDetail.add(handyDetail);

                            myDB.addHandyDetail(handyDetail);

                            int quantityTotal = Integer.parseInt((txtQuantityDetailTotal.getText().toString()).replace(",", ""));
                            int quantityDetail = handyDetail.getQUANTITY();

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
                    Toast.makeText(PickingDetailActivity.this, "Thông tin chi tiết Handy Picking Detail không đúng format", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private handy_detail SplitString(String qrCodeValue)
    {
        handy_detail handyDetail = new handy_detail();
        if(checkError())
        {
            String customerItemCode = qrCodeValue.substring(0,18).trim();
            String lineCode =  qrCodeValue.substring(18, 26).trim();
            Integer quantity = Integer.parseInt(qrCodeValue.substring(26, 34).trim().replace(".00",""));
            String tvcItemCode = qrCodeValue.substring(34, 52).trim();
            String series = qrCodeValue.substring(52, 70).trim();

            handyDetail = new handy_detail(
                    handyMS.getPICKING_LIST_NO(),
                    customerItemCode,
                    series,
                    quantity,
                    Calendar.getInstance().getTime().toString(),
                    handyMS.getEMPLOYEE_CODE(),
                    null,
                    null,
                    txtPalletNo.getText().toString().trim(),
                    tvcItemCode,
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
            if(item.getCUSTOMER_ITEM_CODE().equals(handyDetail.getCUSTOMER_ITEM_CODE())
            && item.getSERIES().equals(handyDetail.getSERIES()))
            {
                return true;
            }
        }

        return false;
    }
}