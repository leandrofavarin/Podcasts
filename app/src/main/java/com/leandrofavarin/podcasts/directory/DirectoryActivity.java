package com.leandrofavarin.podcasts.directory;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.example.android.common.view.SlidingTabLayout;
import com.leandrofavarin.podcasts.R;

import java.util.ArrayList;
import java.util.List;

public class DirectoryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = getFragments();
        DirectoryPagerAdapter adapter = new DirectoryPagerAdapter(this, fragmentManager, fragments);
        mViewPager.setAdapter(adapter);

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.view_sliding_tab, R.id.text_view);
        mSlidingTabLayout.setDividerColors(android.R.color.white);
        mSlidingTabLayout.setSelectedIndicatorColors(Color.WHITE);
        mSlidingTabLayout.setViewPager(mViewPager);

        //TODO mViewPager.setCurrentItem(fragments.indexOf());
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();

        CategoriesFragment categoriesFragment = new CategoriesFragment();
        fragments.add(categoriesFragment);

        return fragments;
    }
}
