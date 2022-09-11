package com.example.demoproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AnhVietFragment();
            case 1:
                return new NguPhapFragment();
            case 2:
                return new DongNghiaFragment();
            default:
                return new AnhVietFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Anh Việt";
                break;
            case 1:
                title = "Ngữ Pháp";
                break;
            case 2:
                title = "Đồng Nghĩa";
                break;
        }
        return title;

    }
}
