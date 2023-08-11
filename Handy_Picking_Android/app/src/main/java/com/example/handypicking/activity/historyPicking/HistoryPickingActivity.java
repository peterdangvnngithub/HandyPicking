package com.example.handypicking.activity.historyPicking;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;
import android.content.Intent;

import com.example.handypicking.R;
import com.example.handypicking.Utils.Utils;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.preferences.AppPreferences;
import com.example.handypicking.activity.picking.pickingDetail.PickingDetailActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

public class HistoryPickingActivity extends AppCompatActivity implements HistoryPickingView {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    HistoryPickingAdapter.ItemClickListener itemClickListener;
    private HistoryPickingAdapter historyPickingAdapter;
    private HistoryPickingPresenter historyPickingPresenter;
    private AppPreferences appPreferences;
    private Utils utils;
    private Boolean IsFilter = false;
    private String PLNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_picking);

        setTitle( "Lịch sử Picking" );

        recyclerView            = findViewById(R.id.recyclerView);
        swipeRefreshLayout      = (SwipeRefreshLayout) findViewById( R.id.swipe_refresh );

        appPreferences          = new AppPreferences(HistoryPickingActivity.this);
        utils                   = new Utils(HistoryPickingActivity.this, appPreferences);

        historyPickingPresenter = new HistoryPickingPresenter( appPreferences, HistoryPickingActivity.this);
        historyPickingPresenter.getDataHandyMS();

        swipeRefreshLayout.setOnRefreshListener(
                () -> historyPickingPresenter.getDataHandyMS()
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
        // Item click
        itemClickListener = (((view, position) -> {
            if(resultModels.get(position).getSTATUS() == 0) {
                //Fix bug get position không đúng khi dùng chức năng filter
                Intent intent = new Intent(HistoryPickingActivity.this, PickingDetailActivity.class);
                if (IsFilter)
                {
                    intent.putExtra("HistoryPicking-PLNo", PLNumber);
                } else {
                    intent.putExtra("HistoryPicking-PLNo", resultModels.get(position).getPICKING_LIST_NO());
                }
                startActivity(intent);
            } else if(resultModels.get(position).getSTATUS() == 1) {
                String title = "Thông Báo";
                String message = "Packing đã khóa, không thể chỉnh sửa";
                utils.displayDialogNotification(title, message);
            }
        }));

        historyPickingAdapter = new HistoryPickingAdapter( this, resultModels, itemClickListener );
        historyPickingAdapter.notifyDataSetChanged();

        // Update recyclerView
        recyclerView.setAdapter( historyPickingAdapter );

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }

    /***
     * When press Scan button
     * @param event The key event.
     *
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getCharacters() != null && !event.getCharacters().isEmpty())
        {
            String inputString = event.getCharacters();
            if(inputString.length() != 126)
            {
                String title    = "Lỗi";
                String message  = "QR Code không đúng định dạng";

                /*utils.displayDialogNotification(title, message);*/
                utils.displayDialogNotification(title, String.valueOf(inputString.length()));
            } else {
                PLNumber = inputString.substring(66, 96).trim();

                historyPickingAdapter.getFilter().filter(PLNumber);

                IsFilter = true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.action_refresh:
                historyPickingAdapter.getFilter().filter("");
                IsFilter = false;
                PLNumber = "";
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}