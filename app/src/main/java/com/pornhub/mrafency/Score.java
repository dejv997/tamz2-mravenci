package com.pornhub.mrafency;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Score {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "score")
    public long time;

    public Score(long time) {
        this.time = time;
    }
}
