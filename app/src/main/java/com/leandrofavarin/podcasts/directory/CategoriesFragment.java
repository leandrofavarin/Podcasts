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
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.TitledFragment;
import com.leandrofavarin.podcasts.network.CategoriesUrlCreator;
import com.leandrofavarin.podcasts.network.VolleyRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CategoriesFragment extends TitledFragment implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    public CategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.inject(this, rootView);
        final Context context = rootView.getContext();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.accent);

        CategoriesUrlCreator categoriesUrlCreator = new CategoriesUrlCreator();
        String url = categoriesUrlCreator.create();

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<String> genres = new ArrayList<>();
                try {
                    String podcastJsonKey = (String) response.names().get(0);
                    JSONObject podcastJson = response.getJSONObject(podcastJsonKey);
                    JSONObject subgenres = podcastJson.getJSONObject("subgenres");
                    for (Iterator<String> iterator = subgenres.keys(); iterator.hasNext();) {
                        String value = iterator.next();
                        JSONObject subgenre = subgenres.getJSONObject(value);
                        genres.add(subgenre.getString("name"));
                    }
                    Collections.sort(genres);
                    setupRecyclerView(genres);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, errorListener);

        VolleyRequestQueue.getInstance(context).addToRequestQueue(request);

        return rootView;
    }

    private void setupRecyclerView(List<String> data) {
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(data);
        recyclerView.setAdapter(categoriesAdapter);

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
        progressBar.animate().alpha(0f).setDuration(animDuration);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_tab_categories);
    }

    @Override
    public void onRefresh() {
    }
}
