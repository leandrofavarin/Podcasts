package com.leandrofavarin.podcasts.player;

public interface PlayerObserver {

    public abstract void onPlayerStateChanged(PlayerState state, PlayerInfo info);
}

