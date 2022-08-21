package com.websarva.wings.android.tableorderapp.admin.editmenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.database.ProductOpenHelper;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    Context context;
    // 商品番号、商品名、商品価格のArrayList
    ArrayList id, number, name, price;

    // コンストラクタ
    public MenuAdapter(Context context, ArrayList id,ArrayList number, ArrayList name, ArrayList price) {
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
        public ImageButton btMoveUp;   // 上移動ボタン
        public ImageButton btMoveDown; // 下移動ボタン
        public ImageButton btDel;      // 削除ボタン

        public ViewHolder(View view) {
            super(view);

            // 各項目の参照を取得
            product_name = view.findViewById(R.id.name_text);
            product_price = view.findViewById(R.id.price_text);
            btMoveUp = view.findViewById(R.id.btn_move_up);
            btMoveDown = view.findViewById(R.id.btn_move_down);
            btDel = view.findViewById(R.id.btn_del);
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
        View view = inflater.inflate(R.layout.row_menu, parent, false);

        return new ViewHolder(view);
    }

    // 3.onBindViewHolder()を呼び出す。そのときに、２で作成したビューホルダーと表示される位置を受け取る。
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // 4.受け取ったビューホルダーに表示するデータを埋め込む
        viewHolder.product_name.setText(String.valueOf(name.get(position)));
        viewHolder.product_price.setText(String.valueOf(price.get(position) + "円"));

        // 上移動ボタンをクリック
        viewHolder.btMoveUp.setOnClickListener(view -> {
            // このViewHolderによって表されるアイテムの、それをバインドしたものに対するアダプターの位置を返します
            int adapterPosition = viewHolder.getBindingAdapterPosition();

            // Listの一番上の行でない場合実行可
            if (adapterPosition != 0) {
                notifyItemMoved(adapterPosition, adapterPosition - 1);
            }

        });

        // 下移動ボタンをクリック
        viewHolder.btMoveDown.setOnClickListener(view -> {
            // このViewHolderによって表されるアイテムの、それをバインドしたものに対するアダプターの位置を返します
            int adapterPosition = viewHolder.getBindingAdapterPosition();

            // Listの一番下の行でない場合実行可
            if (adapterPosition != getItemCount() - 1) {
                notifyItemMoved(adapterPosition, adapterPosition + 1);
            }
        });

        // リスト押下時データの編集を行う
        viewHolder.product_name.setOnClickListener(view -> {
            // インテントを生成
            Intent intent = new Intent(context, MenuEditActivity.class);

            String edit_id = String.valueOf(id.get(position));
            String edit_name = viewHolder.product_name.getText().toString();
            String edit_price = viewHolder.product_price.getText().toString();
            // 上で取得した文字列から最後の円を削除し、edit_priceに再代入
            edit_price = edit_price.substring(0, edit_price.length()-1);

            intent.putExtra("id", edit_id);
            intent.putExtra("name", edit_name);
            intent.putExtra("price", edit_price);

            context.startActivity(intent);
        });

        // 削除ボタンをクリック
        viewHolder.btDel.setOnClickListener(v -> {
            // このViewHolderによって表されるアイテムの、それをバインドしたものに対するアダプターの位置を返します
            int adapterPosition = viewHolder.getBindingAdapterPosition();

            // 削除するレコードの商品名をString型で取得
            String delete_text = viewHolder.product_name.getText().toString();

            Log.d("--->", adapterPosition + ":" + delete_text);

            /**　productOpenHelper.deleteDataを呼び出しデータベースか選択したレコードの削除 */
            ProductOpenHelper productOpenHelper = new ProductOpenHelper(context);
            productOpenHelper.deleteData(delete_text);

            /**
             * リストからも削除されているように見えるようにArrayListのnameからも削除
             * name.remove(adapterPosition)
             * →ArrayListのnameから削除したpositionにある値を削除
             * notifyItemRemoved(adapterPosition)
             * →adapterPositionの位置にあった項目がデータセットから削除されたことを通知
             */
            name.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
        });
    }

    // アイテム数を取得
    @Override
    public int getItemCount() {
        return name.size();
    }
}
