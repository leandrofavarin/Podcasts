package com.leandrofavarin.podcasts;

import android.view.View;

public interface Emptiness {

    public abstract void setEmptyView(View emptyView);

    public abstract void updateEmptyView();

    public abstract boolean shouldShowEmptyView();
}
