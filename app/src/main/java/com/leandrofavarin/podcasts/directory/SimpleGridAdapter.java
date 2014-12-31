package com.leandrofavarin.podcasts.directory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.leandrofavarin.podcasts.Podcast;
import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.network.VolleyRequestQueue;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SimpleGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Podcast> podcasts;
    private ImageLoader imageLoader;
    private static int preferredSize;

    public SimpleGridAdapter(Context context, List<Podcast> items, int columnWidth) {
        this.podcasts = items;
        this.imageLoader = VolleyRequestQueue.getInstance(context).getImageLoader();
        preferredSize = columnWidth;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.cell_simple_grid, parent, false);
        return new CellSimpleGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final Podcast podcast = podcasts.get(position);
        CellSimpleGridViewHolder holder = (CellSimpleGridViewHolder) viewHolder;
        String preferredUrl = podcast.getArtworkProvider().getUrl(preferredSize);
        holder.artwork.setImageUrl(preferredUrl, imageLoader);
    }

    @Override
    public int getItemCount() {
        return podcasts.size();
    }

    static class CellSimpleGridViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.artwork)
        NetworkImageView artwork;

        private CellSimpleGridViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
