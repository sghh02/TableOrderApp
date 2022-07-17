package com.websarva.wings.android.tableorderapp.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.websarva.wings.android.tableorderapp.database.DBContract.ProductEntry;
import com.websarva.wings.android.tableorderapp.database.ProductOpenHelper;

public class ProductProvider extends ContentProvider {

    // Authority
    public static final String AUTHORITY = "com.websarva.wings.android.tableorderapp.provider";

    // Productテーブル URI ID
    private static final int PRODUCTS = 1;
    // Productテーブル 個別 URI ID
    private static final int PRODUCT_ID = 2;

    // 利用者がメソッドを呼び出したURIに対応する処理を判定処理に使用する
    private static UriMatcher sUriMatcher;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, ProductEntry.PATH, PRODUCTS);
        sUriMatcher.addURI(AUTHORITY, ProductEntry.PATH + "/#", PRODUCT_ID);
    }

    // DBHelperのインスタンス
    private ProductOpenHelper mProductOpenHelper;

    /** コンテンツプロバイダの作成。プロバイダの初期化 */
    @Override
    public boolean onCreate() {
        mProductOpenHelper = new ProductOpenHelper(getContext());
        return true;
    }

    /** データを取得。戻り値はCursor */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch (sUriMatcher.match(uri)) {
            case PRODUCTS:
            case PRODUCT_ID:
                queryBuilder.setTables(ProductEntry.PRODUCT_TABLE_NAME);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase db = mProductOpenHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, strings, s, strings1, null, null, s1);
        return cursor;
    }

    /** 行を追加。戻り値は新たに追加された行のURI */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        String insertTable;
        Uri contentUri;
        switch (sUriMatcher.match(uri)) {
            case PRODUCTS:
                insertTable = ProductEntry.PRODUCT_TABLE_NAME;
                contentUri = ProductEntry.CONTENT_URI;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase db = mProductOpenHelper.getWritableDatabase();
        long rowId = db.insert(insertTable, null, contentValues);
        if (rowId > 0) {
            Uri returnUri = ContentUris.withAppendedId(contentUri, rowId);
            getContext().getContentResolver().notifyChange(returnUri, null);
            return returnUri;
        } else {
            throw new IllegalArgumentException("Failed to insert row into " + uri);
        }
    }

    /** 行を削除。戻り地は行の数 */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = mProductOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
            case PRODUCTS:
            case PRODUCT_ID:
                count = db.delete(ProductEntry.PRODUCT_TABLE_NAME, s, strings);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    /** 各業を更新。戻り値は更新した行の数 */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = mProductOpenHelper.getWritableDatabase();
        int count;
        String id = uri.getPathSegments().get(1);
        count = db.update(ProductEntry.PRODUCT_TABLE_NAME, contentValues, s, strings);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    /** ContentURIに対応するMIMEタイプを返す */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case PRODUCTS:
                return ProductEntry.CONTENT_TYPE;
            case PRODUCT_ID:
                return ProductEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}
