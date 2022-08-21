package com.websarva.wings.android.tableorderapp.admin.editmenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.database.ProductOpenHelper;

/** 商品の追加をするActivity */
public class MenuAddActivity extends AppCompatActivity {

    EditText product_name, product_price;
    Integer product_number;
    Button add_button;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add);

        product_name = findViewById(R.id.product_name);
        product_price = findViewById(R.id.product_price);
        add_button = findViewById(R.id.add_menu_button);

        intent = getIntent();
        product_number = intent.getIntExtra("PRODUCT_NUMBER", 0);

        // add_button押下時の処理
        add_button.setOnClickListener(view -> {
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
                if (product_number != 0) {
                    // 入力された商品番号、商品名、商品価格を商品テーブルに追加
                    productOpenHelper.addData(
                            product_number,
                            product_name.getText().toString().trim(),
                            Integer.parseInt(product_price.getText().toString().trim()));
                } else {
                    Log.d("MenuAddActivity", "addData failed");
                }

                // MenuEditActivityに遷移する処理
                intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}