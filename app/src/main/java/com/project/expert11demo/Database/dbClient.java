/**
 * Created by Maniraj Agarwal on 8/6/20.
 */
package com.project.expert11demo.Database;

import android.content.Context;

import androidx.room.Room;

public class dbClient {

    private Context mCtx;
    private static dbClient mInstance;
    private AppDatabase appDatabase;

    private dbClient(Context mCtx) {
        this.mCtx = mCtx;
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "BooksLibrary").build();
    }

    public static synchronized dbClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new dbClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
