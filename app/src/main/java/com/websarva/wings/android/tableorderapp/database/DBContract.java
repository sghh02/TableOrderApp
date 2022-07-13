package com.websarva.wings.android.tableorderapp.database;

import android.net.Uri;
import android.provider.BaseColumns;

import com.websarva.wings.android.tableorderapp.provider.ProductProvider;

// データベースのテーブル名・項目名を定義
public final class DBContract {

    // 誤ってインスタンス化しないようにコンストラクタをプライベート宣言
    private DBContract() {};

    // テーブルの内容を定義
    public static class ProductEntry implements BaseColumns {

        // URIパス
        public static final String PATH = "product";
        // コンテントURI
        public static final Uri CONTENT_URI = Uri.parse("content://" + ProductProvider.AUTHORITY + "/" + PATH);
        // テーブル指定コンテントタイプ
        public static final String CONTENT_TYPE = "vnd.android.cursor.item/vnd.example.products";
        // レコード個別指定コンテントタイプ
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.dir/vnd.example.products";


        // BaseColumns インターフェースを実装することで、内部クラスは_IDを継承できる
        public static final String PRODUCT_TABLE_NAME = "product";
        public static final String COLUMN_NAME_PRODUCT_ID = "product_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PRICE = "price";
    }
}
