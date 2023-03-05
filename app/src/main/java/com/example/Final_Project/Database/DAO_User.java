package com.example.Final_Project.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.Final_Project.Models.User;

import java.util.List;

@Dao
public interface DAO_User {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void InsertUser( User user);

    @Update
    void UpdateUser(User user);

    @Delete
    void DeleteUser(User user);

    @Query("SELECT * FROM User")
    LiveData<List<User>> AllUsers();

}
