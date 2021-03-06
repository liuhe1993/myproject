package com.eric.base.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.annotation.NonNull;

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
