package com.leandrofavarin.podcasts.provider;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

// References:
// http://www.androiddesignpatterns.com/2012/08/implementing-loaders.html
// http://www.phloxblog.in/android-custom-loader-load-data-sqlite-database-android-version-1-6
abstract class AbstractDataLoader<E extends List<?>> extends AsyncTaskLoader<E> {

    protected E lastDataList;

    protected abstract E buildList();

    public AbstractDataLoader(Context context) {
        // Loaders may be used across multiple Activities (assuming they aren't
        // bound to the LoaderManager), so NEVER hold a reference to the context
        // directly. Doing so will cause you to leak an entire Activity's context.
        // The superclass constructor will store a reference to the Application
        // Context instead, and can be retrieved with a call to getContext().
        super(context);
    }

    /**
     * Runs on a worker thread, loading in our data.
     * Delegates the real work to concrete subclass' buildList() method.
     */
    @Override
    public E loadInBackground() {
        return buildList();
    }

    /**
     * Runs on the UI thread, routing the results from the background thread to
     * whatever is using the dataList.
     */
    @Override
    public void deliverResult(E data) {
        if (isReset()) {
            // An AsyncTask came in while the loader is stopped.
            releaseResources(data);
            return;
        }

        // Hold a reference to the old data so it doesn't get garbage collected.
        // We must protect it until the new data has been delivered.
        E oldDataList = lastDataList;
        lastDataList = data;

        if (isStarted()) {
            // If the Loader is in a started state, deliver the results to the
            // client. The superclass method does this for us.
            super.deliverResult(data);
        }

        // Invalidate the old data as we don't need it any more.
        if (oldDataList != null && oldDataList != data && oldDataList.size() > 0) {
            releaseResources(oldDataList);
        }
    }

    /**
     * Starts an asynchronous load of the list data. When the result is ready the callbacks
     * will be called on the UI thread. If a previous load has been completed and is still valid
     * the result may be passed to the callbacks immediately.
     * <p/>
     * Must be called from the UI thread.
     */
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (lastDataList != null) {
            // Deliver any previously loaded data immediately.
            deliverResult(lastDataList);
        }

        if (takeContentChanged() || lastDataList == null || lastDataList.size() == 0) {
            // When the observer detects a change, it should call onContentChanged()
            // on the Loader, which will cause the next call to takeContentChanged()
            // to return true. If this is ever the case (or if the current data is
            // null), we force a new load.
            forceLoad();
        }
    }

    /**
     * Must be called from the UI thread, triggered by a call to stopLoading().
     */
    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        // Attempt to cancel the current load task if possible.
        cancelLoad();

        // Note that we leave the observer as is. Loaders in a stopped state
        // should still monitor the data source for changes so that the Loader
        // will know to force a new load if it is ever started again.
    }

    /**
     * Must be called from the UI thread, triggered by a call to cancel(). Here,
     * we make sure our Cursor is closed, if it still exists and is not already
     * closed.
     */
    @Override
    public void onCanceled(E data) {
        super.onCanceled(data);

        // The load has been canceled, so we should release the resources
        // associated with 'data'.
        releaseResources(data);
    }

    /**
     * Must be called from the UI thread, triggered by a call to reset(). Here, we make sure
     * our Cursor is closed, if it still exists and is not already closed.
     */
    @Override
    protected void onReset() {
        super.onReset();
        // Ensure the loader has been stopped.
        onStopLoading();

        // At this point we can release the resources associated with 'mData'.
        if (lastDataList != null && lastDataList.size() > 0) {
            releaseResources(lastDataList);
        }
        lastDataList = null;
    }

    protected void releaseResources(E dataList) {
        if (dataList != null && dataList.size() > 0) {
            dataList.clear();
        }
    }
}