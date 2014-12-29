package com.leandrofavarin.podcasts.directory;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.TitledFragment;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CategoriesFragment extends TitledFragment implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager linearLayoutManager;

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    public CategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.inject(this, rootView);

        linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        setupRecyclerView();

        return rootView;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(linearLayoutManager);
        String[] testData = new String[] { "Lorem", "ipsum", "dolor", "sit amet", "consectetur",
                "adipiscing", "elit", "Quisque", "dapibus", "placerat", "convallis", "Nam",
                "viverra", "magna", "nunc" };
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(Arrays.asList(testData));
        recyclerView.setAdapter(categoriesAdapter);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_tab_categories);
    }

    @Override
    public void onRefresh() {
    }
}
