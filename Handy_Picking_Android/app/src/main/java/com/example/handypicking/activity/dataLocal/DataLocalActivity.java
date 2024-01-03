package com.example.handypicking.activity.dataLocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.handypicking.R;
import com.example.handypicking.Utils.Utils;
import com.example.handypicking.activity.historyPicking.HistoryPickingAdapter;
import com.example.handypicking.activity.historyPicking.HistoryPickingPresenter;
import com.example.handypicking.activity.picking.pickingDetail.PickingDetailActivity;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.preferences.AppPreferences;

public class DataLocalActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    HistoryPickingAdapter.ItemClickListener itemClickListener;

    private DataLocalAdapter dataLocalAdapter;
    private DataLocalPresenter dataLocalPresenter;
    private PickingDatabaseHelper myDB;
    private AppPreferences appPreferences;
    private Utils utils;
    private Boolean IsFilter = false;
    private String PLNumber = "";
    private String TABLE_HANDY_MS = "handy_ms";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_local);

        setTitle( "Data Local" );

        recyclerView            = findViewById(R.id.dataLocal_recyclerView);
        swipeRefreshLayout      = (SwipeRefreshLayout) findViewById( R.id.dataLocal_swipe_refresh );
        myDB                    = new PickingDatabaseHelper(DataLocalActivity.this);

        swipeRefreshLayout.setOnRefreshListener(
                () -> myDB.getAllData_HandyMS(TABLE_HANDY_MS)
        );

        // Setting init recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }
}