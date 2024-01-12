package com.example.handypicking.activity.dataServer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.handypicking.R;
import com.example.handypicking.model.handy;
import com.example.handypicking.model.handy_ms;
import com.example.handypicking.preferences.AppPreferences;

import java.util.ArrayList;
import java.util.List;

public class DataServerActivity extends AppCompatActivity implements DataServerAdapter.ItemClickListener, DataServerAdapter.ButtonClickListener, DataServerView  {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    DataServerAdapter.ItemClickListener itemClickListener;
    DataServerAdapter.ButtonClickListener buttonClickListener;
    private AppPreferences appPreferences;
    private DataServerPresenter dataServerPresenter;
    private DataServerAdapter dataServerAdapter;
    private List<handy_ms> listHandyMS = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        setTitle("Data Server");

        recyclerView        = findViewById(R.id.data_recyclerView);
        itemClickListener   = this;
        buttonClickListener = this;

        appPreferences      = new AppPreferences(DataServerActivity.this);
        dataServerPresenter = new DataServerPresenter(appPreferences, DataServerActivity.this);
        dataServerPresenter.getData_Server();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, listHandyMS.get(position).getPICKING_LIST_NO(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClick(View view, int position) {
    }

    @Override
    public void onGetResult(handy handy) {
      /*  listHandyMS = resultModels;
        dataServerAdapter = new DataServerAdapter( DataServerActivity.this, listHandyMS, itemClickListener, buttonClickListener);
        dataServerAdapter.notifyDataSetChanged();

        // Update recyclerView
        recyclerView.setAdapter( dataServerAdapter );*/
    }

    // Add menu to Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dataserver, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_refresh:

                return true;
            default:return super.onOptionsItemSelected(item);
        }

    }
}