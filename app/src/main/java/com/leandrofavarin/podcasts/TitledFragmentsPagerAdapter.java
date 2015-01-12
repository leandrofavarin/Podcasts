package com.leandrofavarin.podcasts;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TitledFragmentsPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<TitledFragment> fragments;

    public TitledFragmentsPagerAdapter(Context context, FragmentManager fManager, List<TitledFragment> items) {
        super(fManager);
        this.context = context;
        this.fragments = items;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle(context);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
