package com.websarva.wings.android.tableorderapp.customer.ordermenu;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.websarva.wings.android.tableorderapp.R;

public class OrderActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.pager2);

        FragmentManager fm = getSupportFragmentManager();
        pagerAdapter = new PagerAdapter(fm, getLifecycle());
        viewPager2.setAdapter(pagerAdapter);
        
         tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
             // タグセレクト時、Positionを取得する
             @Override
             public void onTabSelected(TabLayout.Tab tab) {
                 viewPager2.setCurrentItem(tab.getPosition());
             }
             @Override
             public void onTabUnselected(TabLayout.Tab tab) {

             }
             @Override
             public void onTabReselected(TabLayout.Tab tab) {

             }
         });
         viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
             @Override
             public void onPageSelected(int position) {
                 super.onPageSelected(position);
                 tabLayout.selectTab(tabLayout.getTabAt(position));
             }
         });

        // Android端末のナビゲーションバーを非表示に設定
        View decorView = getWindow().getDecorView();
        // ナビゲーションバーとステータスバーの両方を非表示にします。
        // SYSTEM_UI_FLAG_FULLSCREEN は Android 4.1 以降で利用可能です。
        // 一般的なルールとして、ナビゲーションバーを隠すときは常にステータスバーを隠すようにアプリを設計する必要があります。
        // ナビゲーションバーを非表示にする場合は、ステータスバーを非表示にするようにアプリを設計するのが一般的です。
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // ナビゲーションバーを非表示にする
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION // ナビゲーションバーが非表示である前提でレイアウトする」
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }
}