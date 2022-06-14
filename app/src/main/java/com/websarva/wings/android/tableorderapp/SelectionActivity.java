package com.websarva.wings.android.tableorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.websarva.wings.android.tableorderapp.admin.editmenu.MenuEditActivity;
import com.websarva.wings.android.tableorderapp.customer.customerstart.CustomerStartActivity;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        // 管理者画面への遷移ボタンIDを取得
        Button button_administrator = findViewById(R.id.btAdministrator);

        // お客様画面への遷移ボタンIDを取得
        Button button_customer = findViewById(R.id.btCustomer);

        button_administrator.setOnClickListener(view -> {
            Intent intent = new Intent(SelectionActivity.this, MenuEditActivity.class);
            startActivity(intent);
        });

        button_customer.setOnClickListener(view -> {
            Intent intent = new Intent(SelectionActivity.this, CustomerStartActivity.class);
            startActivity(intent);
        });
    }
}