package com.pornhub.mrafency;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ScoreDao {
    @Query("SELECT * FROM score ORDER BY score ASC")
    List<Score> getAll();

    @Insert
    void insert(Score score);
}
