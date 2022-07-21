package com.websarva.wings.android.tableorderapp.admin.editmenu.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.admin.editmenu.MenuAdapter;
import com.websarva.wings.android.tableorderapp.admin.editmenu.MenuAddActivity;
import com.websarva.wings.android.tableorderapp.database.DBContract.ProductEntry;
import com.websarva.wings.android.tableorderapp.database.ProductOpenHelper;

import java.util.ArrayList;
import java.util.Collections;

public class EditFragment1 extends Fragment {

    private static final String TAG = "EditFragment1";

    public ItemTouchHelper itemTouchHelper;

    ImageButton add_menu_button1;
    ImageButton add_menu_button2;
    ImageButton add_menu_button3;
    ImageButton add_menu_button4;

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;

    ProductOpenHelper productOpenHelper;
    MenuAdapter adapter;
    ArrayList<String> product_id, product_name, product_price;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("producoOpenHelper", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called");
        View view = inflater.inflate(R.layout.fragment_edit1, container, false);

        productOpenHelper = new ProductOpenHelper(getContext());

        // RecyclerViewの取得
        recyclerView1 = view.findViewById(R.id.edit_list1);
        recyclerView2 = view.findViewById(R.id.edit_list2);
        recyclerView3 = view.findViewById(R.id.edit_list3);
        recyclerView4 = view.findViewById(R.id.edit_list4);

        product_id = new ArrayList<>();
        product_name = new ArrayList<>();
        product_price = new ArrayList<>();

        // DataInArraysメソッド呼出し
        DataInArrays();

        // MenuAdapterコンストラクタを呼出し
        adapter = new MenuAdapter(getContext(), product_id, product_name, product_price);
        recyclerView1.setAdapter(adapter);

        // LayoutManagerの設定
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(layoutManager);

        /**
         * ItemTouchHelperを使いListの長押し時移動、スワイプ時削除を実装
         */
//        itemTouchHelper = new ItemTouchHelper(
//                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
//                        ItemTouchHelper.LEFT) {
//                    // 長押しで移動
//                    @Override
//                    public boolean onMove(@NonNull RecyclerView recyclerView,
//                                          @NonNull RecyclerView.ViewHolder viewHolder,
//                                          @NonNull RecyclerView.ViewHolder target) {
//                        final int fromPos = viewHolder.getBindingAdapterPosition();
//                        final int toPos = target.getBindingAdapterPosition();
//                        // データを入れ替え
//                        Collections.swap(data, fromPos, toPos);
//                        // 移動したことを通知
//                        adapter.notifyItemMoved(fromPos, toPos);
//                        return true;
//                    }
//
//                    // スワイプで削除
//                    @Override
//                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                        // アイテムを削除
//                        data.remove(viewHolder.getBindingAdapterPosition());
//                        // 削除したことを通知
//                        adapter.notifyItemRemoved(viewHolder.getBindingAdapterPosition());
//                    }
//        });

        // ItemTouchHelper を RecyclerView にアタッチ
//        itemTouchHelper.attachToRecyclerView(recyclerView1);

        /** RecyclerViewにListを追加する処理 */
        add_menu_button1 = view.findViewById(R.id.add_menu_button1);
        add_menu_button2 = view.findViewById(R.id.add_menu_button2);
        add_menu_button3 = view.findViewById(R.id.add_menu_button3);
        add_menu_button4 = view.findViewById(R.id.add_menu_button4);

        /**
         *  add_menu_button1を押下時の処理
         *  MenuAddActivityに遷移
         */
        add_menu_button1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MenuAddActivity.class);
            startActivity(intent);
        });

        add_menu_button2.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Button2を押した！", Toast.LENGTH_SHORT).show());

        add_menu_button3.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Button3を押した", Toast.LENGTH_SHORT).show());

        add_menu_button4.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Button4を押した！", Toast.LENGTH_SHORT).show());

        return view;
    }

    // readDataで用意したCursorを操作。1番から始まっているのは0番にはidが入ってるため
    void DataInArrays() {
        Cursor cursor = productOpenHelper.readData();
        while (cursor.moveToNext()) {
            product_id.add(cursor.getString(1));
            product_name.add(cursor.getString(2));
            product_price.add(cursor.getString(3));
        }
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: called");
        super.onStart();


    }
}