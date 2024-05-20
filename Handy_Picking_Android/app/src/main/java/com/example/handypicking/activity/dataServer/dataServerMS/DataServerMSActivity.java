package com.example.handypicking.activity.dataServer.dataServerMS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.content.Intent;
import android.widget.Toast;

import com.example.handypicking.R;
import com.example.handypicking.Utils.Utils;
import com.example.handypicking.model.handy;
import com.example.handypicking.model.handy_detail;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.preferences.AppPreferences;
import com.example.handypicking.activity.dataServer.dataServerDetail.DataServerDetailActivity;

import java.util.List;
import java.util.ArrayList;

public class DataServerMSActivity extends AppCompatActivity implements DataServerMSView {
    RecyclerView dataServerMS_recyclerView;
    ProgressBar dataServerMS_progressBar;
    View dataServerMS_backgroundView;
    SwipeRefreshLayout swipeRefreshLayout;
    DataServerMSAdapter.ItemClickListener itemClickListener;
    AppPreferences appPreferences;
    DataServerMSPresenter dataServerMSPresenter;
    DataServerMSAdapter dataServerMSAdapter;
    List<handy_ms> listHandyMS = new ArrayList<>();
    Boolean IsFilter = false;
    String PLNumber = "";
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_server_ms);

        setTitle("Data Server");

        dataServerMS_recyclerView   = findViewById(R.id.dataServerMS_recyclerView);
        dataServerMS_progressBar    = findViewById(R.id.dataServerMS_progressBar);
        dataServerMS_backgroundView = findViewById(R.id.dataServerMS_backgroundView);

        appPreferences              = new AppPreferences(DataServerMSActivity.this);
        dataServerMSPresenter = new DataServerMSPresenter(DataServerMSActivity.this, appPreferences, this);
        dataServerMSPresenter.getData_Server();

        dataServerMS_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataServerMS_recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onGetResult(List<handy_ms> handyMS) {
        listHandyMS = handyMS;
        // Item click
        itemClickListener = (new DataServerMSAdapter.ItemClickListener() {
            handy_ms handy = new handy_ms();
            @Override
            public void onItemClick(View view, int position) {
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
            }

            @Override
            public void onImageClick(View view, int position) {
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

                setLoading(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /*Toast.makeText(DataServerMSActivity.this, "Activity run: " + handy.getPICKING_LIST_NO(), Toast.LENGTH_SHORT).show();*/
                        // Hide ProgressBar and layout silver
                        dataServerMSPresenter.getData_Handy_By_Picking_List(handy.getPICKING_LIST_NO());
                        setLoading(View.GONE);
                    }
                }, 2000);
            }
        });

        dataServerMSAdapter = new DataServerMSAdapter( DataServerMSActivity.this, listHandyMS, itemClickListener);
        dataServerMSAdapter.notifyDataSetChanged();

        // Update recyclerView
        dataServerMS_recyclerView.setAdapter(dataServerMSAdapter);
    }

    @Override
    public void onInsertDataToLocalTable(handy handyData) {
        /*handy_ms handyMS = new handy_ms();
        List<handy_detail> handyDetail = new ArrayList<>();
        if(handyData != null)
        {
            handyMS = handyData.get_ListHandyMS().get(0);
            handyDetail = handyData.get_ListHandyDetail();
        }*/

        Toast.makeText(DataServerMSActivity.this, "onInsertDataToLocalTable Clicked", Toast.LENGTH_SHORT).show();
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

    private void setLoading(int status)
    {
        dataServerMS_progressBar.setVisibility(status);
        dataServerMS_backgroundView.setVisibility(status);
    }
}