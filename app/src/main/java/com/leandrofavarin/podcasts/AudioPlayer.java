package com.leandrofavarin.podcasts;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class AudioPlayer extends Service implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener {

    private MediaPlayer mediaPlayer;
    private IBinder audioPlayerBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.setOnPreparedListener(this);
        audioPlayerBinder = new AudioPlayerBinder();
    }

    public class AudioPlayerBinder extends Binder {
        public AudioPlayer getService() {
            // Return this instance of LocalService so clients can call public methods
            return AudioPlayer.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return audioPlayerBinder;
    }

    /**
     * Although you should usually implement either onBind() or onStartCommand(), it's sometimes
     * necessary to implement both. For example, a music player might find it useful to allow its
     * service to run indefinitely and also provide binding. This way, an activity can start the
     * service to play some music and the music continues to play even if the user leaves the
     * application. Then, when the user returns to the application, the activity can bind to the
     * service to regain control of playback.
     * http://developer.android.com/guide/components/bound-services.html
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
