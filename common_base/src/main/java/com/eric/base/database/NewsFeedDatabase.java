package com.eric.base.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.eric.base.utils.ContextUtils;

@Database(entities = {InfoFlowRecord.class}, version = 1, exportSchema = false)
public abstract class NewsFeedDatabase extends RoomDatabase {
    private static volatile NewsFeedDatabase INSTANCE;

    public static NewsFeedDatabase getInstance() {
        if (INSTANCE == null){
            synchronized (NewsFeedDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ContextUtils.getApplicationContext(),
                            NewsFeedDatabase.class,
                            "com.eric.application1.newsfeed.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    public abstract InfoFlowDao infoFlowDao();
}
