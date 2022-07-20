package com.websarva.wings.android.tableorderapp.admin.editmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.admin.editmenu.fragment.EditFragment1;
import com.websarva.wings.android.tableorderapp.database.ProductOpenHelper;

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

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductOpenHelper productOpenHelper = new ProductOpenHelper(MenuAddActivity.this);
                productOpenHelper.addData(Integer.valueOf(product_number.getText().toString().trim()), product_name.getText().toString().trim(), Integer.valueOf(product_price.getText().toString().trim()));

                Intent intent = new Intent(MenuAddActivity.this, MenuEditActivity.class);
                startActivity(intent);
            }
        });
    }
}