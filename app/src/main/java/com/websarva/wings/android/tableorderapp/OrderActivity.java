package com.websarva.wings.android.tableorderapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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
    }
}