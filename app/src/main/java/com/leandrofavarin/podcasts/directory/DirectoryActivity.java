package com.leandrofavarin.podcasts.directory;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.samples.apps.iosched.util.LogUtils;
import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.SwipeableActivity;
import com.leandrofavarin.podcasts.TitledFragment;

import java.util.ArrayList;
import java.util.List;

public class DirectoryActivity extends SwipeableActivity {

    private static final String TAG = LogUtils.makeLogTag(DirectoryActivity.class);

    private List<TitledFragment> fragments;
    private TopAudioFragment topAudioFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewPager().setCurrentItem(fragments.indexOf(topAudioFragment));
    }

    @Override
    protected void setupActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected List<TitledFragment> getFragments() {
        createListFragments();
        return fragments;
    }

    private void createListFragments() {
        List<TitledFragment> fragments = new ArrayList<>();
        TitledFragment categoriesFragment = CategoriesFragment.newInstance();
        fragments.add(categoriesFragment);
        topAudioFragment = TopAudioFragment.newInstance();
        fragments.add(topAudioFragment);
        this.fragments = fragments;
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

    private void openCountryPicker() {

    }
}
