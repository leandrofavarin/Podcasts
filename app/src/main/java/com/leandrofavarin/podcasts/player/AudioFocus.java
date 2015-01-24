package com.leandrofavarin.podcasts.player;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.view.KeyEvent;

import com.google.samples.apps.iosched.util.LogUtils;

// TODO implement MediaSession on API 21+
public class AudioFocus {

    private static final String TAG = LogUtils.makeLogTag(AudioFocus.class);

    private Context context;
    private ComponentName remoteControlResponder;
    private AudioManager audioManager;
    private boolean hasAudioFocus;

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                            context.startService(new Intent(AudioPlayer.ACTION_PAUSE));
                            break;
                        case AudioManager.AUDIOFOCUS_GAIN:
                            getAudioFocus();
                            context.startService(new Intent(AudioPlayer.ACTION_RAISE_VOLUME));
                            context.startService(new Intent(AudioPlayer.ACTION_PLAY_PAUSE));
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS:
                            removeAudioFocus();
                            context.startActivity(new Intent(AudioPlayer.ACTION_PAUSE));
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            context.startActivity(new Intent(AudioPlayer.ACTION_LOWER_VOLUME));
                            break;
                    }
                }
            };

    public AudioFocus(Context context) {
        this.context = context;
        String className = RemoteControlReceiver.class.getName();
        this.remoteControlResponder = new ComponentName(context.getPackageName(), className);
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void getAudioFocus() {
        int result = audioManager.requestAudioFocus(
                audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            LogUtils.LOGV(TAG, "Received audio focus.");
            audioManager.registerMediaButtonEventReceiver(remoteControlResponder);
            hasAudioFocus = true;
        } else if (result == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
            LogUtils.LOGV(TAG, "Failed to received audio focus.");
            audioManager.unregisterMediaButtonEventReceiver(remoteControlResponder);
            hasAudioFocus = false;
        }
    }

    public void removeAudioFocus() {
        LogUtils.LOGV(TAG, "Lost audio focus.");
        audioManager.abandonAudioFocus(audioFocusChangeListener);
        audioManager.unregisterMediaButtonEventReceiver(remoteControlResponder);
    }

    public static class RemoteControlReceiver extends BroadcastReceiver {
        private static final String TAG = LogUtils.makeLogTag(RemoteControlReceiver.class);

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
                KeyEvent event = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                if (event.getAction() == KeyEvent.ACTION_UP) {
                    // The system always send two events (ACTION_UP and ACTION_DOWN)
                    // Returning early prevents the command from being processed twice
                    return;
                }
                int keyCode = event.getKeyCode();
                LogUtils.LOGD(TAG, "Received keyCode: " + keyCode);
                if (keyCode == KeyEvent.KEYCODE_MEDIA_PLAY) {
                    context.startService(new Intent(AudioPlayer.ACTION_PLAY_PAUSE));
                } else if (keyCode == KeyEvent.KEYCODE_MEDIA_PREVIOUS) {
                    context.startService(new Intent(AudioPlayer.ACTION_PREVIOUS));
                } else if (keyCode == KeyEvent.KEYCODE_MEDIA_NEXT) {
                    context.startService(new Intent(AudioPlayer.ACTION_NEXT));
                } else if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
                    context.startService(new Intent(AudioPlayer.ACTION_PLAY_PAUSE));
                }
            }
        }
    }
}
