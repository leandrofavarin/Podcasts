package com.leandrofavarin.podcasts.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

public abstract class HideableScrollListener extends RecyclerView.OnScrollListener {

    private static final int HIDE_THRESHOLD = 20;

    private int scrolledDistance;
    private boolean controlVisible = true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (scrolledDistance > HIDE_THRESHOLD && controlVisible) {
            onHide(recyclerView.getContext());
            controlVisible = false;
            scrolledDistance = 0;
        } else if (scrolledDistance < -HIDE_THRESHOLD && !controlVisible) {
            onShow(recyclerView.getContext());
            controlVisible = true;
            scrolledDistance = 0;
        }
        if ((controlVisible && dy > 0) || (!controlVisible && dy < 0)) {
            scrolledDistance += dy;
        }
    }

    public abstract void onHide(Context context);

    public abstract void onShow(Context context);
}
