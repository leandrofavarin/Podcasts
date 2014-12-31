package com.leandrofavarin.podcasts.directory;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.common.view.SlidingTabLayout;
import com.google.samples.apps.iosched.util.LogUtils;
import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.TitledFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DirectoryActivity extends ActionBarActivity {

    private static final String TAG = LogUtils.makeLogTag(DirectoryActivity.class);

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    private TopAudioFragment topAudioFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<TitledFragment> fragments = getFragments();
        DirectoryPagerAdapter adapter = new DirectoryPagerAdapter(this, fragmentManager, fragments);
        viewPager.setAdapter(adapter);

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.view_sliding_tab, R.id.text_view);
        mSlidingTabLayout.setDividerColors(android.R.color.white);
        mSlidingTabLayout.setSelectedIndicatorColors(Color.WHITE);
        mSlidingTabLayout.setViewPager(viewPager);

        viewPager.setCurrentItem(fragments.indexOf(topAudioFragment));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_directory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_country_picker:
                openCountryPicker();
                return true;
            case R.id.action_search:
                // TODO
                return true;
            default:
                LogUtils.LOGE(TAG, "Menu item id not recognized.");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private List<TitledFragment> getFragments() {
        List<TitledFragment> fragments = new ArrayList<>();

        TitledFragment categoriesFragment = CategoriesFragment.newInstance();
        fragments.add(categoriesFragment);

        topAudioFragment = TopAudioFragment.newInstance();
        fragments.add(topAudioFragment);

        return fragments;
    }

    private void openCountryPicker() {

    }
}
