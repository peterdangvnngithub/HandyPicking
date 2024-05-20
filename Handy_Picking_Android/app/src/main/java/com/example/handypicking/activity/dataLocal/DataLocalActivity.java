package com.example.handypicking.activity.dataLocal;

import android.os.Bundle;
import android.view.Menu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import com.example.handypicking.R;
import com.example.handypicking.Utils.Utils;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.preferences.AppPreferences;
import com.example.handypicking.activity.picking.pickingDetail.PickingDetailActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class DataLocalActivity extends AppCompatActivity implements DataLocalView {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    DataLocalAdapter.ItemClickListener itemClickListener;
    private DataLocalAdapter dataLocalAdapter;
    private AppPreferences appPreferences;
    PickingDatabaseHelper myDB;
    List<handy_ms> handyMS = new ArrayList<>();
    private Utils utils;
    private Boolean IsFilter = false;
    private String PLNumber = "";
    private String TABLE_NAME = "handy_ms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_picking);

        setTitle( "Lịch sử Picking" );

        recyclerView            = findViewById(R.id.recyclerView);
        swipeRefreshLayout      = (SwipeRefreshLayout) findViewById( R.id.swipe_refresh );
        myDB                    = new PickingDatabaseHelper(DataLocalActivity.this);

        appPreferences          = new AppPreferences(DataLocalActivity.this);
        utils                   = new Utils(DataLocalActivity.this, appPreferences);
        handyMS                 = myDB.getAllData_HandyMS(TABLE_NAME);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        handyMS = myDB.getAllData_HandyMS(TABLE_NAME);
                    }
                }
        );

        // Item click
        itemClickListener = new DataLocalAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                handy_ms handy = new handy_ms();
                if (IsFilter){
                    for (handy_ms item : handyMS)
                    {
                        if (item.getPICKING_LIST_NO().equalsIgnoreCase(PLNumber))
                        {
                            handy = item;
                        }
                    }
                } else {
                    handy = handyMS.get(position);
                }

                if(handy == null)
                {
                    String title = "Thông Báo";
                    String message = "Lỗi giá trị History.\nKhông thể kết nối tới database.";
                    utils.displayDialogNotification(title, message);
                    return;
                }

                if(handy.getSTATUS() == 0) {
                    //Fix bug get position không đúng khi dùng chức năng filter
                    Intent intent = new Intent(DataLocalActivity.this, PickingDetailActivity.class);
                    intent.putExtra("handyMS", handy);
                    startActivity(intent);
                } else if(handy.getSTATUS() == 1) {
                    String title = "Thông Báo";
                    String message = "Packing đã khóa, không thể chỉnh sửa";
                    utils.displayDialogNotification(title, message);
                }
            }

            @Override
            public void onImageClick(View view, int position) {
                handy_ms handy = new handy_ms();
                if (IsFilter){
                    for (handy_ms item : handyMS)
                    {
                        if (item.getPICKING_LIST_NO().equalsIgnoreCase(PLNumber))
                        {
                            handy = item;
                        }
                    }
                } else {
                    handy = handyMS.get(position);
                }

                Toast.makeText(DataLocalActivity.this, handy.getPICKING_LIST_NO(), Toast.LENGTH_SHORT).show();
            }
        };

        dataLocalAdapter = new DataLocalAdapter( this, handyMS, itemClickListener );
        dataLocalAdapter.notifyDataSetChanged();

        // Update recyclerView
        recyclerView.setAdapter(dataLocalAdapter);
        swipeRefreshLayout.setRefreshing(false);

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
            if(inputString.length() != 139)
            {
                String title    = "Lỗi";
                String message  = "QR Code không đúng định dạng\nĐộ dài QR Code đúng: 139\nĐộ dài QR Code đang bấm: " + String.valueOf(inputString.length());

                utils.displayDialogNotification(title, message);
            } else {
                PLNumber = inputString.substring(66, 96).trim();

                dataLocalAdapter.getFilter().filter(PLNumber);

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
                dataLocalAdapter.getFilter().filter("");
                IsFilter = false;
                PLNumber = "";
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}