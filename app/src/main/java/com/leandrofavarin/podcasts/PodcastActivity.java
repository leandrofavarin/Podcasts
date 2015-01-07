package com.leandrofavarin.podcasts;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.samples.apps.iosched.ui.widget.ObservableScrollView;
import com.google.samples.apps.iosched.util.LogUtils;

import java.security.InvalidParameterException;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PodcastActivity extends ActionBarActivity implements ObservableScrollView.Callbacks {

    private static final String TAG = LogUtils.makeLogTag(PodcastActivity.class);

    public static final String PODCAST_ID = "podcast_id";

    @InjectView(R.id.scroll_view)
    ObservableScrollView scrollView;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);
        ButterKnife.inject(this);

        Bundle extras = getIntent().getExtras();
        if (!extras.containsKey(PODCAST_ID)) {
            throw new InvalidParameterException("Intent does not contain key \"" + PODCAST_ID + "\"");
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        scrollView.addCallbacks(this);
    }

    @Override
    public void onScrollChanged(int deltaX, int deltaY) {

    }
}
