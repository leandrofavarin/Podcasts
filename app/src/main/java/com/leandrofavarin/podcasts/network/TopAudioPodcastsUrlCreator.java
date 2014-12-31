package com.leandrofavarin.podcasts.network;

public class TopAudioPodcastsUrlCreator extends UrlCreator {

    public TopAudioPodcastsUrlCreator() {
        super();
    }

    public void preprendPath(String pathPrefix) {
        builder.path(pathPrefix);
    }

    @Override
    public String create() {
        builder.appendEncodedPath("rss/topaudiopodcasts/genre=" + PODCASTS_ID + "/json");
        return super.create();
    }
}
