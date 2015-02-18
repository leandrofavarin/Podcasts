package com.leandrofavarin.podcasts;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public final class ArtworkProvider {

    private final NavigableMap<Integer, String> artworks = new TreeMap<>();

    public ArtworkProvider() {
    }

    public void addSize(int size, @NonNull String url) {
        artworks.put(size, url);
    }

    public int getArtworksCount() {
        return artworks.size();
    }

    @Nullable
    public String getUrl() {
        return getUrl(0);
    }

    @Nullable
    public String getUrl(final int preferredSize) {
        if (artworks.isEmpty()) {
            return null;
        }
        if (preferredSize <= 0) {
            return artworks.lastEntry().getValue();
        }
        Map.Entry<Integer, String> entry = artworks.ceilingEntry(preferredSize);
        if (entry == null) {
            entry = artworks.floorEntry(preferredSize);
        }
        return entry.getValue();
    }
}
