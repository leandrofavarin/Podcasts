package com.leandrofavarin.podcasts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SubscriptionsFragment extends TitledFragment {

    public static SubscriptionsFragment newInstance() {
        return new SubscriptionsFragment();
    }

    public SubscriptionsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_tab_subscriptions);
    }
}
