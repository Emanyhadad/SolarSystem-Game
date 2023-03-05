package com.example.Final_Project.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.Final_Project.Models.Level;
import com.example.Final_Project.Models.Question;
import com.example.Final_Project.Models.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = { User.class , Level.class , Question.class},
        version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract DAO_User userDao();
    public abstract DAO_Level levelDao();public abstract DAO_Question mysteryDao();

    private static volatile MyDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

     public static MyDatabase getDatabase( final Context context) {
        if (INSTANCE == null) {
            synchronized ( MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "GameDataBase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
