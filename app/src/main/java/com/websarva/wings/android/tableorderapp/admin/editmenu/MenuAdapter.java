package com.websarva.wings.android.tableorderapp.admin.editmenu;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.websarva.wings.android.tableorderapp.R;

import java.util.ArrayList;
import java.util.Collections;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private final ArrayList<String> localDataSet;

    /**
     * 使用しているビューの種類への参照を提供します。
     * (カスタムViewHolder)を使用します。
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        // ビューに配置されたウィジェットへの参照を保持しておくためのフィールド
        private final TextView textView; // リストの内容
        public ImageButton mbtMoveUp;    // 上移動ボタン
        public ImageButton mbtMoveDown;  // 下移動ボタン
        public ImageButton mbtDel;       // 削除ボタン

        public ViewHolder(View view) {
            super(view);

            // 各項目の参照を取得
            textView = view.findViewById(R.id.edit_text_view);
            mbtMoveUp = view.findViewById(R.id.btn_move_up);
            mbtMoveDown = view.findViewById(R.id.btn_move_down);
            mbtDel = view.findViewById(R.id.btn_del);
        }

        public TextView getTextView() {
            return textView;
        }
    }
    public MenuAdapter(ArrayList<String> dataset) {
        localDataSet = dataset;
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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_menu, parent, false);

        return new ViewHolder(view);
    }

    // 3.onBindViewHolder()を呼び出す。そのときに、２で作成したビューホルダーと表示される位置を受け取る。
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // 4.受け取ったビューホルダーに表示するデータを埋め込む
        viewHolder.getTextView().setText(localDataSet.get(position));

        // 上移動ボタンをクリック
        viewHolder.mbtMoveUp.setOnClickListener(view -> {
            // このViewHolderによって表されるアイテムの、それをバインドしたものに対するアダプターの位置を返します
            int adapterPosition = viewHolder.getBindingAdapterPosition();
            if (adapterPosition != 0) {
                Collections.swap(localDataSet, adapterPosition, adapterPosition - 1);
                notifyItemMoved(adapterPosition, adapterPosition - 1);
            }

        });

        // 下移動ボタンをクリック
        viewHolder.mbtMoveDown.setOnClickListener(view -> {
            // このViewHolderによって表されるアイテムの、それをバインドしたものに対するアダプターの位置を返します
            int adapterPosition = viewHolder.getBindingAdapterPosition();
            if (adapterPosition != localDataSet.size() - 1) {
                Collections.swap(localDataSet, adapterPosition, adapterPosition + 1);
                notifyItemMoved(adapterPosition, adapterPosition + 1);
            }
        });

        // 削除ボタンをクリック
        viewHolder.mbtDel.setOnClickListener(v -> {
            // このViewHolderによって表されるアイテムの、それをバインドしたものに対するアダプターの位置を返します
            int adapterPosition = viewHolder.getBindingAdapterPosition();
            if (adapterPosition != -1) {
                localDataSet.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
            }
        });
    }

    // アイテム数を取得
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
