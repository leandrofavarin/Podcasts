package com.leandrofavarin.podcasts.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class UIUtils {

    private UIUtils() {
    }

    public static float convertDpToPx(Context context, int valueInDp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, displayMetrics);
    }

    public static float convertPxToDp(Context context, float valueInPx) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return valueInPx / (displayMetrics.densityDpi / 160f);
    }
}
