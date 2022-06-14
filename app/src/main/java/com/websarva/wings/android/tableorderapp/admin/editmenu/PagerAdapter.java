package com.websarva.wings.android.tableorderapp.admin.editmenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.websarva.wings.android.tableorderapp.admin.editmenu.fragment.EditFragment1;
import com.websarva.wings.android.tableorderapp.admin.editmenu.fragment.EditFragment2;
import com.websarva.wings.android.tableorderapp.admin.editmenu.fragment.EditFragment3;
import com.websarva.wings.android.tableorderapp.admin.editmenu.fragment.EditFragment4;
import com.websarva.wings.android.tableorderapp.admin.editmenu.fragment.EditFragment5;

public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new EditFragment2();
            case 2:
                return new EditFragment3();
            case 3:
                return new EditFragment4();
            case 4:
                return new EditFragment5();
            default:
                return new EditFragment1();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}