package com.example.handypicking.model;

import java.io.Serializable;

public class handy_ms implements Serializable {
    private String CUSTOMER_CODE;
    private String PICKING_LIST_NO;
    private String DELIVERY_ADDRESS_CODE;
    private String DELIVERY_ADDRESS_NAME;
    private String EMPLOYEE_CODE;
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

    public handy_ms(String CUSTOMER_CODE,
                    String PICKING_LIST_NO,
                    String DELIVERY_ADDRESS_CODE,
                    String DELIVERY_ADDRESS_NAME,
                    String EMPLOYEE_CODE,
                    String CREATE_DATE,
                    String CREATE_BY,
                    String EDIT_DATE,
                    String EDIT_BY,
                    Integer STATUS,
                    String COLUMN1,
                    String COLUMN2,
                    String COLUMN3,
                    String COLUMN4,
                    String COLUMN5) {
        this.CUSTOMER_CODE          = CUSTOMER_CODE;
        this.PICKING_LIST_NO        = PICKING_LIST_NO;
        this.DELIVERY_ADDRESS_CODE  = DELIVERY_ADDRESS_CODE;
        this.DELIVERY_ADDRESS_NAME  = DELIVERY_ADDRESS_NAME;
        this.EMPLOYEE_CODE          = EMPLOYEE_CODE;
        this.CREATE_DATE            = CREATE_DATE;
        this.CREATE_BY              = CREATE_BY;
        this.EDIT_DATE              = EDIT_DATE;
        this.EDIT_BY                = EDIT_BY;
        this.STATUS                 = STATUS;
        this.COLUMN1                = COLUMN1;
        this.COLUMN2                = COLUMN2;
        this.COLUMN3                = COLUMN3;
        this.COLUMN4                = COLUMN4;
        this.COLUMN5                = COLUMN5;
    }

    public handy_ms() {

    }

    public String getCUSTOMER_CODE() {
        return CUSTOMER_CODE;
    }

    public String getPICKING_LIST_NO()
    {
        return PICKING_LIST_NO;
    }

    public String getDELIVERY_ADDRESS_CODE()
    {
        return DELIVERY_ADDRESS_CODE;
    }
    public String getDELIVERY_ADDRESS_NAME()
    {
        return DELIVERY_ADDRESS_NAME;
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

    public void setCUSTOMER_CODE(String CUSTOMER_CODE) {
        this.CUSTOMER_CODE = CUSTOMER_CODE;
    }

    public void setPICKING_LIST_NO(String PICKING_LIST_NO) {
        this.PICKING_LIST_NO = PICKING_LIST_NO;
    }

    public void setDELIVERY_ADDRESS_CODE(String DELIVERY_ADDRESS_CODE) {
        this.DELIVERY_ADDRESS_CODE = DELIVERY_ADDRESS_CODE;
    }

    public void setDELIVERY_ADDRESS_NAME(String DELIVERY_ADDRESS_NAME) {
        this.DELIVERY_ADDRESS_NAME = DELIVERY_ADDRESS_NAME;
    }

    public void setEMPLOYEE_CODE(String EMPLOYEE_CODE) {
        this.EMPLOYEE_CODE = EMPLOYEE_CODE;
    }

    public void setCREATE_DATE(String CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    public void setCREATE_BY(String CREATE_BY) {
        this.CREATE_BY = CREATE_BY;
    }

    public void setEDIT_DATE(String EDIT_DATE) {
        this.EDIT_DATE = EDIT_DATE;
    }

    public void setEDIT_BY(String EDIT_BY) {
        this.EDIT_BY = EDIT_BY;
    }

    public void setSTATUS(Integer STATUS) {
        this.STATUS = STATUS;
    }

    public void setCOLUMN1(String COLUMN1) {
        this.COLUMN1 = COLUMN1;
    }

    public void setCOLUMN2(String COLUMN2) {
        this.COLUMN2 = COLUMN2;
    }

    public void setCOLUMN3(String COLUMN3) {
        this.COLUMN3 = COLUMN3;
    }

    public void setCOLUMN4(String COLUMN4) {
        this.COLUMN4 = COLUMN4;
    }

    public void setCOLUMN5(String COLUMN5) {
        this.COLUMN5 = COLUMN5;
    }
}
