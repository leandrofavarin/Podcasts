package com.leandrofavarin.podcasts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.samples.apps.iosched.util.LogUtils;
import com.leandrofavarin.podcasts.directory.DirectoryActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends SwipeableActivity {

    private static final String TAG = LogUtils.makeLogTag(HomeActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    @Override
    protected List<TitledFragment> getFragments() {
        List<TitledFragment> fragments = new ArrayList<>();
        fragments.add(PlaylistsFragment.newInstance());
        fragments.add(SubscriptionsFragment.newInstance());
        return fragments;
    }

    @Override
    protected FragmentPagerAdapter getFragmentPagerAdapter() {
        return new TitledFragmentsPagerAdapter(this, getSupportFragmentManager(), getFragments());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_directory:
                startActivity(new Intent(this, DirectoryActivity.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                LogUtils.LOGE(TAG, "Menu item id not recognized.");
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
