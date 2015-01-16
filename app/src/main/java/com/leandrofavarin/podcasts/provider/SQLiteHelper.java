package com.leandrofavarin.podcasts.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.samples.apps.iosched.util.LogUtils;
import com.leandrofavarin.podcasts.R;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = LogUtils.makeLogTag(SQLiteHelper.class);

    // Database versions
    private static final int DRAFT_1 = 1;
    private static final int CURRENT_DATABASE_VERSION = DRAFT_1;

    public SQLiteHelper(Context context) {
        super(context, context.getString(R.string.app_name_simplified) + ".db", null, CURRENT_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableCreator.SUBSCRIPTIONS);
    }

    interface TableCreator {
        String SUBSCRIPTIONS = "";
    }

    interface Tables {
        String SUBSCRIPTIONS = "subscriptions";

        // When tables get deprecated, add them to this list (so they get correctly deleted on database upgrades)
        interface DeprecatedTables {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.LOGW(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
    }
}
