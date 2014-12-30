package com.leandrofavarin.podcasts.utils;

import android.content.Context;

import com.google.samples.apps.iosched.util.LogUtils;

public class GridArtworksHelper {

    private static final String TAG = LogUtils.makeLogTag(GridArtworksHelper.class);

    private Context context;
    private int columnSize;
    private int numItems;
    private int paddingPx;
    private int paddingDp;

    public GridArtworksHelper(Context context, int paddingDp) {
        this.context = context;
        this.paddingDp = paddingDp;
        setPadding(context, paddingDp);
    }

    public void setPadding(Context context, int valueInDp) {
        if (valueInDp >= 0) {
            paddingPx = (int) UIUtils.convertDpToPx(context, valueInDp);
        }
    }

    /**
     * @return the value set in the constructor.
     */
    public int getPadding() {
        return paddingDp;
    }

    public int getColumnSize() {
        if (columnSize == 0) {
            int width = DeviceUtils.getCurrentScreenWidthPx(context);
            int numIntersectionSpaces = (numItems - 1) + 2;
            int intersectionSpace = paddingPx;
            int totalSpacing = numIntersectionSpaces * intersectionSpace;
            columnSize = (width - totalSpacing) / numItems;
        }
        return columnSize;
    }

    /**
     * Returns a value between 2 and 6 based on device's screen size and orientation.
     */
    public int getNumColumns() {
        if (numItems == 0) {
            double screenWidthInches = DeviceUtils.getCurrentScreenWidthInches(context);
            if (screenWidthInches < 3.5) { // [0, 3.5[
                numItems = 2;
            } else if (screenWidthInches < 5.5) { // [3.5, 5.5[
                numItems = 3;
            } else if (screenWidthInches < 6) { // [5.5, 6[
                numItems = 4;
            } else if (screenWidthInches < 7) { // [6, 7[
                numItems = 5;
            } else { // [7, + oo[
                numItems = 6;
            }
            LogUtils.LOGV(TAG, "Creating a list with " + numItems + " columns.");
        }
        return numItems;
    }
}
