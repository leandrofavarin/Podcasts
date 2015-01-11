package com.leandrofavarin.podcasts.player;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class AudioPlayer extends Service implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener {

    public static final String INTENT_BASE_NAME = "com.leandrofavarin.player.AudioPlayer";
    public static final String ACTION_PREVIOUS = INTENT_BASE_NAME + ".ACTION_PREVIOUS";
    public static final String ACTION_PLAY_PAUSE = INTENT_BASE_NAME + ".ACTION_PLAY_PAUSE";
    public static final String ACTION_NEXT = INTENT_BASE_NAME + ".ACTION_NEXT";
    public static final String ACTION_STOP = INTENT_BASE_NAME + ".ACTION_STOP";

    private MediaPlayer mediaPlayer;
    private IBinder audioPlayerBinder;
    private AudioPlayerBroadcastReceiver broadcastReceiver;

    private PhoneStateListener phoneStateListener = new PhoneStateListener() {
        private boolean wasPlaying;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == TelephonyManager.CALL_STATE_RINGING) {
                wasPlaying = (mediaPlayer != null) && mediaPlayer.isPlaying();
                pause();
            } else if (state == TelephonyManager.CALL_STATE_IDLE) {
                if (wasPlaying) {
                    start();
                }
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    };

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

        monitorPhoneState();
        setupIntentFilters();
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

    private void monitorPhoneState() {
        TelephonyManager mgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (mgr != null) {
            mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    private void setupIntentFilters() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_PREVIOUS);
        intentFilter.addAction(ACTION_PLAY_PAUSE);
        intentFilter.addAction(ACTION_NEXT);
        intentFilter.addAction(ACTION_STOP);
        broadcastReceiver = new AudioPlayerBroadcastReceiver();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * Called by the system to notify a Service that it is no longer used and is being removed.
     * The service should clean up any resources it holds (threads, registered receivers, etc)
     * at this point. Upon return, there will be no more calls in to this Service object and it
     * is effectively dead. Do not call this method directly.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMonitorPhoneState();
        unregisterReceiver(broadcastReceiver);
    }

    private void stopMonitorPhoneState() {
        TelephonyManager mgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (mgr != null) {
            mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
        }
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

    private void start() {
        mediaPlayer.start();
    }

    private void pause() {
        mediaPlayer.pause();
    }

    private class AudioPlayerBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case ACTION_PREVIOUS:
                    break;
                case ACTION_PLAY_PAUSE:
                    break;
                case ACTION_NEXT:
                    break;
                case ACTION_STOP:
                    break;
                default:
                    break;
            }
        }

    }
}
