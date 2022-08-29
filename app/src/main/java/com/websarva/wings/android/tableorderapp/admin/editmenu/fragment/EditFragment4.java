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
import java.util.HashMap;

public class EditFragment4 extends Fragment {

    private static final String TAG = "EditFragment4";

    ImageButton add_menu_button1;
    ImageButton add_menu_button2;
    ImageButton add_menu_button3;
    ImageButton add_menu_button4;

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;


    ProductOpenHelper productOpenHelper;
    MenuAdapter adapter1, adapter2, adapter3, adapter4;

    HashMap<String, ArrayList<String>> id_map = new HashMap<>();
    HashMap<String, ArrayList<String>> product_id_map = new HashMap<>();
    HashMap<String, ArrayList<String>> product_name_map = new HashMap<>();
    HashMap<String, ArrayList<String>> product_price_map = new HashMap<>();

    Intent intent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called");
        View view = inflater.inflate(R.layout.fragment_edit2, container, false);

        productOpenHelper = new ProductOpenHelper(getContext());

        // RecyclerViewの取得
        recyclerView1 = view.findViewById(R.id.edit_list1);
        recyclerView2 = view.findViewById(R.id.edit_list2);
        recyclerView3 = view.findViewById(R.id.edit_list3);
        recyclerView4 = view.findViewById(R.id.edit_list4);

        // onDataInArraysメソッド呼出し
        onDataInArrays();

        // MenuAdapterコンストラクタを呼出し
        adapter1 = new MenuAdapter(getContext(), id_map.get("1"), product_id_map.get("1"), product_name_map.get("1"), product_price_map.get("1"));
        recyclerView1.setAdapter(adapter1);
        adapter2 = new MenuAdapter(getContext(), id_map.get("2"), product_id_map.get("2"), product_name_map.get("2"), product_price_map.get("2"));
        recyclerView2.setAdapter(adapter2);
        adapter3 = new MenuAdapter(getContext(), id_map.get("3"), product_id_map.get("3"), product_name_map.get("3"), product_price_map.get("3"));
        recyclerView3.setAdapter(adapter3);
        adapter4 = new MenuAdapter(getContext(), id_map.get("4"), product_id_map.get("4"), product_name_map.get("4"), product_price_map.get("4"));
        recyclerView4.setAdapter(adapter4);

        // LayoutManagerの設定
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView4.setLayoutManager(layoutManager4);

        // RecyclerViewにListを追加する処理
        add_menu_button1 = view.findViewById(R.id.add_menu_button1);
        add_menu_button2 = view.findViewById(R.id.add_menu_button2);
        add_menu_button3 = view.findViewById(R.id.add_menu_button3);
        add_menu_button4 = view.findViewById(R.id.add_menu_button4);

        /*
           add_menu_button1を押下時の処理
           MenuAddActivityに遷移
         */
        add_menu_button1.setOnClickListener(v -> {
            intent = new Intent(getActivity(), MenuAddActivity.class);
            intent.putExtra("PRODUCT_NUMBER", 13);
            startActivity(intent);
        });

        add_menu_button2.setOnClickListener(v -> {
            intent = new Intent(getActivity(), MenuAddActivity.class);
            intent.putExtra("PRODUCT_NUMBER", 14);
            startActivity(intent);
        });

        add_menu_button3.setOnClickListener(v -> {
            intent = new Intent(getActivity(), MenuAddActivity.class);
            intent.putExtra("PRODUCT_NUMBER", 15);
            startActivity(intent);
        });

        add_menu_button4.setOnClickListener(v -> {
            intent = new Intent(getActivity(), MenuAddActivity.class);
            intent.putExtra("PRODUCT_NUMBER", 16);
            startActivity(intent);
        });

        return view;
    }

    // readDataで用意したCursorを操作。0番からid,product_id,product_name,product_priceが入っている
    void onDataInArrays() {
        // 初期化
        ArrayList<String> id1 = new ArrayList<>();
        ArrayList<String> id2 = new ArrayList<>();
        ArrayList<String> id3 = new ArrayList<>();
        ArrayList<String> id4 = new ArrayList<>();
        ArrayList<String> product_id1 = new ArrayList<>();
        ArrayList<String> product_id2 = new ArrayList<>();
        ArrayList<String> product_id3 = new ArrayList<>();
        ArrayList<String> product_id4 = new ArrayList<>();
        ArrayList<String> product_name1 = new ArrayList<>();
        ArrayList<String> product_name2 = new ArrayList<>();
        ArrayList<String> product_name3 = new ArrayList<>();
        ArrayList<String> product_name4 = new ArrayList<>();
        ArrayList<String> product_price1 = new ArrayList<>();
        ArrayList<String> product_price2 = new ArrayList<>();
        ArrayList<String> product_price3 = new ArrayList<>();
        ArrayList<String> product_price4 = new ArrayList<>();

        Cursor cursor = productOpenHelper.readData();
        while (cursor.moveToNext()) {
            switch (cursor.getString(1)) {
                case "13":
                    id1.add(cursor.getString(0));
                    product_id1.add(cursor.getString(1));
                    product_name1.add(cursor.getString(2));
                    product_price1.add(cursor.getString(3));
                    break;
                case "14":
                    id2.add(cursor.getString(0));
                    product_id2.add(cursor.getString(1));
                    product_name2.add(cursor.getString(2));
                    product_price2.add(cursor.getString(3));
                    break;
                case "15":
                    id3.add(cursor.getString(0));
                    product_id3.add(cursor.getString(1));
                    product_name3.add(cursor.getString(2));
                    product_price3.add(cursor.getString(3));
                    break;
                case "16":
                    id4.add(cursor.getString(0));
                    product_id4.add(cursor.getString(1));
                    product_name4.add(cursor.getString(2));
                    product_price4.add(cursor.getString(3));
                    break;
            }
        }

        // product_idごとにHashMapに格納
        for (int i = 1; i < 4; i++) {
            switch (i) {
                case 1:
                    id_map.put("1", id1);
                    product_id_map.put("1", product_id1);
                    product_name_map.put("1", product_name1);
                    product_price_map.put("1", product_price1);
                case 2:
                    id_map.put("2", id2);
                    product_id_map.put("2", product_id2);
                    product_name_map.put("2", product_name2);
                    product_price_map.put("2", product_price2);
                case 3:
                    id_map.put("3", id3);
                    product_id_map.put("3", product_id3);
                    product_name_map.put("3", product_name3);
                    product_price_map.put("3", product_price3);
                case 4:
                    id_map.put("4", id4);
                    product_id_map.put("4", product_id4);
                    product_name_map.put("4", product_name4);
                    product_price_map.put("4", product_price4);
            }
        }
    }
}