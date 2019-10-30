package com.inducesmile.taxirental.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.inducesmile.taxirental.fragment.CancelledFragment;
import com.inducesmile.taxirental.fragment.CompleteFragment;
import com.inducesmile.taxirental.fragment.UpcomingFragment;

public class CustomFragmentPageAdapter extends FragmentPagerAdapter {

    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 3;


    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new UpcomingFragment();
            case 1:
                return new CompleteFragment();
            case 2:
                return new CancelledFragment();
        }
        return null;
    }
    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Upcoming";
            case 1:
                return "Complete";
            case 2:
                return "Cancelled";
        }
        return null;
    }
}
