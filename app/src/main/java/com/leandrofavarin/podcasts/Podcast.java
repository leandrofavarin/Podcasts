package com.leandrofavarin.podcasts;

public class Podcast {

    private int id;
    private ArtworkProvider artworkProvider;

    public Podcast(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setArtworkProvider(ArtworkProvider artworkProvider) {
        this.artworkProvider = artworkProvider;
    }

    public ArtworkProvider getArtworkProvider() {
        return artworkProvider;
    }
}
