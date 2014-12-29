package com.leandrofavarin.podcasts.directory;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.common.view.SlidingTabLayout;
import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.TitledFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DirectoryActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<TitledFragment> fragments = getFragments();
        DirectoryPagerAdapter adapter = new DirectoryPagerAdapter(this, fragmentManager, fragments);
        mViewPager.setAdapter(adapter);

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.view_sliding_tab, R.id.text_view);
        mSlidingTabLayout.setDividerColors(android.R.color.white);
        mSlidingTabLayout.setSelectedIndicatorColors(Color.WHITE);
        mSlidingTabLayout.setViewPager(mViewPager);

        //TODO mViewPager.setCurrentItem(fragments.indexOf());
    }

    private List<TitledFragment> getFragments() {
        List<TitledFragment> fragments = new ArrayList<>();

        TitledFragment categoriesFragment = CategoriesFragment.newInstance();
        fragments.add(categoriesFragment);

        return fragments;
    }
}
