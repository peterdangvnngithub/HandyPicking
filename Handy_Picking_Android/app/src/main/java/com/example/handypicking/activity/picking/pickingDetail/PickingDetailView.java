package com.example.handypicking.activity.picking.pickingDetail;

import com.example.handypicking.model.handy_detail;
import com.example.handypicking.model.handy_ms;

import java.util.List;

public interface PickingDetailView {
    void onSendHandyDetailData(handy_ms handyMS, List<handy_detail> handyDetail);
}
