package com.leandrofavarin.podcasts.directory;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.leandrofavarin.podcasts.ArtworkProvider;
import com.leandrofavarin.podcasts.Podcast;
import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.TitledFragment;
import com.leandrofavarin.podcasts.network.TopAudioPodcastsUrlCreator;
import com.leandrofavarin.podcasts.network.VolleyRequestQueue;
import com.leandrofavarin.podcasts.utils.GridArtworksHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TopAudioFragment extends TitledFragment implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectView(android.R.id.empty)
    TextView emptyView;
    private int columnWidth;

    public static TopAudioFragment newInstance() {
        return new TopAudioFragment();
    }

    public TopAudioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.inject(this, rootView);
        final Context context = rootView.getContext();

        GridArtworksHelper gridArtworksHelper = new GridArtworksHelper(context);
        int gridPadding = (int) context.getResources().getDimension(R.dimen.grid_padding);
        gridArtworksHelper.setPadding(gridPadding);
        int numColumns = gridArtworksHelper.getNumColumns();
        columnWidth = gridArtworksHelper.getColumnSize();

        emptyView.setText(R.string.empty_top_podcasts);
        recyclerView.setLayoutManager(new GridLayoutManager(context, numColumns));
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.accent);

        TopAudioPodcastsUrlCreator urlCreator = new TopAudioPodcastsUrlCreator();
        urlCreator.preprendPath("us");
        String url = urlCreator.create();

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Podcast> podcasts = new ArrayList<>();
                try {
                    JSONObject feed = response.getJSONObject("feed");
                    JSONArray entries = feed.getJSONArray("entry");
                    int arraySize = entries.length();
                    for (int i = 0; i < arraySize; i++) {
                        JSONObject entry = entries.getJSONObject(i);
                        int id = entry.getJSONObject("id").getJSONObject("attributes").getInt("im:id");
                        Podcast podcast = new Podcast(id);

                        JSONArray imagesArray = entry.getJSONArray("im:image");
                        int imagesSize = imagesArray.length();
                        ArtworkProvider artworkProvider = new ArtworkProvider();
                        for (int j = 0; j < imagesSize; j++) {
                            JSONObject imageJson = imagesArray.getJSONObject(j);
                            String url = imageJson.getString("label");
                            int imageSize = imageJson.getJSONObject("attributes").getInt("height");
                            artworkProvider.addSize(imageSize, url);
                        }

                        podcast.setArtworkProvider(artworkProvider);
                        podcasts.add(podcast);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setupRecyclerView(podcasts);
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

    private void setupRecyclerView(List<Podcast> data) {
        SimpleGridAdapter simpleGridAdapter = new SimpleGridAdapter(recyclerView.getContext(), data, columnWidth);
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
        progressBar.animate().alpha(0f).setDuration(animDuration);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_tab_top_audio);
    }

    @Override
    public void onRefresh() {
    }
}
