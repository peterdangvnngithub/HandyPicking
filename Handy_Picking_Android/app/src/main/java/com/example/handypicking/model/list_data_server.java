package com.example.handypicking.model;

import java.util.List;

public class list_data_server {
    private String pickingNo;
    private List<handy_detail> list_handyDetail;

    private boolean isExpandable;

    public list_data_server(String pickingNo, List<handy_detail> list_handyDetail) {
        this.pickingNo = pickingNo;
        this.list_handyDetail = list_handyDetail;
        isExpandable = false;
    }

    public String getPickingNo() {
        return pickingNo;
    }

    public void setPickingNo(String pickingNo) {
        this.pickingNo = pickingNo;
    }

    public List<handy_detail> getList_handyDetail() {
        return list_handyDetail;
    }

    public void setList_handyDetail(List<handy_detail> list_handyDetail) {
        this.list_handyDetail = list_handyDetail;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
