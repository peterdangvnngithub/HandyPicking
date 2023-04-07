package com.example.handypicking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.handypicking.model.handy_detail;
import com.example.handypicking.model.handy_ms;

import java.util.List;

public class PickingDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final String TAG = "PK_DB_HELPER";
    private static final String DATABASE_NAME = "Handy.db";
    private static final int DATABASE_VERSION = 1;

    /*---------------------------------HANDY MS---------------------------------*/
    private static final String TABLE_HANDY_MS = "handy_ms";
    private static final String COL_HANDY_MS_CUSTOMER_CODE = "customer_code";
    private static final String COL_HANDY_MS_PICKING_LIST_NO = "picking_list_no";
    private static final String COL_HANDY_MS_DELIVERY_ADDRESS = "delivery_address";
    private static final String COL_HANDY_MS_SALE_ORDER = "sale_order";
    private static final String COL_HANDY_MS_ITEM_CODE = "item_code";
    private static final String COL_HANDY_MS_LOT_ID = "lot_id";
    private static final String COL_HANDY_MS_QUANTITY = "quantity";
    private static final String COL_HANDY_MS_EMPLOYEE_CODE = "employee_code";
    private static final String COL_HANDY_MS_PALLET_NO = "pallet_no";
    private static final String COL_HANDY_MS_CREATE_DATE = "create_date";
    private static final String COL_HANDY_MS_CREATE_BY = "create_by";
    private static final String COL_HANDY_MS_EDIT_DATE = "edit_date";
    private static final String COL_HANDY_MS_EDIT_BY = "edit_by";
    private static final String COL_HANDY_MS_COLUMN1 = "column1";
    private static final String COL_HANDY_MS_COLUMN2 = "column2";
    private static final String COL_HANDY_MS_COLUMN3 = "column3";
    private static final String COL_HANDY_MS_COLUMN4 = "column4";
    private static final String COL_HANDY_MS_COLUMN5 = "column5";

    /*---------------------------------HANDY DETAIL---------------------------------*/
    private static final String TABLE_HANDY_DETAIL = "handy_detail";
    private static final String COL_HANDY_DETAIL_PICKING_LIST_NO = "picking_list_no";
    private static final String COL_HANDY_DETAIL_ITEM_CODE = "item_code";
    private static final String COL_HANDY_DETAIL_SERIES = "series";
    private static final String COL_HANDY_DETAIL_QUANTITY = "quantity";
    private static final String COL_HANDY_DETAIL_CREATE_DATE = "create_date";
    private static final String COL_HANDY_DETAIL_CREATE_BY = "create_by";
    private static final String COL_HANDY_DETAIL_EDIT_DATE = "edit_date";
    private static final String COL_HANDY_DETAIL_EDIT_BY = "edit_by";
    private static final String COL_HANDY_DETAIL_COLUMN1 = "column1";
    private static final String COL_HANDY_DETAIL_COLUMN2 = "column2";
    private static final String COL_HANDY_DETAIL_COLUMN3 = "column3";
    private static final String COL_HANDY_DETAIL_COLUMN4 = "column4";
    private static final String COL_HANDY_DETAIL_COLUMN5 = "column5";

    /*---------------------------------HANDY MS---------------------------------*/
    private String CREATE_TABLE_HANDY_MS = "CREATE TABLE " + TABLE_HANDY_MS +
            " (" + COL_HANDY_MS_CUSTOMER_CODE   + " TEXT, " +
            COL_HANDY_MS_PICKING_LIST_NO        + " TEXT PRIMARY KEY, " +
            COL_HANDY_MS_DELIVERY_ADDRESS       + " TEXT, " +
            COL_HANDY_MS_SALE_ORDER             + " TEXT, " +
            COL_HANDY_MS_ITEM_CODE              + " TEXT, " +
            COL_HANDY_MS_LOT_ID                 + " TEXT, " +
            COL_HANDY_MS_QUANTITY               + " INTEGER, " +
            COL_HANDY_MS_EMPLOYEE_CODE          + " TEXT, " +
            COL_HANDY_MS_PALLET_NO              + " TEXT, " +
            COL_HANDY_MS_CREATE_DATE            + " TEXT, " +
            COL_HANDY_MS_CREATE_BY              + " TEXT, " +
            COL_HANDY_MS_EDIT_DATE              + " TEXT, " +
            COL_HANDY_MS_EDIT_BY                + " TEXT, " +
            COL_HANDY_MS_COLUMN1                + " TEXT, " +
            COL_HANDY_MS_COLUMN2                + " TEXT, " +
            COL_HANDY_MS_COLUMN3                + " TEXT, " +
            COL_HANDY_MS_COLUMN4                + " TEXT, " +
            COL_HANDY_MS_COLUMN5                + " TEXT)";

    /*---------------------------------HANDY DETAIL---------------------------------*/
    private final String CREATE_TABLE_HANDY_DETAIL = "CREATE TABLE " + TABLE_HANDY_DETAIL +
            " (" + COL_HANDY_DETAIL_PICKING_LIST_NO + " TEXT, " +
            COL_HANDY_DETAIL_ITEM_CODE              + " TEXT, " +
            COL_HANDY_DETAIL_SERIES                 + " TEXT, " +
            COL_HANDY_DETAIL_QUANTITY               + " INTEGER, " +
            COL_HANDY_DETAIL_CREATE_DATE            + " TEXT, " +
            COL_HANDY_DETAIL_CREATE_BY              + " TEXT, " +
            COL_HANDY_DETAIL_EDIT_DATE              + " TEXT, " +
            COL_HANDY_DETAIL_EDIT_BY                + " TEXT, " +
            COL_HANDY_DETAIL_COLUMN1                + " TEXT, " +
            COL_HANDY_DETAIL_COLUMN2                + " TEXT, " +
            COL_HANDY_DETAIL_COLUMN3                + " TEXT, " +
            COL_HANDY_DETAIL_COLUMN4                + " TEXT, " +
            COL_HANDY_DETAIL_COLUMN5                + " TEXT, " +
            "PRIMARY KEY (" +
            COL_HANDY_DETAIL_PICKING_LIST_NO    + "," +
            COL_HANDY_DETAIL_ITEM_CODE          + "," +
            COL_HANDY_DETAIL_SERIES             + ")" +
            ")";

    public PickingDatabaseHelper(@Nullable Context mContext) {
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HANDY_MS);
        db.execSQL(CREATE_TABLE_HANDY_DETAIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HANDY_MS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HANDY_DETAIL);
        onCreate(db);
    }

    public long addHandyMS(handy_ms handy_ms)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_HANDY_MS_CUSTOMER_CODE,handy_ms.getCUSTOMER_CODE());
        cv.put(COL_HANDY_MS_PICKING_LIST_NO,handy_ms.getPICKING_LIST_NO());
        cv.put(COL_HANDY_MS_DELIVERY_ADDRESS,handy_ms.getDELIVERY_ADDRESS());
        cv.put(COL_HANDY_MS_SALE_ORDER,handy_ms.getSALE_ORDER());
        cv.put(COL_HANDY_MS_ITEM_CODE,handy_ms.getITEM_CODE());
        cv.put(COL_HANDY_MS_LOT_ID,handy_ms.getLOT_ID());
        cv.put(COL_HANDY_MS_QUANTITY,handy_ms.getQUANTITY());
        cv.put(COL_HANDY_MS_EMPLOYEE_CODE,handy_ms.getEMPLOYEE_CODE());
        cv.put(COL_HANDY_MS_PALLET_NO,handy_ms.getPALLET_NO());
        cv.put(COL_HANDY_MS_CREATE_DATE,handy_ms.getCREATE_DATE());
        cv.put(COL_HANDY_MS_CREATE_BY,handy_ms.getCREATE_BY());
        cv.put(COL_HANDY_MS_EDIT_DATE,handy_ms.getEDIT_DATE());
        cv.put(COL_HANDY_MS_EDIT_BY,handy_ms.getEDIT_BY());
        cv.put(COL_HANDY_MS_COLUMN1,handy_ms.getCOLUMN1());
        cv.put(COL_HANDY_MS_COLUMN2,handy_ms.getCOLUMN2());
        cv.put(COL_HANDY_MS_COLUMN3,handy_ms.getCOLUMN3());
        cv.put(COL_HANDY_MS_COLUMN4,handy_ms.getCOLUMN4());
        cv.put(COL_HANDY_MS_COLUMN5,handy_ms.getCOLUMN5());

        long result = db.insert(TABLE_HANDY_MS, null, cv);

        return result;
    }

    public long addHandyDetail(handy_detail handyDetail) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COL_HANDY_DETAIL_PICKING_LIST_NO, handyDetail.getPICKING_LIST_NO());
            cv.put(COL_HANDY_DETAIL_ITEM_CODE, handyDetail.getITEM_CODE());
            cv.put(COL_HANDY_DETAIL_SERIES, handyDetail.getSERIES());
            cv.put(COL_HANDY_DETAIL_QUANTITY, handyDetail.getQUANTITY());
            cv.put(COL_HANDY_DETAIL_CREATE_DATE, handyDetail.getCREATE_DATE());
            cv.put(COL_HANDY_DETAIL_CREATE_BY, handyDetail.getCREATE_BY());
            cv.put(COL_HANDY_DETAIL_EDIT_DATE, handyDetail.getEDIT_DATE());
            cv.put(COL_HANDY_DETAIL_EDIT_BY, handyDetail.getEDIT_BY());
            cv.put(COL_HANDY_DETAIL_COLUMN1, handyDetail.getCOLUMN1());
            cv.put(COL_HANDY_DETAIL_COLUMN2, handyDetail.getCOLUMN2());
            cv.put(COL_HANDY_DETAIL_COLUMN3, handyDetail.getCOLUMN3());
            cv.put(COL_HANDY_DETAIL_COLUMN4, handyDetail.getCOLUMN4());
            cv.put(COL_HANDY_DETAIL_COLUMN5, handyDetail.getCOLUMN5());

            long result = db.insert(TABLE_HANDY_DETAIL, null, cv);

            return result;
        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return 0;
    }

    public long deleteHandyDetailByCode(handy_detail handyDetail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COL_HANDY_DETAIL_PICKING_LIST_NO + " = ? AND " + COL_HANDY_DETAIL_ITEM_CODE + " = ? AND " + COL_HANDY_DETAIL_SERIES  + " = ?";
        String[] whereArgs = new String[] { handyDetail.getPICKING_LIST_NO(), handyDetail.getITEM_CODE(), handyDetail.getSERIES() };

        long result = db.delete(TABLE_HANDY_DETAIL, whereClause, whereArgs);
        return result;
    }

    Cursor readAllHandyMSData() {
        String query = "SELECT * FROM " + TABLE_HANDY_MS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    /*public List<handy_ms> getDataHandyMS(Context)
    {

    }*/

    public void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HANDY_MS, null, null);
        db.delete(TABLE_HANDY_DETAIL, null, null);
    }
}
