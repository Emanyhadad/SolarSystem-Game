package com.example.Final_Project.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.Final_Project.Models.Question;

import java.util.List;

@Dao
public interface DAO_Question {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void InsertQuestion( Question question);

    @Update
    void UpdateQuestion( Question question);

    @Delete
    void DeleteQuestion( Question question);

    @Query("SELECT * FROM Question")
    LiveData<List< Question >> AllQuestions();

    @Query("SELECT * FROM Question where level_no=:level_no")
    LiveData<List< Question >> getQuestion( int level_no);

}
