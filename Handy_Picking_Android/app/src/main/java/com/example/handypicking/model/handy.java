package com.example.handypicking.model;

import java.util.List;

public class handy {
    private List<handy_ms> list_handyMS;
    private List<handy_detail> list_handyDetail;

    public handy(List<handy_ms> _listHandyMS, List<handy_detail> _listHandyDetail)
    {
        this.list_handyMS = _listHandyMS;
        this.list_handyDetail = _listHandyDetail;
    }

    public List<handy_ms> get_ListHandyMS()
    {
        return list_handyMS;
    }

    public List<handy_detail> get_ListHandyDetail()
    {
        return list_handyDetail;
    }
}
