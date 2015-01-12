package com.leandrofavarin.podcasts.directory;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.TitledFragment;
import com.leandrofavarin.podcasts.TitledFragmentsPagerAdapter;

import java.util.List;

public class DirectoryPagerAdapter extends TitledFragmentsPagerAdapter {

    private boolean isLargeScreen;

    public DirectoryPagerAdapter(Context context, FragmentManager fManager, List<TitledFragment> items) {
        super(context, fManager, items);
        this.isLargeScreen = context.getResources().getBoolean(R.bool.largeScreen);
    }

    @Override
    public float getPageWidth(int position) {
        if (isLargeScreen) {
            return (position == 0) ? 0.5f : 1.0f;
        }
        return super.getPageWidth(position);
    }
}
