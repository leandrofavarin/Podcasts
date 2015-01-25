package com.leandrofavarin.podcasts.player;

public enum PlayerState {

    /**
     * The Player is empty, and no more songs are in the queue.
     */
    EMPTY,

    /**
     * State in which the Player is going to start, but it hasn't even buffered yet.
     * Could happen when transitioning to another media.
     */
    PREPARING,

    /**
     * Self explanatory. Could be used to set callbacks to update the SeekBar.
     */
    PLAYING,

    /**
     * Mode in which the player could be protected from interaction.
     */
    ADVERTISING,

    /**
     * Self explanatory.
     */
    PAUSED,

    /**
     * Self explanatory. Could be used to block play and pause interactions.
     */
    BUFFERING
}
