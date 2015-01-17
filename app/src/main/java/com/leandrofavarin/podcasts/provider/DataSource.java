package com.leandrofavarin.podcasts.provider;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public abstract class DataSource<T> {

    protected SQLiteDatabase database;

    public DataSource(SQLiteDatabase database) {
        this.database = database;
    }

    abstract boolean insert(T[] items);

    abstract boolean delete(T[] items);

    abstract boolean update(T[] items);

    abstract List<T> read();

    abstract List<T> read(String selection, String[] selectionArgs, String groupBy, String having, String orderBy);
}