package com.example.handypicking.model;

public class handy_detail {
    private String PICKING_LIST_NO;
    private String INVOICE_NO;
    private String SALE_ORDER;
    private String ITEM_CODE;
    private String LOT_ID;
    private Integer QUANTITY;
    private String PALLET_NO;
    private String SERIES;
    private String CUS_ITEM_CODE;
    private String TVC_ITEM_CODE;
    private String CUSTOMER_PO;
    private Integer QTY_CARTON;
    private Integer QTY_PER_CARTON;
    private Integer QTY_TOTAL;
    private Float NET_WEIGHT;
    private Float NET_WEIGHT_TOTAL;
    private Float GROSS_WEIGHT;
    private String LOT_NO;
    private String CREATE_DATE;
    private String CREATE_BY;
    private String EDIT_DATE;
    private String EDIT_BY;
    private Integer STATUS;
    private String COLUMN1;
    private String COLUMN2;
    private String COLUMN3;
    private String COLUMN4;
    private String COLUMN5;

    public handy_detail() {
    }

    public handy_detail(String PICKING_LIST_NO,
                        String INVOICE_NO,
                        String SALE_ORDER,
                        String ITEM_CODE,
                        String LOT_ID,
                        Integer QUANTITY,
                        String PALLET_NO,
                        String SERIES,
                        String CUS_ITEM_CODE,
                        String TVC_ITEM_CODE,
                        String CUSTOMER_PO,
                        Integer QTY_CARTON,
                        Integer QTY_PER_CARTON,
                        Integer QTY_TOTAL,
                        Float NET_WEIGHT,
                        Float NET_WEIGHT_TOTAL,
                        Float GROSS_WEIGHT,
                        String LOT_NO,
                        String CREATE_DATE,
                        String CREATE_BY,
                        String EDIT_DATE,
                        String EDIT_BY,
                        Integer STATUS,
                        String COLUMN1,
                        String COLUMN2,
                        String COLUMN3,
                        String COLUMN4,
                        String COLUMN5)
    {
        this.PICKING_LIST_NO = PICKING_LIST_NO;
        this.INVOICE_NO = INVOICE_NO;
        this.SALE_ORDER = SALE_ORDER;
        this.ITEM_CODE = ITEM_CODE;
        this.LOT_ID = LOT_ID;
        this.QUANTITY = QUANTITY;
        this.PALLET_NO = PALLET_NO;
        this.SERIES = SERIES;
        this.CUS_ITEM_CODE = CUS_ITEM_CODE;
        this.TVC_ITEM_CODE = TVC_ITEM_CODE;
        this.CUSTOMER_PO = CUSTOMER_PO;
        this.QTY_CARTON = QTY_CARTON;
        this.QTY_PER_CARTON = QTY_PER_CARTON;
        this.QTY_TOTAL = QTY_TOTAL;
        this.NET_WEIGHT = NET_WEIGHT;
        this.NET_WEIGHT_TOTAL = NET_WEIGHT_TOTAL;
        this.GROSS_WEIGHT = GROSS_WEIGHT;
        this.LOT_NO = LOT_NO;
        this.CREATE_DATE = CREATE_DATE;
        this.CREATE_BY = CREATE_BY;
        this.EDIT_DATE = EDIT_DATE;
        this.EDIT_BY = EDIT_BY;
        this.STATUS = STATUS;
        this.COLUMN1 = COLUMN1;
        this.COLUMN2 = COLUMN2;
        this.COLUMN3 = COLUMN3;
        this.COLUMN4 = COLUMN4;
        this.COLUMN5 = COLUMN5;
    }

    public String getPICKING_LIST_NO() {
        return PICKING_LIST_NO;
    }

    public String getINVOICE_NO() {
        return INVOICE_NO;
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

    public String getPALLET_NO() {
        return PALLET_NO;
    }

    public String getSERIES() {
        return SERIES;
    }

    public String getCUS_ITEM_CODE() {
        return CUS_ITEM_CODE;
    }

    public String getTVC_ITEM_CODE() {
        return TVC_ITEM_CODE;
    }

    public String getCUSTOMER_PO() {
        return CUSTOMER_PO;
    }

    public Integer getQTY_CARTON() {
        return QTY_CARTON;
    }

    public Integer getQTY_PER_CARTON() {
        return QTY_PER_CARTON;
    }

    public Integer getQTY_TOTAL() {
        return QTY_TOTAL;
    }

    public Float getNET_WEIGHT() {
        return NET_WEIGHT;
    }

    public Float getNET_WEIGHT_TOTAL() {
        return NET_WEIGHT_TOTAL;
    }

    public Float getGROSS_WEIGHT() {
        return GROSS_WEIGHT;
    }

    public String getLOT_NO() {
        return LOT_NO;
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

    public Integer getSTATUS() { return STATUS; }

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

    public void setPALLET_NO(String PALLET_NO) {
        this.PALLET_NO = PALLET_NO;
    }

    public void setQTY_TOTAL(Integer QTY_TOTAL) {this.QTY_TOTAL = QTY_TOTAL;}
}