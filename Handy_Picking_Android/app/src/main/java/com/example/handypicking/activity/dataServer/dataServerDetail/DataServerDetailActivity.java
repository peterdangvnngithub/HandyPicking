package com.example.handypicking.activity.dataServer.dataServerDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;
import android.widget.TextView;

import com.example.handypicking.R;
import com.example.handypicking.model.handy_detail;
import com.example.handypicking.preferences.AppPreferences;

import java.util.List;
import java.util.ArrayList;

public class DataServerDetailActivity extends AppCompatActivity implements DataServerDetailView {
    private DataServerDetailAdapter dataServerDetailAdapter;
    private DataServerDetailPresenter dataServerDetailPresenter;
    private AppPreferences appPreferences;
    private RecyclerView recyclerView;
    private List<handy_detail> handyDetail = new ArrayList<>();
    private TextView txtTotalQRCode, txtTotalQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_server_detail);

        recyclerView        = findViewById(R.id.dataServer_recyclerView);
        txtTotalQRCode      = findViewById(R.id.dataServer_txtTotalQRCodeScan);
        txtTotalQuantity    = findViewById(R.id.dataServer_txtQtyDetailTotal);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("packingListNo")) {
            // Get data from Intent
            String pickingListNo = intent.getStringExtra("packingListNo");

            setTitle(pickingListNo);

            appPreferences = new AppPreferences(DataServerDetailActivity.this);

            dataServerDetailPresenter = new DataServerDetailPresenter(appPreferences, DataServerDetailActivity.this);
            dataServerDetailPresenter.get_Data_Handy_Detail_By_PickingNo(intent.getStringExtra("packingListNo"));
        }
    }

    private int totalQuantity(List<handy_detail> _handyDetail)
    {
        int total = 0;
        for(handy_detail item : _handyDetail)
        {
            total += item.getQTY_TOTAL();
        }
        return total;
    }

    @Override
    public void onGetResult(List<handy_detail> resultModels) {
        if (resultModels != null) {
            handyDetail = resultModels;
            dataServerDetailAdapter = new DataServerDetailAdapter(DataServerDetailActivity.this, handyDetail);
            dataServerDetailAdapter.notifyDataSetChanged();

            // Update recyclerView
            if (recyclerView != null) {
                recyclerView.setAdapter(dataServerDetailAdapter);

                // Set info header
                txtTotalQRCode.setText(String.valueOf(handyDetail.size()));
                txtTotalQuantity.setText(String.valueOf(totalQuantity(handyDetail)));
            } else {
                Toast.makeText(DataServerDetailActivity.this, "RecyclerView is null in onGetResult", Toast.LENGTH_SHORT).show();
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        } else {
            Toast.makeText(DataServerDetailActivity.this, "Result models are null in onGetResult", Toast.LENGTH_SHORT).show();
        }
    }
}