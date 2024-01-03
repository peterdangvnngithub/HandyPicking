package com.example.handypicking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.handypicking.model.handy_detail;
import com.example.handypicking.model.handy_ms;

import java.util.ArrayList;
import java.util.List;

public class PickingDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final String TAG             = "PK_DB_HELPER";
    private static final String DATABASE_NAME   = "Handy.db";
    private static final int DATABASE_VERSION   = 1;

    /*---------------------------------HANDY MS---------------------------------*/
    private static final String TABLE_HANDY_MS                      = "handy_ms";
    private static final String COL_HANDY_MS_CUSTOMER_CODE          = "customer_code";
    private static final String COL_HANDY_MS_PICKING_LIST_NO        = "picking_list_no";
    private static final String COL_HANDY_MS_DELIVERY_ADDRESS_CODE  = "delivery_address_code";
    private static final String COL_HANDY_MS_DELIVERY_ADDRESS_NAME  = "delivery_address_name";
    private static final String COL_HANDY_MS_EMPLOYEE_CODE          = "employee_code";
    private static final String COL_HANDY_MS_CREATE_DATE            = "create_date";
    private static final String COL_HANDY_MS_CREATE_BY              = "create_by";
    private static final String COL_HANDY_MS_EDIT_DATE              = "edit_date";
    private static final String COL_HANDY_MS_EDIT_BY                = "edit_by";
    private static final String COL_HANDY_MS_STATUS                 = "status";
    private static final String COL_HANDY_MS_COLUMN1                = "column1";
    private static final String COL_HANDY_MS_COLUMN2                = "column2";
    private static final String COL_HANDY_MS_COLUMN3                = "column3";
    private static final String COL_HANDY_MS_COLUMN4                = "column4";
    private static final String COL_HANDY_MS_COLUMN5                = "column5";
    /*---------------------------------HANDY DETAIL---------------------------------*/
    private static final String TABLE_HANDY_DETAIL                  = "handy_detail";
    private static final String COL_HANDY_DETAIL_PICKING_LIST_NO    = "picking_list_no";
    private static final String COL_HANDY_DETAIL_INVOICE_NO         = "invoice_no";
    private static final String COL_HANDY_DETAIL_SALE_ORDER         = "sale_order";
    private static final String COL_HANDY_DETAIL_ITEM_CODE          = "item_code";
    private static final String COL_HANDY_DETAIL_LOT_ID             = "lot_id";
    private static final String COL_HANDY_DETAIL_QUANTITY           = "quantity";
    private static final String COL_HANDY_DETAIL_PALLET_NO          = "pallet_no";
    private static final String COL_HANDY_DETAIL_SERIES             = "series";
    private static final String COL_HANDY_DETAIL_CUSTOMER_ITEM_CODE = "customer_item_code";
    private static final String COL_HANDY_DETAIL_TVC_ITEM_CODE      = "tvc_item_code";
    private static final String COL_HANDY_DETAIL_CUSTOMER_PO        = "customer_po";
    private static final String COL_HANDY_DETAIL_QTY_CARTON         = "qty_carton";
    private static final String COL_HANDY_DETAIL_QTY_PER_CARTON     = "qty_per_carton";
    private static final String COL_HANDY_DETAIL_QTY_TOTAL          = "qty_total";
    private static final String COL_HANDY_DETAIL_NET_WEIGHT         = "net_weight";
    private static final String COL_HANDY_DETAIL_NET_WEIGHT_TOTAL   = "net_weight_total";
    private static final String COL_HANDY_DETAIL_GROSS_WEIGHT       = "gross_weight";
    private static final String COL_HANDY_DETAIL_LOT_NO             = "lot_no";
    private static final String COL_HANDY_DETAIL_CREATE_DATE        = "create_date";
    private static final String COL_HANDY_DETAIL_CREATE_BY          = "create_by";
    private static final String COL_HANDY_DETAIL_EDIT_DATE          = "edit_date";
    private static final String COL_HANDY_DETAIL_EDIT_BY            = "edit_by";
    private static final String COL_HANDY_DETAIL_STATUS             = "status";
    private static final String COL_HANDY_DETAIL_COLUMN1            = "column1";
    private static final String COL_HANDY_DETAIL_COLUMN2            = "column2";
    private static final String COL_HANDY_DETAIL_COLUMN3            = "column3";
    private static final String COL_HANDY_DETAIL_COLUMN4            = "column4";
    private static final String COL_HANDY_DETAIL_COLUMN5            = "column5";
    /*---------------------------------DB BACKUP---------------------------------*/
    private static final String TABLE_HANDY_MS_LOCAL                = "handy_ms_local";
    private static final String TABLE_HANDY_DETAIL_LOCAL            = "handy_detail_local";
    /*---------------------------------HANDY MS---------------------------------*/
    private String CREATE_TABLE_HANDY_MS = "CREATE TABLE " + TABLE_HANDY_MS +
            " (" + COL_HANDY_MS_CUSTOMER_CODE   + " TEXT, "     +
            COL_HANDY_MS_PICKING_LIST_NO        + " TEXT PRIMARY KEY, " +
            COL_HANDY_MS_DELIVERY_ADDRESS_CODE  + " TEXT, "     +
            COL_HANDY_MS_DELIVERY_ADDRESS_NAME  + " TEXT, "     +
            COL_HANDY_MS_EMPLOYEE_CODE          + " TEXT, "     +
            COL_HANDY_MS_CREATE_DATE            + " TEXT, "     +
            COL_HANDY_MS_CREATE_BY              + " TEXT, "     +
            COL_HANDY_MS_EDIT_DATE              + " TEXT, "     +
            COL_HANDY_MS_EDIT_BY                + " TEXT, "     +
            COL_HANDY_MS_STATUS                 + " INTEGER, "  +
            COL_HANDY_MS_COLUMN1                + " TEXT, "     +
            COL_HANDY_MS_COLUMN2                + " TEXT, "     +
            COL_HANDY_MS_COLUMN3                + " TEXT, "     +
            COL_HANDY_MS_COLUMN4                + " TEXT, "     +
            COL_HANDY_MS_COLUMN5                + " TEXT)";

    /*---------------------------------HANDY DETAIL---------------------------------*/
    private final String CREATE_TABLE_HANDY_DETAIL = "CREATE TABLE " + TABLE_HANDY_DETAIL +
            " (" +
            COL_HANDY_DETAIL_PICKING_LIST_NO        + " TEXT, "     +
            COL_HANDY_DETAIL_INVOICE_NO             + " TEXT, "     +
            COL_HANDY_DETAIL_SALE_ORDER             + " TEXT, "     +
            COL_HANDY_DETAIL_ITEM_CODE              + " TEXT, "     +
            COL_HANDY_DETAIL_LOT_ID                 + " TEXT, "     +
            COL_HANDY_DETAIL_QUANTITY               + " INTEGER, "  +
            COL_HANDY_DETAIL_PALLET_NO              + " TEXT, "     +
            COL_HANDY_DETAIL_SERIES                 + " TEXT, "     +
            COL_HANDY_DETAIL_CUSTOMER_ITEM_CODE     + " TEXT, "     +
            COL_HANDY_DETAIL_TVC_ITEM_CODE          + " TEXT, "     +
            COL_HANDY_DETAIL_CUSTOMER_PO            + " TEXT, "     +
            COL_HANDY_DETAIL_QTY_CARTON             + " INTEGER, "  +
            COL_HANDY_DETAIL_QTY_PER_CARTON         + " INTEGER, "  +
            COL_HANDY_DETAIL_QTY_TOTAL              + " INTEGER, "  +
            COL_HANDY_DETAIL_NET_WEIGHT             + " INTEGER, "  +
            COL_HANDY_DETAIL_NET_WEIGHT_TOTAL       + " INTEGER, "  +
            COL_HANDY_DETAIL_GROSS_WEIGHT           + " INTEGER, "  +
            COL_HANDY_DETAIL_LOT_NO                 + " TEXT, "     +
            COL_HANDY_DETAIL_CREATE_DATE            + " TEXT, "     +
            COL_HANDY_DETAIL_CREATE_BY              + " TEXT, "     +
            COL_HANDY_DETAIL_EDIT_DATE              + " TEXT, "     +
            COL_HANDY_DETAIL_EDIT_BY                + " TEXT, "     +
            COL_HANDY_DETAIL_STATUS                 + " INTEGER, "  +
            COL_HANDY_DETAIL_COLUMN1                + " TEXT, "     +
            COL_HANDY_DETAIL_COLUMN2                + " TEXT, "     +
            COL_HANDY_DETAIL_COLUMN3                + " TEXT, "     +
            COL_HANDY_DETAIL_COLUMN4                + " TEXT, "     +
            COL_HANDY_DETAIL_COLUMN5                + " TEXT, "     +
            "PRIMARY KEY (" +
            COL_HANDY_DETAIL_PICKING_LIST_NO        + "," +
            COL_HANDY_DETAIL_CUSTOMER_ITEM_CODE     + "," +
            COL_HANDY_DETAIL_SERIES                 + ")" +
            ")";
    /*---------------------------------HANDY MS BACKUP---------------------------------*/
    private String CREATE_TABLE_HANDY_MS_LOCAL = "CREATE TABLE " + TABLE_HANDY_MS_LOCAL +
            " (" + COL_HANDY_MS_CUSTOMER_CODE   + " TEXT, "     +
            COL_HANDY_MS_PICKING_LIST_NO        + " TEXT PRIMARY KEY, " +
            COL_HANDY_MS_DELIVERY_ADDRESS_CODE  + " TEXT, "     +
            COL_HANDY_MS_DELIVERY_ADDRESS_NAME  + " TEXT, "     +
            COL_HANDY_MS_EMPLOYEE_CODE          + " TEXT, "     +
            COL_HANDY_MS_CREATE_DATE            + " TEXT, "     +
            COL_HANDY_MS_CREATE_BY              + " TEXT, "     +
            COL_HANDY_MS_EDIT_DATE              + " TEXT, "     +
            COL_HANDY_MS_EDIT_BY                + " TEXT, "     +
            COL_HANDY_MS_STATUS                 + " INTEGER, "  +
            COL_HANDY_MS_COLUMN1                + " TEXT, "     +
            COL_HANDY_MS_COLUMN2                + " TEXT, "     +
            COL_HANDY_MS_COLUMN3                + " TEXT, "     +
            COL_HANDY_MS_COLUMN4                + " TEXT, "     +
            COL_HANDY_MS_COLUMN5                + " TEXT)";
    /*---------------------------------HANDY DETAIL BACKUP---------------------------------*/
    private final String CREATE_TABLE_HANDY_DETAIL_LOCAL = "CREATE TABLE " + TABLE_HANDY_DETAIL_LOCAL +
            " (" +
            COL_HANDY_DETAIL_PICKING_LIST_NO        + " TEXT, "     +
            COL_HANDY_DETAIL_INVOICE_NO             + " TEXT, "     +
            COL_HANDY_DETAIL_SALE_ORDER             + " TEXT, "     +
            COL_HANDY_DETAIL_ITEM_CODE              + " TEXT, "     +
            COL_HANDY_DETAIL_LOT_ID                 + " TEXT, "     +
            COL_HANDY_DETAIL_QUANTITY               + " INTEGER, "  +
            COL_HANDY_DETAIL_PALLET_NO              + " TEXT, "     +
            COL_HANDY_DETAIL_SERIES                 + " TEXT, "     +
            COL_HANDY_DETAIL_CUSTOMER_ITEM_CODE     + " TEXT, "     +
            COL_HANDY_DETAIL_TVC_ITEM_CODE          + " TEXT, "     +
            COL_HANDY_DETAIL_CUSTOMER_PO            + " TEXT, "     +
            COL_HANDY_DETAIL_QTY_CARTON             + " INTEGER, "  +
            COL_HANDY_DETAIL_QTY_PER_CARTON         + " INTEGER, "  +
            COL_HANDY_DETAIL_QTY_TOTAL              + " INTEGER, "  +
            COL_HANDY_DETAIL_NET_WEIGHT             + " REAL, "     +
            COL_HANDY_DETAIL_NET_WEIGHT_TOTAL       + " REAL, "     +
            COL_HANDY_DETAIL_GROSS_WEIGHT           + " REAL, "     +
            COL_HANDY_DETAIL_LOT_NO                 + " TEXT, "     +
            COL_HANDY_DETAIL_CREATE_DATE            + " TEXT, "     +
            COL_HANDY_DETAIL_CREATE_BY              + " TEXT, "     +
            COL_HANDY_DETAIL_EDIT_DATE              + " TEXT, "     +
            COL_HANDY_DETAIL_EDIT_BY                + " TEXT, "     +
            COL_HANDY_DETAIL_STATUS                 + " INTEGER, "  +
            COL_HANDY_DETAIL_COLUMN1                + " TEXT, "     +
            COL_HANDY_DETAIL_COLUMN2                + " TEXT, "     +
            COL_HANDY_DETAIL_COLUMN3                + " TEXT, "     +
            COL_HANDY_DETAIL_COLUMN4                + " TEXT, "     +
            COL_HANDY_DETAIL_COLUMN5                + " TEXT, "     +
            "PRIMARY KEY (" +
            COL_HANDY_DETAIL_PICKING_LIST_NO        + "," +
            COL_HANDY_DETAIL_CUSTOMER_ITEM_CODE     + "," +
            COL_HANDY_DETAIL_SERIES                 + ")" +
            ")";

    public PickingDatabaseHelper(@Nullable Context mContext) {
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HANDY_MS);
        db.execSQL(CREATE_TABLE_HANDY_DETAIL);
        db.execSQL(CREATE_TABLE_HANDY_MS_LOCAL);
        db.execSQL(CREATE_TABLE_HANDY_DETAIL_LOCAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HANDY_MS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HANDY_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HANDY_MS_LOCAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HANDY_DETAIL_LOCAL);
        onCreate(db);
    }

    public void backUpData()
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.beginTransaction();

            db.execSQL("INSERT INTO " + TABLE_HANDY_MS_LOCAL        + " SELECT * FROM " + TABLE_HANDY_MS);
            db.execSQL("INSERT INTO " + TABLE_HANDY_DETAIL_LOCAL    + " SELECT * FROM " + TABLE_HANDY_DETAIL);

            db.endTransaction();
        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public long addHandyMS(String table_name,handy_ms handy_ms)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Define the columns to be queried
        String[] projection = {COL_HANDY_MS_CUSTOMER_CODE, COL_HANDY_MS_PICKING_LIST_NO};

        // Define the selection criteria
        String selection = COL_HANDY_MS_CUSTOMER_CODE + " = ? AND " + COL_HANDY_MS_PICKING_LIST_NO + " = ?";
        String[] selectionArgs = {handy_ms.getCUSTOMER_CODE(), handy_ms.getPICKING_LIST_NO()};

        // Perform the query to check if data exists
        Cursor cursor = db.query(table_name, projection, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows (i.e., data exists)
        if (cursor.moveToFirst()) {
            // Data already exists, handle accordingly (e.g., update existing data, show an error message, etc.)
            cursor.close();
            return -1; // Return a value indicating data already exists
        }

        cv.put(COL_HANDY_MS_CUSTOMER_CODE,          handy_ms.getCUSTOMER_CODE());
        cv.put(COL_HANDY_MS_PICKING_LIST_NO,        handy_ms.getPICKING_LIST_NO());
        cv.put(COL_HANDY_MS_DELIVERY_ADDRESS_CODE,  handy_ms.getDELIVERY_ADDRESS_CODE());
        cv.put(COL_HANDY_MS_DELIVERY_ADDRESS_NAME,  handy_ms.getDELIVERY_ADDRESS_NAME());
        cv.put(COL_HANDY_MS_EMPLOYEE_CODE,          handy_ms.getEMPLOYEE_CODE());
        cv.put(COL_HANDY_MS_CREATE_DATE,            handy_ms.getCREATE_DATE());
        cv.put(COL_HANDY_MS_CREATE_BY,              handy_ms.getCREATE_BY());
        cv.put(COL_HANDY_MS_EDIT_DATE,              handy_ms.getEDIT_DATE());
        cv.put(COL_HANDY_MS_EDIT_BY,                handy_ms.getEDIT_BY());
        cv.put(COL_HANDY_MS_STATUS,                 handy_ms.getSTATUS());
        cv.put(COL_HANDY_MS_COLUMN1,                handy_ms.getCOLUMN1());
        cv.put(COL_HANDY_MS_COLUMN2,                handy_ms.getCOLUMN2());
        cv.put(COL_HANDY_MS_COLUMN3,                handy_ms.getCOLUMN3());
        cv.put(COL_HANDY_MS_COLUMN4,                handy_ms.getCOLUMN4());
        cv.put(COL_HANDY_MS_COLUMN5,                handy_ms.getCOLUMN5());

        long result = db.insertWithOnConflict(table_name, null, cv, SQLiteDatabase.CONFLICT_IGNORE);

        return result;
    }

    public long addHandyDetail(String table_name,handy_detail handyDetail) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COL_HANDY_DETAIL_PICKING_LIST_NO,    handyDetail.getPICKING_LIST_NO());
            cv.put(COL_HANDY_DETAIL_INVOICE_NO,         handyDetail.getINVOICE_NO());
            cv.put(COL_HANDY_DETAIL_SALE_ORDER,         handyDetail.getSALE_ORDER());
            cv.put(COL_HANDY_DETAIL_ITEM_CODE,          handyDetail.getITEM_CODE());
            cv.put(COL_HANDY_DETAIL_LOT_ID,             handyDetail.getLOT_ID());
            cv.put(COL_HANDY_DETAIL_QUANTITY,           handyDetail.getQUANTITY());
            cv.put(COL_HANDY_DETAIL_PALLET_NO,          handyDetail.getPALLET_NO());
            cv.put(COL_HANDY_DETAIL_SERIES,             handyDetail.getSERIES());
            cv.put(COL_HANDY_DETAIL_CUSTOMER_ITEM_CODE, handyDetail.getCUS_ITEM_CODE());
            cv.put(COL_HANDY_DETAIL_TVC_ITEM_CODE,      handyDetail.getTVC_ITEM_CODE());
            cv.put(COL_HANDY_DETAIL_CUSTOMER_PO,        handyDetail.getCUSTOMER_PO());
            cv.put(COL_HANDY_DETAIL_QTY_CARTON,         handyDetail.getQTY_CARTON());
            cv.put(COL_HANDY_DETAIL_QTY_PER_CARTON,     handyDetail.getQTY_PER_CARTON());
            cv.put(COL_HANDY_DETAIL_QTY_TOTAL,          handyDetail.getQTY_TOTAL());
            cv.put(COL_HANDY_DETAIL_NET_WEIGHT,         handyDetail.getNET_WEIGHT());
            cv.put(COL_HANDY_DETAIL_NET_WEIGHT_TOTAL,   handyDetail.getNET_WEIGHT_TOTAL());
            cv.put(COL_HANDY_DETAIL_GROSS_WEIGHT,       handyDetail.getGROSS_WEIGHT());
            cv.put(COL_HANDY_DETAIL_LOT_NO,             handyDetail.getLOT_NO());
            cv.put(COL_HANDY_DETAIL_CREATE_DATE,        handyDetail.getCREATE_DATE());
            cv.put(COL_HANDY_DETAIL_CREATE_BY,          handyDetail.getCREATE_BY());
            cv.put(COL_HANDY_DETAIL_EDIT_DATE,          handyDetail.getEDIT_DATE());
            cv.put(COL_HANDY_DETAIL_EDIT_BY,            handyDetail.getEDIT_BY());
            cv.put(COL_HANDY_DETAIL_STATUS,             handyDetail.getSTATUS());
            cv.put(COL_HANDY_DETAIL_COLUMN1,            handyDetail.getCOLUMN1());
            cv.put(COL_HANDY_DETAIL_COLUMN2,            handyDetail.getCOLUMN2());
            cv.put(COL_HANDY_DETAIL_COLUMN3,            handyDetail.getCOLUMN3());
            cv.put(COL_HANDY_DETAIL_COLUMN4,            handyDetail.getCOLUMN4());
            cv.put(COL_HANDY_DETAIL_COLUMN5,            handyDetail.getCOLUMN5());

            long result = db.insertWithOnConflict(table_name, null, cv, SQLiteDatabase.CONFLICT_IGNORE);

            return result;
        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return 0;
    }

    public long addListHandyDetails(String table_name, List<handy_detail> handyDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        long result = 0;

        try {
            for (handy_detail handyDetail : handyDetails) {
                ContentValues cv = new ContentValues();

                cv.put(COL_HANDY_DETAIL_PICKING_LIST_NO,    handyDetail.getPICKING_LIST_NO());
                cv.put(COL_HANDY_DETAIL_INVOICE_NO,         handyDetail.getINVOICE_NO());
                cv.put(COL_HANDY_DETAIL_SALE_ORDER,         handyDetail.getSALE_ORDER());
                cv.put(COL_HANDY_DETAIL_ITEM_CODE,          handyDetail.getITEM_CODE());
                cv.put(COL_HANDY_DETAIL_LOT_ID,             handyDetail.getLOT_ID());
                cv.put(COL_HANDY_DETAIL_QUANTITY,           handyDetail.getQUANTITY());
                cv.put(COL_HANDY_DETAIL_PALLET_NO,          handyDetail.getPALLET_NO());
                cv.put(COL_HANDY_DETAIL_SERIES,             handyDetail.getSERIES());
                cv.put(COL_HANDY_DETAIL_CUSTOMER_ITEM_CODE, handyDetail.getCUS_ITEM_CODE());
                cv.put(COL_HANDY_DETAIL_TVC_ITEM_CODE,      handyDetail.getTVC_ITEM_CODE());
                cv.put(COL_HANDY_DETAIL_CUSTOMER_PO,        handyDetail.getCUSTOMER_PO());
                cv.put(COL_HANDY_DETAIL_QTY_CARTON,         handyDetail.getQTY_CARTON());
                cv.put(COL_HANDY_DETAIL_QTY_PER_CARTON,     handyDetail.getQTY_PER_CARTON());
                cv.put(COL_HANDY_DETAIL_QTY_TOTAL,          handyDetail.getQTY_TOTAL());
                cv.put(COL_HANDY_DETAIL_NET_WEIGHT,         handyDetail.getNET_WEIGHT());
                cv.put(COL_HANDY_DETAIL_NET_WEIGHT_TOTAL,   handyDetail.getNET_WEIGHT_TOTAL());
                cv.put(COL_HANDY_DETAIL_GROSS_WEIGHT,       handyDetail.getGROSS_WEIGHT());
                cv.put(COL_HANDY_DETAIL_LOT_NO,             handyDetail.getLOT_NO());
                cv.put(COL_HANDY_DETAIL_CREATE_DATE,        handyDetail.getCREATE_DATE());
                cv.put(COL_HANDY_DETAIL_CREATE_BY,          handyDetail.getCREATE_BY());
                cv.put(COL_HANDY_DETAIL_EDIT_DATE,          handyDetail.getEDIT_DATE());
                cv.put(COL_HANDY_DETAIL_EDIT_BY,            handyDetail.getEDIT_BY());
                cv.put(COL_HANDY_DETAIL_STATUS,             handyDetail.getSTATUS());
                cv.put(COL_HANDY_DETAIL_COLUMN1,            handyDetail.getCOLUMN1());
                cv.put(COL_HANDY_DETAIL_COLUMN2,            handyDetail.getCOLUMN2());
                cv.put(COL_HANDY_DETAIL_COLUMN3,            handyDetail.getCOLUMN3());
                cv.put(COL_HANDY_DETAIL_COLUMN4,            handyDetail.getCOLUMN4());
                cv.put(COL_HANDY_DETAIL_COLUMN5,            handyDetail.getCOLUMN5());

                result = db.insertWithOnConflict(table_name, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
            }

            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        } finally {
            db.endTransaction();
        }

        return result;
    }

    public long deleteHandyDetailByCode(handy_detail handyDetail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COL_HANDY_DETAIL_PICKING_LIST_NO + " = ? AND " + COL_HANDY_DETAIL_CUSTOMER_ITEM_CODE + " = ? AND " + COL_HANDY_DETAIL_SERIES  + " = ?";
        String[] whereArgs = new String[] { handyDetail.getPICKING_LIST_NO(), handyDetail.getCUS_ITEM_CODE(), handyDetail.getSERIES() };

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

    public void deleteData(String tableName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, null, null);
    }

    public int countBackupData()
    {
        // Execute the query to count the items
        String query = "SELECT COUNT(*) FROM " + TABLE_HANDY_MS_LOCAL;
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteStatement statement = db.compileStatement(query);

        // Retrieve the count value from the statement
        int count = (int) statement.simpleQueryForLong();

        // Close the database
        db.close();

        return count;
    }

    public List<handy_ms> getAllData_HandyMS(String tableName) {
        List<handy_ms> list_HandyMS = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String[] columns =
            {
                COL_HANDY_MS_CUSTOMER_CODE,
                COL_HANDY_MS_PICKING_LIST_NO,
                COL_HANDY_MS_DELIVERY_ADDRESS_CODE,
                COL_HANDY_MS_DELIVERY_ADDRESS_NAME,
                COL_HANDY_MS_EMPLOYEE_CODE,
                COL_HANDY_MS_CREATE_DATE,
                COL_HANDY_MS_CREATE_BY,
                COL_HANDY_MS_EDIT_DATE,
                COL_HANDY_MS_EDIT_BY,
                COL_HANDY_MS_STATUS,
                COL_HANDY_MS_COLUMN1,
                COL_HANDY_MS_COLUMN2,
                COL_HANDY_MS_COLUMN3,
                COL_HANDY_MS_COLUMN4,
                COL_HANDY_MS_COLUMN5
            };

        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = db.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve data from the cursor
                handy_ms data = new handy_ms(
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_CUSTOMER_CODE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_PICKING_LIST_NO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_DELIVERY_ADDRESS_CODE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_DELIVERY_ADDRESS_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_EMPLOYEE_CODE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_CREATE_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_CREATE_BY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_EDIT_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_EDIT_BY)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_HANDY_MS_STATUS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_COLUMN1)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_COLUMN2)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_COLUMN3)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_COLUMN4)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_MS_COLUMN5))
                );

                list_HandyMS.add(data);
            } while (cursor.moveToNext());
        }

        // Close the cursor and database
        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return list_HandyMS;
    }

    public List<handy_detail> getData_HandyDetail_By_PickingListNo(String tableName, String pickingListNo) {
        List<handy_detail> list_HandyDetails = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String[] columns =
                {
                    COL_HANDY_DETAIL_PICKING_LIST_NO,
                    COL_HANDY_DETAIL_INVOICE_NO,
                    COL_HANDY_DETAIL_SALE_ORDER,
                    COL_HANDY_DETAIL_ITEM_CODE,
                    COL_HANDY_DETAIL_LOT_ID,
                    COL_HANDY_DETAIL_QUANTITY,
                    COL_HANDY_DETAIL_PALLET_NO,
                    COL_HANDY_DETAIL_SERIES,
                    COL_HANDY_DETAIL_CUSTOMER_ITEM_CODE,
                    COL_HANDY_DETAIL_TVC_ITEM_CODE,
                    COL_HANDY_DETAIL_CUSTOMER_PO,
                    COL_HANDY_DETAIL_QTY_CARTON,
                    COL_HANDY_DETAIL_QTY_PER_CARTON,
                    COL_HANDY_DETAIL_QTY_TOTAL,
                    COL_HANDY_DETAIL_NET_WEIGHT,
                    COL_HANDY_DETAIL_NET_WEIGHT_TOTAL,
                    COL_HANDY_DETAIL_GROSS_WEIGHT,
                    COL_HANDY_DETAIL_LOT_NO,
                    COL_HANDY_DETAIL_CREATE_DATE,
                    COL_HANDY_DETAIL_CREATE_BY,
                    COL_HANDY_DETAIL_EDIT_DATE,
                    COL_HANDY_DETAIL_EDIT_BY,
                    COL_HANDY_DETAIL_STATUS,
                    COL_HANDY_DETAIL_COLUMN1,
                    COL_HANDY_DETAIL_COLUMN2,
                    COL_HANDY_DETAIL_COLUMN3,
                    COL_HANDY_DETAIL_COLUMN4,
                    COL_HANDY_DETAIL_COLUMN5
                };

        String selection = COL_HANDY_DETAIL_PICKING_LIST_NO + "=?";
        String[] selectionArgs = { pickingListNo };
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = db.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve data from the cursor
                handy_detail data = new handy_detail(
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_PICKING_LIST_NO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_INVOICE_NO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_SALE_ORDER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_ITEM_CODE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_LOT_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_QUANTITY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_PALLET_NO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_SERIES)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_CUSTOMER_ITEM_CODE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_TVC_ITEM_CODE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_CUSTOMER_PO)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_QTY_CARTON)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_QTY_PER_CARTON)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_QTY_TOTAL)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_NET_WEIGHT)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_NET_WEIGHT_TOTAL)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_GROSS_WEIGHT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_LOT_NO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_CREATE_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_CREATE_BY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_EDIT_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_EDIT_BY)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_STATUS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_COLUMN1)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_COLUMN2)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_COLUMN3)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_COLUMN4)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_HANDY_DETAIL_COLUMN5))
                );
                list_HandyDetails.add(data);
            } while (cursor.moveToNext());
        }

        // Close the cursor and database
        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return list_HandyDetails;
    }
}
