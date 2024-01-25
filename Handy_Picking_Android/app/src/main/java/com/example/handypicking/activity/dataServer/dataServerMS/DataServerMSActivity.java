package com.example.handypicking.activity.dataServer.dataServerMS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Toast;
import android.content.Intent;

import com.example.handypicking.R;
import com.example.handypicking.Utils.Utils;
import com.example.handypicking.activity.historyPicking.HistoryPickingActivity;
import com.example.handypicking.activity.picking.pickingDetail.PickingDetailActivity;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.preferences.AppPreferences;
import com.example.handypicking.activity.dataServer.dataServerDetail.DataServerDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class DataServerMSActivity extends AppCompatActivity implements DataServerMSAdapter.ItemClickListener, DataServerMSAdapter.ButtonClickListener, DataServerMSView {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    DataServerMSAdapter.ItemClickListener itemClickListener;
    DataServerMSAdapter.ButtonClickListener buttonClickListener;
    private AppPreferences appPreferences;
    private DataServerMSPresenter dataServerMSPresenter;
    private DataServerMSAdapter dataServerMSAdapter;
    private List<handy_ms> listHandyMS = new ArrayList<>();
    private Utils utils;
    private Boolean IsFilter = false;
    private String PLNumber = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_server_ms);

        setTitle("Data Server");

        recyclerView        = findViewById(R.id.data_recyclerView);
        itemClickListener   = this;
        buttonClickListener = this;

        appPreferences      = new AppPreferences(DataServerMSActivity.this);
        dataServerMSPresenter = new DataServerMSPresenter(DataServerMSActivity.this, appPreferences, this);
        dataServerMSPresenter.getData_Server();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onItemClick(View view, int position) {
      /*  Intent intent = new Intent(DataServerMSActivity.this, DataServerDetailActivity.class);
        intent.putExtra("packingListNo", listHandyMS.get(position).getPICKING_LIST_NO());
        startActivity(intent);*/
    }

    @Override
    public void onButtonClick(View view, int position) {
    }

    @Override
    public void onGetResult(List<handy_ms> handyMS) {
        listHandyMS = handyMS;

        // Item click
        itemClickListener = (((view, position) -> {
            handy_ms handy = new handy_ms();
            if (IsFilter){
                for (handy_ms item : listHandyMS)
                {
                    if (item.getPICKING_LIST_NO().equalsIgnoreCase(PLNumber))
                    {
                        handy = item;
                    }
                }
            } else {
                handy = listHandyMS.get(position);
            }

            if(handy == null)
            {
                String title = "Thông Báo";
                String message = "Lỗi giá trị History.\nKhông thể kết nối tới database.";
                utils.displayDialogNotification(title, message);
                return;
            }

            Intent intent = new Intent(DataServerMSActivity.this, DataServerDetailActivity.class);
            intent.putExtra("packingListNo", handy.getPICKING_LIST_NO());
            startActivity(intent);
        }));

        dataServerMSAdapter = new DataServerMSAdapter( DataServerMSActivity.this, listHandyMS, itemClickListener, buttonClickListener);
        dataServerMSAdapter.notifyDataSetChanged();

        // Update recyclerView
        recyclerView.setAdapter(dataServerMSAdapter);
    }

    // Add menu to Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dataserver, menu);
        return super.onCreateOptionsMenu(menu);
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

                dataServerMSAdapter.getFilter().filter(PLNumber);

                IsFilter = true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.action_refresh:
                dataServerMSAdapter.getFilter().filter("");
                IsFilter = false;
                PLNumber = "";
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}