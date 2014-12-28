package com.leandrofavarin.podcasts.network;

import android.net.Uri;

public abstract class UrlCreator {

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
