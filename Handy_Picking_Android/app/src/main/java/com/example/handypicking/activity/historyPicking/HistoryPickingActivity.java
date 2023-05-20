package com.example.handypicking.activity.historyPicking;

import android.os.Bundle;
import android.widget.Toast;

import com.example.handypicking.R;
import com.example.handypicking.model.handy_ms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class HistoryPickingActivity extends AppCompatActivity implements HistoryPickingView {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    HistoryPickingAdapter.ItemClickListener itemClickListener;
    private HistoryPickingAdapter historyPickingAdapter;
    private HistoryPickingPresenter historyPickingPresenter;
    /*private List<handy_ms> list_handyMS = new ArrayList<>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_picking);

        setTitle( "Lịch sử Picking" );

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById( R.id.swipe_refresh );

        historyPickingPresenter = new HistoryPickingPresenter(this);
        historyPickingPresenter.getData();

        swipeRefreshLayout.setOnRefreshListener(
                () -> historyPickingPresenter.getData()
        );

        // Setting init recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing( true );
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void onGetResult(List<handy_ms> resultModels) {
        historyPickingAdapter = new HistoryPickingAdapter( this, resultModels, itemClickListener );
        historyPickingAdapter.notifyDataSetChanged();

        /*list_handyMS = resultModels;*/

        // Update recyclerView
        recyclerView.setAdapter( historyPickingAdapter );

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }
}