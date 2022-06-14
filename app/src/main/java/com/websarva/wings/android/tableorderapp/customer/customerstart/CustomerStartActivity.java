package com.websarva.wings.android.tableorderapp.customer.customerstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.customer.ordermenu.OrderActivity;

public class CustomerStartActivity extends AppCompatActivity {

    // お客様人数の表示場所
    private TextView count_view;
    // お客様の人数
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_start);

        // プラスボタンidを取得
        Button button_Plus = findViewById(R.id.btPlus);
        // マイナスボタンidを取得
        Button button_minus = findViewById(R.id.btMinus);
        // お客様人数のidを取得
        count_view = findViewById(R.id.tvVisitScreen3);
        // 注文へ進むボタンのidを取得
        Button button_send = findViewById(R.id.btProceedToOrder);

        // プラスボタン押下時の処理
        button_Plus.setOnClickListener(view -> {
            String countView = (count_view.getText()).toString();
            mCount = Integer.parseInt(countView);
            mCount++;
            count_view.setText(String.valueOf(mCount));
        });

        // マイナスボタン押下時の処理
        button_minus.setOnClickListener(view -> {
            String countView = (count_view.getText()).toString();
            mCount = Integer.parseInt(countView);

            // 1より大きい数値ならマイナスボタンを押せる
            if (mCount > 1) {
                mCount--;
                count_view.setText(String.valueOf(mCount));
            }
        });

        // 注文へ進むボタン押下時の処理
        button_send.setOnClickListener(view -> {
            Intent intent = new Intent(CustomerStartActivity.this, OrderActivity.class);
            startActivity(intent);
        });

        // Android端末のナビゲーションバーを非表示に設定
        View decorView = getWindow().getDecorView();
        // ナビゲーションバーとステータスバーの両方を非表示にします。
        // SYSTEM_UI_FLAG_FULLSCREEN は Android 4.1 以降で利用可能です。
        // 一般的なルールとして、ナビゲーションバーを隠すときは常にステータスバーを隠すようにアプリを設計する必要があります。
        // ナビゲーションバーを非表示にする場合は、ステータスバーを非表示にするようにアプリを設計するのが一般的です。
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // ナビゲーションバーを非表示にする
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION // ナビゲーションバーが非表示である前提でレイアウトする」
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }


    // Android端末標準である、バックボタンの処理を何もしないに設定
//    @Override
//    public void onBackPressed() {
//    }
}