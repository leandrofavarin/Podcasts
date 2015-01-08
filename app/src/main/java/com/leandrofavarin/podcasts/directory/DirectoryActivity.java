package com.leandrofavarin.podcasts.directory;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.samples.apps.iosched.util.LogUtils;
import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.SwipeableActivity;
import com.leandrofavarin.podcasts.TitledFragment;

import java.util.ArrayList;
import java.util.List;

public class DirectoryActivity extends SwipeableActivity implements SearchView.OnQueryTextListener {

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

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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
                return true;
            default:
                LogUtils.LOGE(TAG, "Menu item id not recognized.");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void openCountryPicker() {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
