package com.leandrofavarin.podcasts.player;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.view.KeyEvent;

import com.google.samples.apps.iosched.util.LogUtils;

public class AudioFocus {

    private Context context;
    private ComponentName remoteControlResponder;
    private AudioManager audioManager;
    private boolean hasAudioFocus;

    public AudioFocus(Context context) {
        this.context = context;
        String className = RemoteControlReceiver.class.getName();
        this.remoteControlResponder = new ComponentName(context.getPackageName(), className);
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void getAudioFocus() {
        // TODO
    }

    public void removeAudioFocus() {
        // TODO
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
                    // TODO send play/pause command
                } else if (keyCode == KeyEvent.KEYCODE_MEDIA_PREVIOUS) {
                    // TODO send previous command
                } else if (keyCode == KeyEvent.KEYCODE_MEDIA_NEXT) {
                    // TODO send next command
                } else if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
                    // TODO send play/pause command
                }
            }
        }
    }
}
