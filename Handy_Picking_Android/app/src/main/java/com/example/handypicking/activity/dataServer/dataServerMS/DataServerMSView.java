package com.example.handypicking.activity.dataServer.dataServerMS;

import com.example.handypicking.model.handy;
import com.example.handypicking.model.handy_ms;

import java.util.List;

public interface DataServerMSView {
    void onGetResult(List<handy_ms> resultModels);
    void onInsertDataToLocalTable(handy handyData);
}
