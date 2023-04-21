package com.example.handypicking.model;

import java.io.Serializable;

public class handy_ms implements Serializable {
    private String CUSTOMER_CODE;
    private String PICKING_LIST_NO;
    private String DELIVERY_ADDRESS;
    private String SALE_ORDER;
    private String ITEM_CODE;
    private String LOT_ID;
    private Integer QUANTITY;
    private String EMPLOYEE_CODE;
    private String COLUMN6;
    private String CREATE_DATE;
    private String CREATE_BY;
    private String EDIT_DATE;
    private String EDIT_BY;
    private String COLUMN1;
    private String COLUMN2;
    private String COLUMN3;
    private String COLUMN4;
    private String COLUMN5;

    public handy_ms(String CUSTOMER_CODE,
                    String PICKING_LIST_NO,
                    String DELIVERY_ADDRESS,
                    String SALE_ORDER,
                    String ITEM_CODE,
                    String LOT_ID,
                    Integer QUANTITY,
                    String EMPLOYEE_CODE,
                    String COLUMN6,
                    String CREATE_DATE,
                    String CREATE_BY,
                    String EDIT_DATE,
                    String EDIT_BY,
                    String COLUMN1,
                    String COLUMN2,
                    String COLUMN3,
                    String COLUMN4,
                    String COLUMN5) {
        this.CUSTOMER_CODE = CUSTOMER_CODE;
        this.PICKING_LIST_NO = PICKING_LIST_NO;
        this.DELIVERY_ADDRESS = DELIVERY_ADDRESS;
        this.SALE_ORDER = SALE_ORDER;
        this.ITEM_CODE = ITEM_CODE;
        this.LOT_ID = LOT_ID;
        this.QUANTITY = QUANTITY;
        this.EMPLOYEE_CODE = EMPLOYEE_CODE;
        this.COLUMN6 = COLUMN6;
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

    public String getCUSTOMER_CODE() {
        return CUSTOMER_CODE;
    }

    public String getPICKING_LIST_NO() {
        return PICKING_LIST_NO;
    }

    public String getDELIVERY_ADDRESS() {
        return DELIVERY_ADDRESS;
    }

    public String getSALE_ORDER() {
        return SALE_ORDER;
    }

    public String getITEM_CODE() {
        return ITEM_CODE;
    }

    public String getLOT_ID() {
        return LOT_ID;
    }

    public Integer getQUANTITY() {
        return QUANTITY;
    }

    public String getEMPLOYEE_CODE() {
        return EMPLOYEE_CODE;
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

    public String getCOLUMN6() {
        return COLUMN6;
    }
}
