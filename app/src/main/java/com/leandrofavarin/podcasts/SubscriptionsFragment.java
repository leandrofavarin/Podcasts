package com.leandrofavarin.podcasts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SubscriptionsFragment extends TitledFragment {

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static SubscriptionsFragment newInstance() {
        return new SubscriptionsFragment();
    }

    public SubscriptionsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_refreshable_recyclerview, container, false);
        ButterKnife.inject(this, rootView);
        final Context context = rootView.getContext();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return rootView;
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_tab_subscriptions);
    }
}
