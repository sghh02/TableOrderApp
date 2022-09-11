package com.websarva.wings.android.tableorderapp.customer.ordermenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.websarva.wings.android.tableorderapp.R;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    Context context;
    // 商品番号、商品名、商品価格のArrayList
    ArrayList id, number, name, price;

    String order;
    String s;
    OrderListingFragment orderListingFragment;

    // コンストラクタ
    public MenuAdapter(Context context, ArrayList id, ArrayList number, ArrayList name, ArrayList price) {
        this.context = context;
        this.id = id;
        this.number = number;
        this.name = name;
        this.price = price;
    }

    /**
     * 使用しているビューの種類への参照を提供します。
     * (カスタムViewHolder)を使用します。
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        // ビューに配置されたウィジェットへの参照を保持しておくためのフィールド
        public TextView product_name;  // 商品名
        public TextView product_price; // 商品価格
        public LinearLayout row_menu;  // １行分のメニュー

        public ViewHolder(View view) {
            super(view);

            // 各項目の参照を取得
            product_name = view.findViewById(R.id.name_text);
            product_price = view.findViewById(R.id.price_text);
            row_menu = view.findViewById(R.id.row_list);
        }
    }

    /**
     * RecyclerViewでデータが表示されるまでの流れ
     * 1.onCreateViewHolder()を呼び出す
     * 2.onCreateViewHolder()で、リスト1行分のレイアウトXMLファイルをもとにビューホルダーを作成して返す。
     * 3.onBindViewHolder()を呼び出す。そのときに、２で作成したビューホルダーと表示される位置を受け取る。
     * 4.受け取ったビューホルダーに表示するデータを埋め込む
     */
    // 1.onCreateViewHolder()を呼び出す
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 2.onCreateViewHolder()で、リスト1行分のレイアウトXMLファイルをもとにビューホルダーを作成して返す。
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_tab, parent, false);

        return new ViewHolder(view);
    }

    // 3.onBindViewHolder()を呼び出す。そのときに、２で作成したビューホルダーと表示される位置を受け取る。
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // 4.受け取ったビューホルダーに表示するデータを埋め込む
        viewHolder.product_name.setText(String.valueOf(name.get(position)));
        viewHolder.product_price.setText(price.get(position) + "円");

        // リスト押下時注文の数量を決めるダイアログを表示する
        viewHolder.row_menu.setOnClickListener(view -> {
            order = viewHolder.product_name.getText().toString();
            String[] volume = {"1コ", "2コ", "3コ", "4コ", "5コ"};
            s = "1";

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            orderListingFragment = new OrderListingFragment();
            // builderにTitleとMessageを設定
            builder.setTitle(order + "の数量をお選びください")
                    .setSingleChoiceItems(volume, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i) {
                                case 1:
                                    s = "2";
                                    break;
                                case 2:
                                    s = "3";
                                    break;
                                case  3:
                                    s = "4";
                                    break;
                                case 4:
                                    s = "5";
                                    break;
                                default:
                                    s = "1";
                                    break;
                            }
                        }
                    })
                    .setPositiveButton("注文リストに追加", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent("action.add_order_to_list");
                            intent.putExtra("name", order);
                            intent.putExtra("volume", s);

                            // name&volumeの値確認
                            Log.d("---", "order_name" + " : " + order);
                            Log.d("---", "order_volume" + " : " + s);
                            // name&volumeの値をsendBroadcast
                            context.sendBroadcast(intent);
                        }
                    })
                    .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

            // アラートダイアログの取得
            AlertDialog dialog = builder.create();
            // ダイアログの表示
            dialog.show();
        });
    }

    // アイテム数を取得
    @Override
    public int getItemCount() {
        return name.size();
    }
}
