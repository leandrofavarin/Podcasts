package com.leandrofavarin.podcasts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlaylistsFragment extends TitledFragment {

    public static PlaylistsFragment newInstance() {
        return new PlaylistsFragment();
    }

    public PlaylistsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_tab_playlists);
    }
}
