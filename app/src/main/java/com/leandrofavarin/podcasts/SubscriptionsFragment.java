package com.leandrofavarin.podcasts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.leandrofavarin.podcasts.directory.DirectoryActivity;
import com.leandrofavarin.podcasts.directory.SimpleGridAdapter;
import com.leandrofavarin.podcasts.utils.GridArtworksHelper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SubscriptionsFragment extends TitledFragment {

    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    @InjectView(android.R.id.empty)
    TextView emptyView;

    public static SubscriptionsFragment newInstance() {
        return new SubscriptionsFragment();
    }

    public SubscriptionsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_empty_refreshable_list, container, false);
        ButterKnife.inject(this, rootView);
        final Context context = rootView.getContext();

        setClickableEmptyView();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        updateList(context);

        return rootView;
    }

    private void setClickableEmptyView() {
        emptyView.setLinkTextColor(getResources().getColor(R.color.accent));
        emptyView.setHighlightColor(getResources().getColor(R.color.primary_dark));

        String text = getString(R.string.empty_subscriptions);
        String goToDirectory = getString(R.string.empty_subscriptions_clickable_text);
        int start = text.indexOf(goToDirectory);
        int end = start + goToDirectory.length();

        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new OpenDirectorySpan(), start, end, 0);
        emptyView.setText(spannableString);
        emptyView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private static class OpenDirectorySpan extends ClickableSpan {
        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            context.startActivity(new Intent(context, DirectoryActivity.class));
        }
    }

    private void updateList(Context context) {
        GridArtworksHelper gridArtworksHelper = new GridArtworksHelper(context);
        int columnWidth = gridArtworksHelper.getColumnSize();
        SimpleGridAdapter simpleGridAdapter = new SimpleGridAdapter(context, new ArrayList<Podcast>(), columnWidth);
        simpleGridAdapter.setEmptyView(emptyView);
        recyclerView.setAdapter(simpleGridAdapter);

        animateList();
        animateProgress();
    }

    private void animateList() {
        Context context = recyclerView.getContext();
        long animDuration = context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
        recyclerView.setAlpha(0f);
        recyclerView.animate().alpha(1f).setDuration(animDuration);
    }

    private void animateProgress() {
        Context context = progressBar.getContext();
        long animDuration = context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
        progressBar.setAlpha(1f);
        progressBar.animate().alpha(0f).setDuration(animDuration);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_tab_subscriptions);
    }
}
