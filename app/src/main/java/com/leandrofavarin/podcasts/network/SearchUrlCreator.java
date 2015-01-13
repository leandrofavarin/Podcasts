package com.leandrofavarin.podcasts.network;

public class SearchUrlCreator extends UrlCreator {

    public SearchUrlCreator(String searchTerm) {
        super();
        builder.path("search");
        builder.appendQueryParameter("media", "podcast");
        builder.appendQueryParameter("term", searchTerm);
    }
}
