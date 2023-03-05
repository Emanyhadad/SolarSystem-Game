package com.example.Final_Project.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.Final_Project.Models.Level;
import com.example.Final_Project.Models.Question;
import com.example.Final_Project.Models.User;

import java.util.List;

public class MyRepository {

    private final DAO_User daoUser;
    private final DAO_Level daoLevel;
    private final DAO_Question daoQuestion;

    private final LiveData<List<User>> AllUser;
    private final LiveData<List<Level>> AllLevel;
    private final LiveData<List< Question >> AllQuestions;

    public MyRepository( Application application){
        MyDatabase database = MyDatabase.getDatabase(application);

        daoUser = database.userDao();
        AllUser = daoUser.AllUsers();

        daoLevel = database.levelDao();
        AllLevel = daoLevel.AllLevel();

        daoQuestion = database.mysteryDao();
        AllQuestions = daoQuestion.AllQuestions();
    }

    //For User
    void InsertUser(User user){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> daoUser.InsertUser(user) );
    }
    void UpdateUser(User user){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> daoUser.UpdateUser(user) );
    }
    void DeleteUser( User user){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> daoUser.DeleteUser(user) );
    }
    LiveData<List<User>> AllUser(){
        return daoUser.AllUsers();
    }

    //For Level
    void InsertLevel( Level level){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> daoLevel.InsertLevel(level) );
    }
    void UpdateLevel(Level level){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> daoLevel.UpdateLevel(level) );
    }
    void DeleteLevel(Level level){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> daoLevel.DeleteLevel(level) );
    }
    LiveData<List<Level>> AllLevel(){
        return daoLevel.AllLevel();
    }
    LiveData<List<Float>> getAvg(int avg){
        return daoLevel.getAvg( avg );
    }

    //For Question
    void InsertQuestion( Question question){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> daoQuestion.InsertQuestion(question) );
    }
    void UpdateQuestion( Question question){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> daoQuestion.UpdateQuestion(question) );
    }
    void DeleteQuestion( Question question){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> daoQuestion.DeleteQuestion(question) );
    }
    LiveData<List< Question >> AllQuestion(){
        return daoQuestion.AllQuestions();
    }
    LiveData<List< Question >> getQuestion( int level_no){
        return daoQuestion.getQuestion(level_no);
    }
}
