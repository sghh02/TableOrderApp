package com.websarva.wings.android.tableorderapp;

import android.provider.BaseColumns;

public final class DBContract {
    // 誤ってインスタンス化しないようにコンストラクタをプライベート宣言
    private DBContract(){}

    // テーブルの内容を定義
    public static class DBEntry implements BaseColumns {
        // BaseColumnsインターフェースを実装することで、内部クラスは_IDを継承できる
        public static final String TABLE_NAME = "menu_tbl";
        public static final String COLUMN_NAME_MENU_ID = "menu_id";
        public static final String COLUMN_NAME_MENU = "menu";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_UPDATE  = "up_date";
    }
}
