package com.leandrofavarin.podcasts;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.samples.apps.iosched.util.LogUtils;

public class PodcastActivity extends ActionBarActivity {

    private static final String TAG = LogUtils.makeLogTag(PodcastActivity.class);

    public static final String PODCAST_ID = "podcast_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
