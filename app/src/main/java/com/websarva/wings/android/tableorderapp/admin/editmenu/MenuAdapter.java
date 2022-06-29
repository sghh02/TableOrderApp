package com.websarva.wings.android.tableorderapp.admin.editmenu;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.admin.editmenu.fragment.EditFragment1;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private final ArrayList<String> localDataSet;
    private EditFragment1 editFragment1;

    /**
     * 使用しているビューの種類への参照を提供します。
     * (カスタムViewHolder)を使用します。
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        // ビューに配置されたウィジェットへの参照を保持しておくためのフィールド
        private final TextView textView; // リストの内容
        public ImageButton mbtMove;      // 移動ボタン
        public ImageButton mbtDel;       // 削除ボタン

        public ViewHolder(View view) {
            super(view);

            // 各項目の参照を取得
            textView = view.findViewById(R.id.edit_text_view);
            mbtMove = view.findViewById(R.id.btn_move);
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
     * 2.onCreateViewHolder()で、アイテムのレイアウトXMLファイルをもとにビューホルダーを作成して返す。
     * 3.onBindViewHolder()を呼び出す。そのときに、２で作成したビューホルダーと表示される位置を受け取る。
     * 4.受け取ったビューホルダーに表示するデータを埋め込む
     */

    // 1.onCreateViewHolder()を呼び出す
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 2.onCreateViewHolder()で、アイテムのレイアウトXMLファイルをもとにビューホルダーを作成して返す。
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_menu, parent, false);

        // EditFragment1を取得
//        editFragment1.getParentFragmentManager();

        return new ViewHolder(view);
    }

    // 3.onBindViewHolder()を呼び出す。そのときに、２で作成したビューホルダーと表示される位置を受け取る。
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // 4.受け取ったビューホルダーに表示するデータを埋め込む
        viewHolder.getTextView().setText(localDataSet.get(position));

        // 移動ボタンをタッチ
        viewHolder.mbtMove.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    // 長押しでなく、タッチしてすぐにドラッグ状態にする。
                    editFragment1.itemTouchHelper.startDrag(viewHolder);
                    return true;
                }
                return v.onTouchEvent(event);
            }
        });

        // 削除ボタンをクリック
        viewHolder.mbtDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = viewHolder.getAdapterPosition();
                if (adapterPosition != -1) {
                    localDataSet.remove(adapterPosition);
                    notifyItemRemoved(adapterPosition);
                }
            }
        });
    }

    // アイテム数を取得
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
