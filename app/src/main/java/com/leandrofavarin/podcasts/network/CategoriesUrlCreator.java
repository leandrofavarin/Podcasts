package com.leandrofavarin.podcasts.network;

public class CategoriesUrlCreator extends UrlCreator {

    public CategoriesUrlCreator() {
        super();
        builder.path("WebObjects/MZStoreServices.woa/ws/genres");
        builder.appendQueryParameter("id", String.valueOf(PODCASTS_ID));
    }
}
