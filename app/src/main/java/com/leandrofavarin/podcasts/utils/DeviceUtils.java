package com.leandrofavarin.podcasts.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DeviceUtils {

    private DeviceUtils() {
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }

    public static int getCurrentScreenWidthPx(Context context) {
        Point size = new Point();
        getDefaultDisplay(context).getSize(size);
        return size.x;
    }

    public static double getDiagonalScreenInches(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        return Math.sqrt(x + y);
    }

    public static double getCurrentScreenHeightInches(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        return dm.heightPixels / dm.ydpi;
    }

    public static double getCurrentScreenWidthInches(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        return dm.widthPixels / dm.xdpi;
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        getDefaultDisplay(context).getMetrics(dm);
        return dm;
    }

    private static Display getDefaultDisplay(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }
}
