package com.example.handypicking.activity.syncData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.handypicking.R;
import com.example.handypicking.Utils.Utils;
import com.example.handypicking.Utils.UtilsView;
import com.example.handypicking.activity.picking.pickingDetail.PickingDetailView;
import com.example.handypicking.activity.picking.pickingMS.PickingMSPresenter;
import com.example.handypicking.model.handy_detail;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.database.PickingDatabaseHelper;
import com.example.handypicking.preferences.AppPreferences;

import java.util.List;
import java.util.ArrayList;

public class SyncDataActivity extends AppCompatActivity implements SyncDataAdapter.ItemClickListener, SyncDataAdapter.ButtonClickListener {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    SyncDataAdapter.ItemClickListener itemClickListener;
    SyncDataAdapter.ButtonClickListener buttonClickListener;
    final String TABLE_HANDY_MS_LOCAL       = "handy_ms_local";
    final String TABLE_HANDY_DETAIL_LOCAL   = "handy_detail_local";
    private SyncDataAdapter syncDataAdapter;
    private PickingDatabaseHelper myDB;
    private AppPreferences appPreferences;
    private Utils utils;
    private PickingMSPresenter pickingMSPresenter;
    private PickingDetailView pickingDetailView;
    List<handy_ms> list_handyMS;
    List<handy_detail> list_handyDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_data);

        setTitle("Sync Data To Server");

        recyclerView        = findViewById(R.id.recyclerView);
        itemClickListener   = this;
        buttonClickListener = this;

        myDB                = new PickingDatabaseHelper(this);
        appPreferences      = new AppPreferences(SyncDataActivity.this);
        utils               = new Utils(SyncDataActivity.this, appPreferences);
        pickingMSPresenter  = new PickingMSPresenter(SyncDataActivity.this, appPreferences, utils);

        // Retrieve the list of local data for a RecyclerView
        list_handyMS        = new ArrayList<>();
        list_handyDetail    = new ArrayList<>();
        list_handyMS        = myDB.getAllData_HandyMS(TABLE_HANDY_MS_LOCAL);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        syncDataAdapter = new SyncDataAdapter(SyncDataActivity.this, list_handyMS, itemClickListener, buttonClickListener);
        recyclerView.setAdapter(syncDataAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, list_handyMS.get(position).getPICKING_LIST_NO(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClick(View view, int position) {
        String pickingListNo = list_handyMS.get(position).getPICKING_LIST_NO();

        // Get data Picking detail by PickingListNo
        list_handyDetail = myDB.getData_HandyDetail_By_PickingListNo(TABLE_HANDY_DETAIL_LOCAL, pickingListNo);

        // Check exists Header
        utils.check_Exists_List_HandyPicking_Detail(list_handyDetail, new UtilsView() {
            @Override
            public void countDataPickingDetail(int countDataPickingDetail) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(countDataPickingDetail == -1)
                        {
                            String title = "Lỗi";
                            String message = "Phát sinh lỗi\nĐồng bộ không thành công";
                            utils.displayDialogNotification(title, message);
                        } else if(countDataPickingDetail > 0)
                        {
                            String title = "Lỗi";
                            String message = "Đồng bộ không thành công\nPicking Detail chứa item đã tồn tại dưới cơ sở dữ liệu SQL Server";
                            utils.displayDialogNotification(title, message);
                        } else if(countDataPickingDetail == 0)
                        {
                            List<handy_ms> dataDelete = new ArrayList<>();
                            dataDelete.add(list_handyMS.get(position));

                            utils.onSendDataHandyMS(dataDelete);
                            utils.onSendDataHandyDetail(list_handyDetail);

                            //Delete data in the table local
                            myDB.deleteData(TABLE_HANDY_MS_LOCAL);
                            myDB.deleteData(TABLE_HANDY_DETAIL_LOCAL);

                            list_handyMS.remove(position);
                            list_handyDetail.clear();

                            // Refresh recyclerView
                            syncDataAdapter.notifyDataSetChanged();

                            // Notification
                            String title = "Thông báo";
                            String message = "Đồng bộ thành công";
                            utils.displayDialogNotification(title, message);
                        }
                    }
                });
            }
        });

    }
}