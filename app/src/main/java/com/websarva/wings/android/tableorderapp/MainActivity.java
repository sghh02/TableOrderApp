package com.websarva.wings.android.tableorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView count_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // プラスボタンidをを取得
        Button button_Plus = findViewById(R.id.btPlus);
        // マイナスボタンidをを取得
        Button button_minus = findViewById(R.id.btMinus);
        // お客様人数のidを取得
        count_view = findViewById(R.id.tvVisitScreen3);
        // 注文へ進むボタンのidを取得
        Button button_send = findViewById(R.id.btProceedToOrder);

        // プラスボタン押下時の処理
        button_Plus.setOnClickListener(view -> {
            String countView = (count_view.getText()).toString();
            int mCount = Integer.parseInt(countView);
            mCount++;
            count_view.setText(String.valueOf(mCount));
        });

        // マイナスボタン押下時の処理
        button_minus.setOnClickListener(view -> {
            String countView = (count_view.getText()).toString();
            int mCount = Integer.parseInt(countView);
            mCount--;
            count_view.setText(String.valueOf(mCount));
        });

        // 注文へ進むボタン押下時の処理
        button_send.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            startActivity(intent);
        });
    }
}