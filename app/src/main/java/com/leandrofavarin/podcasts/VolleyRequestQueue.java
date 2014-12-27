package com.leandrofavarin.podcasts;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.google.samples.apps.iosched.util.LogUtils;

/**
 * Singleton that interfaces with Volley's RequestQueue.
 * <p/>
 * It is possible to change Volley's log with:
 * <p/>
 * $ <code>adb shell setprop log.tag.Volley VERBOSE (System.getProperty())</code>
 */
public class VolleyRequestQueue {

    private static final String TAG = LogUtils.makeLogTag(VolleyRequestQueue.class);

    private static VolleyRequestQueue instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private Context context;

    private VolleyRequestQueue(Context context) {
        this.context = context;
        this.requestQueue = getRequestQueue();

        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        };
        this.imageLoader = new ImageLoader(requestQueue, imageCache);
    }

    public static synchronized VolleyRequestQueue getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new VolleyRequestQueue(context);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> Request<T> addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        return getRequestQueue().add(request);
    }

    public <T> Request<T> addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        return getRequestQueue().add(request);
    }

    public void cancelPendingRequests(String tag) {
        tag = TextUtils.isEmpty(tag) ? TAG : tag;
        getRequestQueue().cancelAll(tag);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

}
