package com.websarva.wings.android.tableorderapp.customer.ordermenu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.customer.customerstart.CustomerStartActivity;

import java.util.ArrayList;

public class OrderListingFragment extends Fragment {

    View view;

    Button clerk_call_button;
    Button order_history_button;
    Button bill_button;
    Button order_button;

    ListView list;
    ArrayList<ListItem> list_item = new ArrayList<>();
    OderMenuAdapter oderMenuAdapter;



    /**
     * リストに表示する各アイテムの定義クラス
     */
    public static class ListItem {
        private String mOrderProduct;
        private String mOrderVolume;


        public String getOrderProduct() {
            return mOrderProduct;
        }

        public void setOrderProduct(String order_product) {
            this.mOrderProduct = order_product;
        }

        public String getOrderVolume() {
            return mOrderVolume;
        }

        public void setOrderVolume(String order_volume) {
            this.mOrderVolume = order_volume;
        }
    }

    // BroadcastReceiverの受け取り
    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            String volume = intent.getStringExtra("volume");
            onOrderList(name, volume);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter intentFilter = new IntentFilter("action.add_order_to_list");
        getContext().registerReceiver(myReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        getContext().unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_listing, container, false);

        clerk_call_button = view.findViewById(R.id.button1);
        order_history_button = view.findViewById(R.id.button2);
        bill_button = view.findViewById(R.id.button3);
        order_button = view.findViewById(R.id.button4);
        list = view.findViewById(R.id.order);

        oderMenuAdapter = new OderMenuAdapter(requireContext(), list_item);
        list.setAdapter(oderMenuAdapter);

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

        // order_history_button押下時に注文履歴を表示する
        order_history_button.setOnClickListener(view1 -> {
            // 1.インスタンス化
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            // 2.builderにTitleとMessageを設定
            builder.setTitle("注文履歴")
                    .setMessage("合計：1000円です");
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
            handler.postDelayed(r, 10000);
        });

        bill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1.インスタンス化
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                // 2.builderにTitleとMessageを設定
                builder.setTitle("お会計")
                        .setMessage("100万円になります。");
                // 3.アラートダイアログの取得
                AlertDialog dialog = builder.create();
                // 4.ダイアログを非表示にする
                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    public void run() {
                        dialog.dismiss();

                        Intent intent = new Intent(getContext(), CustomerStartActivity.class);
                        startActivity(intent);
                    }
                };
                // 5.ダイアログの表示
                dialog.show();
                // 6.2秒たったらrインスタンスを呼び出しダイアログを非表示にする
                handler.postDelayed(r, 20000);
            }
        });

        // order_button押下時にリスト内の商品すべてを削除する
        order_button.setOnClickListener(view -> {
            if (list_item.size() != 0) {
                list_item.clear();
                oderMenuAdapter.notifyDataSetChanged();
                // 1.インスタンス化
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                // 2.builderにTitleとMessageを設定
                builder.setTitle(R.string.order_clerk_button);
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
            }
        });

        return view;
    }

    public void onOrderList(String name, String volume) {
        ListItem listItem = new ListItem();
        listItem.setOrderProduct(name);
        listItem.setOrderVolume(volume);
        Log.d("---", "listItem : " + listItem.getOrderProduct() + " : " + listItem.getOrderVolume());
        list_item.add(listItem);
        oderMenuAdapter.notifyDataSetChanged();
    }


}