package com.pornhub.mrafency;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.view.View;

public class DatabaseManager {
    private static AppDatabase database = null;

    public static AppDatabase getDatabase(Context context) {
        if(database == null) {
            database = Room.databaseBuilder(context,
                    AppDatabase.class, "dogless-runner").build();
        }
        return database;
    }
}
