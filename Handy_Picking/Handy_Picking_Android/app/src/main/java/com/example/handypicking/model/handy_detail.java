package com.example.handypicking.model;

public class handy_detail {
    private String PICKING_LIST_NO;
    private String ITEM_CODE;
    private String SERIES;
    private Integer QUANTITY;
    private String CREATE_DATE;
    private String CREATE_BY;
    private String EDIT_DATE;
    private String EDIT_BY;
    private String COLUMN1;
    private String COLUMN2;
    private String COLUMN3;
    private String COLUMN4;
    private String COLUMN5;

    public handy_detail(String PICKING_LIST_NO,
                        String ITEM_CODE,
                        String SERIES,
                        Integer QUANTITY,
                        String CREATE_DATE,
                        String CREATE_BY,
                        String EDIT_DATE,
                        String EDIT_BY,
                        String COLUMN1,
                        String COLUMN2,
                        String COLUMN3,
                        String COLUMN4,
                        String COLUMN5) {
        this.PICKING_LIST_NO = PICKING_LIST_NO;
        this.ITEM_CODE = ITEM_CODE;
        this.SERIES = SERIES;
        this.QUANTITY = QUANTITY;
        this.CREATE_DATE = CREATE_DATE;
        this.CREATE_BY = CREATE_BY;
        this.EDIT_DATE = EDIT_DATE;
        this.EDIT_BY = EDIT_BY;
        this.COLUMN1 = COLUMN1;
        this.COLUMN2 = COLUMN2;
        this.COLUMN3 = COLUMN3;
        this.COLUMN4 = COLUMN4;
        this.COLUMN5 = COLUMN5;
    }

    public String getPICKING_LIST_NO() {
        return PICKING_LIST_NO;
    }

    public String getITEM_CODE() {
        return ITEM_CODE;
    }

    public String getSERIES() {
        return SERIES;
    }

    public Integer getQUANTITY() {
        return QUANTITY;
    }

    public String getCREATE_DATE() {
        return CREATE_DATE;
    }

    public String getCREATE_BY() {
        return CREATE_BY;
    }

    public String getEDIT_DATE() {
        return EDIT_DATE;
    }

    public String getEDIT_BY() {
        return EDIT_BY;
    }

    public String getCOLUMN1() {
        return COLUMN1;
    }

    public String getCOLUMN2() {
        return COLUMN2;
    }

    public String getCOLUMN3() {
        return COLUMN3;
    }

    public String getCOLUMN4() {
        return COLUMN4;
    }

    public String getCOLUMN5() {
        return COLUMN5;
    }
}
