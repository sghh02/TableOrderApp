package com.websarva.wings.android.tableorderapp.customer.ordermenu.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.customer.ordermenu.MenuAdapter;
import com.websarva.wings.android.tableorderapp.database.ProductOpenHelper;

import java.util.ArrayList;

public class TabFragment3 extends Fragment {

    private static final String TAG = "TabFragment3";

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;

    ArrayList<String> id1, id2, id3, id4,
            product_id1, product_id2, product_id3, product_id4,
            product_name1, product_name2, product_name3, product_name4,
            product_price1, product_price2, product_price3, product_price4;

    ProductOpenHelper productOpenHelper;
    MenuAdapter adapter1, adapter2, adapter3, adapter4;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called");
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        productOpenHelper = new ProductOpenHelper(getContext());

        // RecyclerViewの取得
        recyclerView1 = view.findViewById(R.id.list_item1);
        recyclerView2 = view.findViewById(R.id.list_item2);
        recyclerView3 = view.findViewById(R.id.list_item3);
        recyclerView4 = view.findViewById(R.id.list_item4);

        id1 = new ArrayList<>();
        id2 = new ArrayList<>();
        id3 = new ArrayList<>();
        id4 = new ArrayList<>();
        product_id1 = new ArrayList<>();
        product_id2 = new ArrayList<>();
        product_id3 = new ArrayList<>();
        product_id4 = new ArrayList<>();
        product_name1 = new ArrayList<>();
        product_name2 = new ArrayList<>();
        product_name3 = new ArrayList<>();
        product_name4 = new ArrayList<>();
        product_price1 = new ArrayList<>();
        product_price2 = new ArrayList<>();
        product_price3 = new ArrayList<>();
        product_price4 = new ArrayList<>();

        // onDataInArraysメソッド呼出し
        onDataInArrays();

        // MenuAdapterコンストラクタを呼出し
        adapter1 = new MenuAdapter(getContext(), id1, product_id1, product_name1, product_price1);
        recyclerView1.setAdapter(adapter1);
        adapter2 = new MenuAdapter(getContext(), id2, product_id2, product_name2, product_price2);
        recyclerView2.setAdapter(adapter2);
        adapter3 = new MenuAdapter(getContext(), id3, product_id3, product_name3, product_price3);
        recyclerView3.setAdapter(adapter3);
        adapter4 = new MenuAdapter(getContext(), id4, product_id4, product_name4, product_price4);
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

        return view;
    }

    // readDataで用意したCursorを操作。0番からid,product_id,product_name,product_priceが入っている
    void onDataInArrays() {
        Cursor cursor = productOpenHelper.readData();
        while (cursor.moveToNext()) {
            switch (cursor.getString(1)) {
                case "9":
                    id1.add(cursor.getString(0));
                    product_id1.add(cursor.getString(1));
                    product_name1.add(cursor.getString(2));
                    product_price1.add(cursor.getString(3));
                    break;
                case "10":
                    id2.add(cursor.getString(0));
                    product_id2.add(cursor.getString(1));
                    product_name2.add(cursor.getString(2));
                    product_price2.add(cursor.getString(3));
                    break;
                case "11":
                    id3.add(cursor.getString(0));
                    product_id3.add(cursor.getString(1));
                    product_name3.add(cursor.getString(2));
                    product_price3.add(cursor.getString(3));
                    break;
                case "12":
                    id4.add(cursor.getString(0));
                    product_id4.add(cursor.getString(1));
                    product_name4.add(cursor.getString(2));
                    product_price4.add(cursor.getString(3));
                    break;
            }
        }
    }
}