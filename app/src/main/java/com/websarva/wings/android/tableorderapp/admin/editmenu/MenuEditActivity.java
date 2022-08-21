package com.websarva.wings.android.tableorderapp.admin.editmenu;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.database.DBContract;
import com.websarva.wings.android.tableorderapp.database.ProductOpenHelper;

public class MenuEditActivity extends AppCompatActivity {

    EditText product_name;
    EditText product_price;
    Button update_button;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_edit);

        product_name = findViewById(R.id.edit_product_name);
        product_price = findViewById(R.id.edit_product_price);
        update_button = findViewById(R.id.update_menu_button);

        intent = getIntent();
        String edit_id = intent.getStringExtra("id");
        String edit_name = intent.getStringExtra("name");
        String edit_price = intent.getStringExtra("price");

        // 商品名と商品価格の入力欄にgetIntentした値をset
        product_name.setText(edit_name);
        product_price.setText(edit_price);

        // add_button押下時の処理
        update_button.setOnClickListener(view -> {
            /**
             * 入力欄の状態によっての条件分岐
             * ture:Productテーブルにデータを挿入し、MenuEditActivityへ遷移
             * false:Toast表示
             */
            if (product_name.getText().toString().trim().equals("") || product_price.getText().toString().trim().equals("")) {
                // 入力欄が1つでも空欄の場合Toast表示
                Toast.makeText(getApplicationContext(),"すべての入力欄を埋めてください!",Toast.LENGTH_SHORT).show();
            }else {
                ProductOpenHelper productOpenHelper = new ProductOpenHelper(this);

                // 入力された商品番号、商品名、商品価格を商品テーブルに
                productOpenHelper.updateData(
                        edit_id,
                        product_name.getText().toString().trim(),
                        Integer.parseInt(product_price.getText().toString().trim()));

                Log.d("--->", edit_id + ":" + product_name + ":" + product_price);

                // MenuEditActivityに遷移する処理
                intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}