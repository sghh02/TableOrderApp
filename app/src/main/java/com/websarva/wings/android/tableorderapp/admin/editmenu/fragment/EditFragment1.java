package com.websarva.wings.android.tableorderapp.admin.editmenu.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.admin.editmenu.MenuAdapter;
import com.websarva.wings.android.tableorderapp.admin.editmenu.MenuAddActivity;
import com.websarva.wings.android.tableorderapp.database.ProductOpenHelper;

import java.util.ArrayList;

public class EditFragment1 extends Fragment {

    private static final String TAG = "EditFragment1";

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
    ArrayList<String> id, product_id, product_name, product_price;
    Intent intent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        id = new ArrayList<>();
        product_id = new ArrayList<>();
        product_name = new ArrayList<>();
        product_price = new ArrayList<>();

        // onDataInArraysメソッド呼出し
        onDataInArrays();

        // MenuAdapterコンストラクタを呼出し
        adapter = new MenuAdapter(getContext(), id, product_id, product_name, product_price);
        recyclerView1.setAdapter(adapter);
        recyclerView2.setAdapter(adapter);
        recyclerView3.setAdapter(adapter);
        recyclerView4.setAdapter(adapter);

        // LayoutManagerの設定
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView4.setLayoutManager(layoutManager4);

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
            intent = new Intent(getActivity(), MenuAddActivity.class);
            intent.putExtra("PRODUCT_NUMBER", 1);
            startActivity(intent);
        });

        add_menu_button2.setOnClickListener(v -> {
            intent = new Intent(getActivity(), MenuAddActivity.class);
            intent.putExtra("PRODUCT_NUMBER", 2);
            startActivity(intent);
        });

        add_menu_button3.setOnClickListener(v -> {
            intent = new Intent(getActivity(), MenuAddActivity.class);
            intent.putExtra("PRODUCT_NUMBER", 3);
            startActivity(intent);
        });

        add_menu_button4.setOnClickListener(v -> {
            intent = new Intent(getActivity(), MenuAddActivity.class);
            intent.putExtra("PRODUCT_NUMBER", 4);
            startActivity(intent);
        });

        return view;
    }

    // readDataで用意したCursorを操作。0番からid,product_id,product_name,product_priceが入っている
    void onDataInArrays() {
        Cursor cursor = productOpenHelper.readData();
        while (cursor.moveToNext()) {
            id.add(cursor.getString(0));
            product_id.add(cursor.getString(1));
            product_name.add(cursor.getString(2));
            product_price.add(cursor.getString(3));
        }
    }
}