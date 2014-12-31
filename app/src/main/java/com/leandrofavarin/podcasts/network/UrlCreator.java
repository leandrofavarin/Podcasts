package com.leandrofavarin.podcasts.network;

import android.net.Uri;

public abstract class UrlCreator {

    // http://www.apple.com/itunes/affiliates/resources/documentation/genre-mapping.html
    protected static final int PODCASTS_ID = 26;

    protected Uri.Builder builder;

    public UrlCreator() {
        builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("itunes.apple.com");
    }

    public String create() {
        return builder.build().toString();
    }
}
