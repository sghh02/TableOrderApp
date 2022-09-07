package com.websarva.wings.android.tableorderapp.customer.ordermenu;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.websarva.wings.android.tableorderapp.R;

import java.util.ArrayList;

public class OrderListingFragment extends Fragment {

    View view;

    Button clerk_call_button;
    Button order_history_button;
    Button bill_button;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order_listing, container, false);

        clerk_call_button = view.findViewById(R.id.button1);
        order_history_button = view.findViewById(R.id.button2);
        bill_button = view.findViewById(R.id.button3);
        list = view.findViewById(R.id.order);

        oderMenuAdapter = new OderMenuAdapter(requireContext(), list_item);
        list.setAdapter(oderMenuAdapter);

        onOrderList(requireContext(), "aaa", "1コ");


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

    public void onOrderList(Context c, String name, String volume) {
        ListItem listItem = new ListItem();
        listItem.setOrderProduct(name);
        listItem.setOrderVolume(volume);
        Log.d("---", "listItem : " + listItem.getOrderProduct() + " : " + listItem.getOrderVolume());
        list_item.add(listItem);
        oderMenuAdapter = new OderMenuAdapter(c, list_item);
        oderMenuAdapter.notifyDataSetChanged();
    }
}