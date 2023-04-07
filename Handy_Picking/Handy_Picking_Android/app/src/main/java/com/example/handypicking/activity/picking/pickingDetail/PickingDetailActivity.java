package com.example.handypicking.activity.picking.pickingDetail;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handypicking.R;
import com.example.handypicking.activity.menu.MenuActivity;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.model.handy_detail;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PickingDetailActivity extends AppCompatActivity {
    TextView txtPLNumber, txtItem, txtQuantity, txtPalletNo, txtQuantityDetailTotal;
    Button btnPost, btnCancel;
    RecyclerView recyclerView;
    handy_ms handyMS;
    PickingDatabaseHelper myDB;

    PickingDetailAdapter.ItemClickListener itemClickListener;

    private String TAG = "PickingDetailActivityDeBug";
    private PickingDetailAdapter pickingDetailAdapter;
    private PickingDetailPresenter pickingDetailPresenter = new PickingDetailPresenter();
    private List<handy_ms> list_handyMS = new ArrayList<>();
    private List<handy_detail> list_handyDetail = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking_detail);

        txtPLNumber =findViewById(R.id.txtPLNumber);
        txtItem =findViewById(R.id.txtItem);
        txtQuantity =findViewById(R.id.txtQuantity);
        txtPalletNo =findViewById(R.id.edPalletNo);
        txtQuantityDetailTotal = findViewById(R.id.txtQuantityDetailTotal);

        recyclerView = findViewById(R.id.recyclerView);

        btnPost = findViewById(R.id.btnPost);
        btnCancel = findViewById(R.id.btnCancel);

        myDB = new PickingDatabaseHelper(PickingDetailActivity.this);

        if(getIntent().getExtras() != null) {
            handyMS = (handy_ms) getIntent().getSerializableExtra("handyMS");
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
            alert.setMessage("Bạn muốn xóa:\nItem: '" + list_handyDetail.get(position).getITEM_CODE() + "'\nSeries: '" + list_handyDetail.get(position).getSERIES() + "'\nQuantity: '" + list_handyDetail.get(position).getQUANTITY() + "'");

            alert.show();
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Send data picking to Web API
                    list_handyMS.add(handyMS);
                    pickingDetailPresenter.onSendDataHandyMS(list_handyMS);
                    pickingDetailPresenter.onSendDataHandyDetail(list_handyDetail);

                    // Clear local data
                    myDB.deleteAllData();

                    Toast.makeText(PickingDetailActivity.this,"Lưu PackingList No '" + handyMS.getPICKING_LIST_NO() + "' thành công!", Toast.LENGTH_SHORT).show();

                    // Back to main menu
                    Intent intent = new Intent(PickingDetailActivity.this, MenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } catch (Exception ex)
                {
                    Log.d(TAG, ex.getMessage());
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
        /*createListData();*/
    }

    /***
     * When press Scan button
     * @param event The key event.
     *
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        if (event.getCharacters() != null && !event.getCharacters().isEmpty()) {
            handy_detail handyDetail = SplitString(event.getCharacters());

            if(!checkExistData(handyDetail, list_handyDetail)) {
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
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private void createAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        AlertDialog alert = builder.create();
        alert.setTitle("Input Packing List Detail");

        final EditText contentEditText = new EditText(PickingDetailActivity.this);

        alert.setView(contentEditText);

        contentEditText.requestFocus();

        contentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String qrCodeValue = contentEditText.getText().toString();
                alert.cancel();

                handy_detail handyDetail = SplitString(qrCodeValue);

                if(!checkExistData(handyDetail, list_handyDetail)) {
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
                }
            }
        });

        alert.show();
    }

    private handy_detail SplitString(String qrCodeValue)
    {
        String customerItemCode = qrCodeValue.substring(0,18).trim();
        String lineCode =  qrCodeValue.substring(18, 26).trim();
        Integer quantity = Integer.parseInt(qrCodeValue.substring(26, 34).trim().replace(".00",""));
        String tvcItemCode = qrCodeValue.substring(34, 52).trim();
        String series = qrCodeValue.substring(52, 70).trim();

        handy_detail handyDetail = new handy_detail(
                txtPLNumber.getText().toString(),
                customerItemCode,
                series,
                quantity,
                Calendar.getInstance().getTime().toString(),
                handyMS.getEMPLOYEE_CODE(),
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        return handyDetail;
    }

    private void createListData() {
        handy_detail handyDetail = new handy_detail("TVC/TSK-22/751","11063-V", "00001", 100, Calendar.getInstance().getTime().toString(), "ADMIN", null, null, null, null, null, null, null);
        list_handyDetail.add(handyDetail);

        handyDetail = new handy_detail("TVC/TSK-22/751","11063-V", "00002", 200, Calendar.getInstance().getTime().toString(), "ADMIN", null, null, null, null, null, null, null);
        list_handyDetail.add(handyDetail);

        handyDetail = new handy_detail("TVC/TSK-22/751","11063-V", "00003", 300, Calendar.getInstance().getTime().toString(), "ADMIN", null, null, null, null, null, null, null);
        list_handyDetail.add(handyDetail);

        handyDetail = new handy_detail("TVC/TSK-22/751","11063-V", "00004", 400, Calendar.getInstance().getTime().toString(), "ADMIN", null, null, null, null, null, null, null);
        list_handyDetail.add(handyDetail);

        handyDetail = new handy_detail("TVC/TSK-22/751","11063-V", "00005", 500, Calendar.getInstance().getTime().toString(), "ADMIN", null, null, null, null, null, null, null);
        list_handyDetail.add(handyDetail);

        handyDetail = new handy_detail("TVC/TSK-22/751","11063-V", "00006", 600, Calendar.getInstance().getTime().toString(), "ADMIN", null, null, null, null, null, null, null);
        list_handyDetail.add(handyDetail);

        handyDetail = new handy_detail("TVC/TSK-22/751","11063-V", "00007", 700, Calendar.getInstance().getTime().toString(), "ADMIN", null, null, null, null, null, null, null);
        list_handyDetail.add(handyDetail);
    }

    private boolean checkExistData(handy_detail handyDetail, List<handy_detail> listHandyDetail)
    {
        for(handy_detail item : listHandyDetail)
        {
            if(item.getITEM_CODE().equals(handyDetail.getITEM_CODE())
            && item.getSERIES().equals(handyDetail.getSERIES())
            && item.getQUANTITY().equals(handyDetail.getQUANTITY()))
            {
                return true;
            }
        }

        return false;
    }
}