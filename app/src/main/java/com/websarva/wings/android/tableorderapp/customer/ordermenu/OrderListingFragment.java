package com.websarva.wings.android.tableorderapp.customer.ordermenu;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.websarva.wings.android.tableorderapp.R;

public class OrderListingFragment extends Fragment {

    Button clerk_call_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_listing, container, false);

        clerk_call_button = view.findViewById(R.id.button1);

        // clerk_call_button押下時にダイアログを表示する
        clerk_call_button.setOnClickListener(view1 -> {
            // 1.インスタンス化
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            // 2.builderにTitleとMessageを設定
            builder.setTitle(R.string.title_clerk_call)
                    .setMessage(R.string.message_clerk_call);
            // 3.アラートダイアログの取得
            AlertDialog dialog = builder.create();
            // 4.ダイアログを非表示にする
            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {
                    dialog.dismiss();
                }
            };
            // 5.ダイアログの表示
            dialog.show();
            // 6.2秒たったらrインスタンスを呼び出しダイアログを非表示にする
            handler.postDelayed(r, 2000);
        });

        return view;
    }
}