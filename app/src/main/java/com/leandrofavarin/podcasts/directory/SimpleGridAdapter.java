package com.leandrofavarin.podcasts.directory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.leandrofavarin.podcasts.Emptiness;
import com.leandrofavarin.podcasts.Podcast;
import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.adapter.OnItemClickListener;
import com.leandrofavarin.podcasts.network.VolleyRequestQueue;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SimpleGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Emptiness {

    private List<Podcast> podcasts;
    private ImageLoader imageLoader;
    private View emptyView;
    private static int preferredSize;
    private OnItemClickListener onItemClickListener;

    private RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            updateEmptyView();
        }
    };

    public SimpleGridAdapter(Context context, List<Podcast> items, int columnWidth) {
        this.podcasts = items;
        this.imageLoader = VolleyRequestQueue.getInstance(context).getImageLoader();
        preferredSize = columnWidth;
        registerAdapterDataObserver(adapterDataObserver);
    }

    public Podcast getItem(int position) {
        return podcasts.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        updateEmptyView();
    }

    @Override
    public void updateEmptyView() {
        if (emptyView != null) {
            emptyView.setVisibility(shouldShowEmptyView() ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public boolean shouldShowEmptyView() {
        return getItemCount() == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.cell_simple_grid, parent, false);
        return new CellSimpleGridViewHolder(view, onItemClickListener);
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

    static class CellSimpleGridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.artwork)
        NetworkImageView artwork;

        private OnItemClickListener onItemClickListener;

        private CellSimpleGridViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            ButterKnife.inject(this, view);
            this.onItemClickListener = onItemClickListener;

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(artwork.getLayoutParams());
            layoutParams.width = preferredSize;
            layoutParams.height = preferredSize;
            artwork.setLayoutParams(layoutParams);

            artwork.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getLayoutPosition());
            }
        }
    }
}
