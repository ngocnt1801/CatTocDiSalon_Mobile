package com.pro.salon.cattocdi.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pro.salon.cattocdi.fragments.ReviewsFragment;
import com.pro.salon.cattocdi.fragments.SalonDetailContactFragment;
import com.pro.salon.cattocdi.fragments.SalonDetailServiceFragment;

public class ProfileTabAdapter extends FragmentPagerAdapter {
    private String title[] = {"DỊCH VỤ", "LIÊN HỆ", "ĐÁNH GIÁ"};
    private boolean isPreview = false;

    public ProfileTabAdapter(FragmentManager fm) {
        super(fm);
    }

    public ProfileTabAdapter(FragmentManager fm, boolean isPreview) {
        super(fm);
        this.isPreview = isPreview;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new SalonDetailServiceFragment(isPreview);
            case 1:
                return new SalonDetailContactFragment(isPreview);
            case 2:
                return new ReviewsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
