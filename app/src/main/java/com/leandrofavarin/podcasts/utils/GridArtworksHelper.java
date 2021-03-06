package com.leandrofavarin.podcasts.utils;

import android.content.Context;

import com.google.samples.apps.iosched.util.LogUtils;

public class GridArtworksHelper {

    private static final String TAG = LogUtils.makeLogTag(GridArtworksHelper.class);

    private Context context;
    private int paddingPx;

    public GridArtworksHelper(Context context) {
        this.context = context;
    }

    public void setPadding(int valueInDp) {
        paddingPx = (int) UIUtils.convertDpToPx(context, valueInDp);
    }

    public int getColumnSize() {
        int width = DeviceUtils.getCurrentScreenWidthPx(context);
        int numIntersectionSpaces = (getNumColumns() - 1) + 2;
        int intersectionSpace = paddingPx;
        int totalSpacing = numIntersectionSpaces * intersectionSpace;
        return (width - totalSpacing) / getNumColumns();
    }

    /**
     * Returns a value between 2 and 6 based on device's screen size and orientation.
     */
    public int getNumColumns() {
        int numColumns;
        double screenWidthInches = DeviceUtils.getCurrentScreenWidthInches(context);
        if (screenWidthInches < 3.5) { // [0, 3.5[
            numColumns = 2;
        } else if (screenWidthInches < 5.5) { // [3.5, 5.5[
            numColumns = 3;
        } else if (screenWidthInches < 6) { // [5.5, 6[
            numColumns = 4;
        } else if (screenWidthInches < 7) { // [6, 7[
            numColumns = 5;
        } else { // [7, + oo[
            numColumns = 6;
        }
        LogUtils.LOGV(TAG, "Creating a list with " + numColumns + " columns.");
        return numColumns;
    }
}
