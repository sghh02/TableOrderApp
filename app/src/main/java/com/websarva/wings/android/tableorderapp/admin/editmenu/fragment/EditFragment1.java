package com.websarva.wings.android.tableorderapp.admin.editmenu.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.websarva.wings.android.tableorderapp.R;
import com.websarva.wings.android.tableorderapp.admin.editmenu.MenuAdapter;

public class EditFragment1 extends Fragment {

    private static final String TAG = Object.class.getName();

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

        // ---> RecyclerView_start
        // RecyclerViewの取得
        recyclerView1 = (RecyclerView) getView().findViewById(R.id.edit_list1);
        recyclerView2 = (RecyclerView) getView().findViewById(R.id.edit_list2);
        recyclerView3 = (RecyclerView) getView().findViewById(R.id.edit_list3);
        recyclerView4 = (RecyclerView) getView().findViewById(R.id.edit_list4);

        //Adapterの設定
        String[] data = new String[20];
        for (int i =0; i <= 20; i++) {
            data[i] = "test" + i;
        }
        Adapter adapter = (Adapter) new MenuAdapter(data);
        recyclerView1.setAdapter((RecyclerView.Adapter) adapter);

        //LayoutManagerの設定
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager);
         // ---> RecyclerView_end

        return inflater.inflate(R.layout.fragment_edit1, container, false);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: called");
        super.onStart();

        // add_menu_button1を取得
        add_menu_button1 = (ImageButton) getView().findViewById(R.id.add_menu_button1);
        add_menu_button1.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Button1を押した！", Toast.LENGTH_SHORT).show());

        // add_menu_button2を取得
        add_menu_button2 = (ImageButton) getView().findViewById(R.id.add_menu_button2);
        add_menu_button2.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Button2を押した！", Toast.LENGTH_SHORT).show());

        // add_menu_button3を取得
        add_menu_button3 = (ImageButton) getView().findViewById(R.id.add_menu_button3);
        add_menu_button3.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Button3を押した", Toast.LENGTH_SHORT).show());

        // add_menu_button4を取得
        add_menu_button4 = (ImageButton) getView().findViewById(R.id.add_menu_button4);
        add_menu_button4.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Button4を押した！", Toast.LENGTH_SHORT).show());
    }
}