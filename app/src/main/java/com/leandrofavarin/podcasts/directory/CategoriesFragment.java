package com.leandrofavarin.podcasts.directory;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.TitledFragment;

public class CategoriesFragment extends TitledFragment {

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    public CategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_tab_categories);
    }
}
