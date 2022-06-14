package com.websarva.wings.android.tableorderapp.customer.ordermenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.websarva.wings.android.tableorderapp.customer.ordermenu.fragment.TabFragment1;
import com.websarva.wings.android.tableorderapp.customer.ordermenu.fragment.TabFragment2;
import com.websarva.wings.android.tableorderapp.customer.ordermenu.fragment.TabFragment3;
import com.websarva.wings.android.tableorderapp.customer.ordermenu.fragment.TabFragment4;
import com.websarva.wings.android.tableorderapp.customer.ordermenu.fragment.TabFragment5;

public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new TabFragment2();
            case 2:
                return new TabFragment3();
            case 3:
                return new TabFragment4();
            case 4:
                return new TabFragment5();
            default:
                return new TabFragment1();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
