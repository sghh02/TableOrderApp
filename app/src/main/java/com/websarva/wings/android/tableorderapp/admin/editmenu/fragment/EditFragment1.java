package com.websarva.wings.android.tableorderapp.admin.editmenu.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.admin.editmenu.MenuAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class EditFragment1 extends Fragment {

    private static final String TAG = Object.class.getName();
    // データ格納用のList
    private final ArrayList<String> data = new ArrayList<>();
    public ItemTouchHelper itemTouchHelper;


    ImageButton add_menu_button1;
    ImageButton add_menu_button2;
    ImageButton add_menu_button3;
    ImageButton add_menu_button4;

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called");
        View mview = inflater.inflate(R.layout.fragment_edit1, container, false);

        // ---> RecyclerView_start
        // RecyclerViewの取得
        recyclerView1 = mview.findViewById(R.id.edit_list1);
        recyclerView2 = mview.findViewById(R.id.edit_list2);
        recyclerView3 = mview.findViewById(R.id.edit_list3);
        recyclerView4 = mview.findViewById(R.id.edit_list4);

        //Adapterの設定
        MenuAdapter adapter = new MenuAdapter(data);
        recyclerView1.setAdapter(adapter);

        //LayoutManagerの設定
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(layoutManager);
        // ---> RecyclerView_end


        /**
         * ItemTouchHelperを使いListの長押し時移動、スワイプ時削除を実装
         */
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT) {
                    // 長押しで移動
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        // データを入れ替え
                        Collections.swap(data, fromPos, toPos);
                        // 移動したことを通知
                        adapter.notifyItemMoved(fromPos, toPos);
                        return true;
                    }

                    // スワイプで削除
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        // アイテムを削除
                        data.remove(viewHolder.getAdapterPosition());
                        // 削除したことを通知
                        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                });
        // ItemTouchHelper を RecyclerView にアタッチ
        itemTouchHelper.attachToRecyclerView(recyclerView1);

        /**
         * RecyclerViewにListを追加する処理
         */
        add_menu_button1 = mview.findViewById(R.id.add_menu_button1);
        add_menu_button2 = mview.findViewById(R.id.add_menu_button2);
        add_menu_button3 = mview.findViewById(R.id.add_menu_button3);
        add_menu_button4 = mview.findViewById(R.id.add_menu_button4);

        // add_menu_button1を押下時の処理
        add_menu_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //　新規リストを追加
                data.add("test" + (data.size() + 1));
                // リストを追加したことを通知
                adapter.notifyItemInserted(adapter.getItemCount() + 1);
            }
        });

        add_menu_button2.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Button2を押した！", Toast.LENGTH_SHORT).show());

        add_menu_button3.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Button3を押した", Toast.LENGTH_SHORT).show());

        add_menu_button4.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Button4を押した！", Toast.LENGTH_SHORT).show());

        return mview;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: called");
        super.onStart();


    }
}