package com.websarva.wings.android.tableorderapp.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.websarva.wings.android.tableorderapp.database.DBContract.ProductEntry;

public class ProductOpenHelper extends SQLiteOpenHelper {

    private final Context context;

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 1;

    // データーベース名
    private static final String DATABASE_NAME = "TableOrder.db";

    // テーブルの作成
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductEntry.PRODUCT_TABLE_NAME + " (" +
                    ProductEntry._ID + " INTEGER PRIMARY KEY," +
                    ProductEntry.COLUMN_NAME_PRODUCT_ID + " INTEGER　NOT NULL," +
                    ProductEntry.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                    ProductEntry.COLUMN_NAME_PRICE + " INTEGER NOT NULL)";

    // コンストラクタ
    public ProductOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // テーブルの削除
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductEntry.PRODUCT_TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        db.execSQL(
                SQL_CREATE_ENTRIES
        );

        Log.d("ProductOpenHelper", "onCreate(SQLiteDatabase db)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // データ挿入メソッド
    public void addData(int product_id, String name, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ProductEntry.COLUMN_NAME_PRODUCT_ID, product_id);
        cv.put(ProductEntry.COLUMN_NAME_NAME, name);
        cv.put(ProductEntry.COLUMN_NAME_PRICE, price);

        long result = db.insert(ProductEntry.PRODUCT_TABLE_NAME, null, cv);
        if (result == -1) {
            Log.d("addData:", "failed");
        } else {
            Log.d("addData:", "success");
        }
    }

    // Cursor作成のために必要
    // rawQueryの引数にはSQL文を渡す
    // MenuAdapterでCursorを作成する時に呼び出される
    public Cursor readData() {
        String query = "SELECT * FROM " + ProductEntry.PRODUCT_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    // データ削除メソッド

}
