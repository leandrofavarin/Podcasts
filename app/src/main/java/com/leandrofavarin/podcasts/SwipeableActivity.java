package com.leandrofavarin.podcasts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.common.view.SlidingTabLayout;
import com.google.samples.apps.iosched.util.LogUtils;
import com.leandrofavarin.podcasts.directory.DirectoryPagerAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public abstract class SwipeableActivity extends ActionBarActivity {

    private static final String TAG = LogUtils.makeLogTag(SwipeableActivity.class);

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        ButterKnife.inject(this);

        setupActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<TitledFragment> fragments = getFragments();
        FragmentPagerAdapter adapter = new DirectoryPagerAdapter(this, fragmentManager, fragments);
        viewPager.setAdapter(adapter);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.view_sliding_tab, R.id.text_view);
        slidingTabLayout.setDividerColors(android.R.color.white);
        slidingTabLayout.setSelectedIndicatorColors(Color.WHITE);
        slidingTabLayout.setViewPager(viewPager);
    }

    protected ViewPager getViewPager() {
        return viewPager;
    }

    protected abstract void setupActionBar(Toolbar toolbar);

    protected abstract List<TitledFragment> getFragments();
}
