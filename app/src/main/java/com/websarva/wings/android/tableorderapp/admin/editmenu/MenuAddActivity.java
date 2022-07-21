package com.websarva.wings.android.tableorderapp.admin.editmenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.database.ProductOpenHelper;

/** 商品の追加をするActivity */
public class MenuAddActivity extends AppCompatActivity {

    EditText product_number, product_name, product_price;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add);

        product_number = findViewById(R.id.product_number);
        product_name = findViewById(R.id.product_name);
        product_price = findViewById(R.id.product_price);
        add_button = findViewById(R.id.add_menu_button);

        add_button.setOnClickListener(view -> {
            if (product_number.getText().toString().trim().equals("") || product_name.getText().toString().trim().equals("") || product_price.getText().toString().trim().equals("")) {
                Context context = getApplicationContext();
                Toast.makeText(context,"すべての入力欄を埋めてください!",Toast.LENGTH_SHORT).show();
            }else {
                ProductOpenHelper productOpenHelper = new ProductOpenHelper(MenuAddActivity.this);
                // 入力された商品番号、商品名、商品価格を商品テーブルに追加
                productOpenHelper.addData(
                        Integer.parseInt(product_number.getText().toString().trim()),
                        product_name.getText().toString().trim(),
                        Integer.parseInt(product_price.getText().toString().trim()));

                // MenuEditActivityに遷移する処理
                Intent intent = new Intent(MenuAddActivity.this, MenuEditActivity.class);
                startActivity(intent);
            }
        });
    }
}