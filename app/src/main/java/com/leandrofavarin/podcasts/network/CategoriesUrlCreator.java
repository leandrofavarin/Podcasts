package com.leandrofavarin.podcasts.network;

public class CategoriesUrlCreator extends UrlCreator {

    // http://www.apple.com/itunes/affiliates/resources/documentation/genre-mapping.html
    private static final int PODCASTS_ID = 26;

    public CategoriesUrlCreator() {
        super();
        builder.path("WebObjects/MZStoreServices.woa/ws/genres");
        builder.appendQueryParameter("id", String.valueOf(PODCASTS_ID));
    }
}
