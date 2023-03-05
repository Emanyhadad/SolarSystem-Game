package com.example.Final_Project.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.Final_Project.Models.Level;

import java.util.List;

@Dao
public interface DAO_Level {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void InsertLevel( Level level);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void UpdateLevel(Level level);

    @Delete
    void DeleteLevel(Level level);

    @Query("SELECT * FROM Level")
    LiveData<List<Level>> AllLevel();

    @Query("SELECT LevelAvg FROM Level where level_no=:level_no")
    LiveData<List<Float>> getAvg(int level_no);

}
