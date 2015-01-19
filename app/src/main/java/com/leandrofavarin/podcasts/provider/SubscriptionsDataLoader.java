package com.leandrofavarin.podcasts.provider;

import android.content.Context;

import com.leandrofavarin.podcasts.Podcast;

import java.util.List;

public class SubscriptionsDataLoader extends AbstractDataLoader<List<Podcast>> {

    private DataSource<Podcast> dataSource;
    private String selection;
    private String[] selectionArgs;
    private String groupBy;
    private String having;
    private String orderBy;

    public SubscriptionsDataLoader(Context context, DataSource<Podcast> dataSource, String selection,
                                   String[] selectionArgs, String groupBy, String having, String orderBy) {
        super(context);
        this.dataSource = dataSource;
        this.selection = selection;
        this.selectionArgs = selectionArgs;
        this.groupBy = groupBy;
        this.having = having;
        this.orderBy = orderBy;
    }

    @Override
    protected List<Podcast> buildList() {
        return dataSource.read(selection, selectionArgs, groupBy, having, orderBy);
    }

    public void insert(List<Podcast> items) {
        Podcast[] array = items.toArray(new Podcast[items.size()]);
        new InsertTask(this).execute(array);
    }

    public void update(List<Podcast> items) {
        Podcast[] array = items.toArray(new Podcast[items.size()]);
        new UpdateTask(this).execute(array);
    }

    public void delete(List<Podcast> items) {
        Podcast[] array = items.toArray(new Podcast[items.size()]);
        new DeleteTask(this).execute(array);
    }

    private class InsertTask extends ContentChangingTask<Podcast[], Void, Void> {
        InsertTask(SubscriptionsDataLoader loader) {
            super(loader);
        }

        @Override
        protected Void doInBackground(Podcast[]... params) {
            dataSource.insert(params[0]);
            return null;
        }
    }

    private class UpdateTask extends ContentChangingTask<Podcast[], Void, Void> {
        UpdateTask(SubscriptionsDataLoader loader) {
            super(loader);
        }

        @Override
        protected Void doInBackground(Podcast[]... params) {
            dataSource.update(params[0]);
            return null;
        }
    }

    private class DeleteTask extends ContentChangingTask<Podcast[], Void, Void> {
        DeleteTask(SubscriptionsDataLoader loader) {
            super(loader);
        }

        @Override
        protected Void doInBackground(Podcast[]... params) {
            dataSource.delete(params[0]);
            return null;
        }
    }
}