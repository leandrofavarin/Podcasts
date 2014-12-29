package com.leandrofavarin.podcasts.directory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leandrofavarin.podcasts.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> categories;

    public CategoriesAdapter(List<String> items) {
        this.categories = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.cell_category, parent, false);
        return new CellCategoriesViewHolder(view);
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

    static class CellCategoriesViewHolder extends RecyclerView.ViewHolder {

        @InjectView(android.R.id.text1)
        TextView categoryName;

        private CellCategoriesViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
