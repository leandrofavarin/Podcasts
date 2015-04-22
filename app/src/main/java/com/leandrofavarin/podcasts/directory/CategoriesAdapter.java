package com.leandrofavarin.podcasts.directory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leandrofavarin.podcasts.Emptiness;
import com.leandrofavarin.podcasts.R;
import com.leandrofavarin.podcasts.adapter.OnItemClickListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Emptiness {

    private List<String> categories;
    private View emptyView;
    private OnItemClickListener onItemClickListener;

    private RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            updateEmptyView();
        }
    };

    public CategoriesAdapter(List<String> items) {
        this.categories = items;
        registerAdapterDataObserver(adapterDataObserver);
    }

    public String getItem(int position) {
        return categories.get(position);
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
        final View view = inflater.inflate(R.layout.cell_category, parent, false);
        return new CellCategoriesViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CellCategoriesViewHolder holder = (CellCategoriesViewHolder) viewHolder;
        holder.categoryName.setText(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CellCategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(android.R.id.text1)
        TextView categoryName;

        private OnItemClickListener onItemClickListener;

        private CellCategoriesViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            ButterKnife.inject(this, view);
            this.onItemClickListener = onItemClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getLayoutPosition());
            }
        }
    }
}
