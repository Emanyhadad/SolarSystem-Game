package com.example.Final_Project.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.Final_Project.Models.Level;
import com.example.Final_Project.Models.Question;
import com.example.Final_Project.Models.User;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    MyRepository myRepository;

    public MyViewModel( @NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
    }

    //For User
    public void InsertUser( User user){
        myRepository.InsertUser(user);
    }
    public void UpdateUser(User user){
        myRepository.UpdateUser(user);
    }
    public void DeleteUser(User user){
        myRepository.DeleteUser(user);
    }
    public LiveData<List<User>> AllUser(){
        return myRepository.AllUser();
    }

    //For Level
    public void InsertLevel(Level level){
        myRepository.InsertLevel(level);
    }
    void UpdateLevel(Level level){
        myRepository.UpdateLevel(level);
    }
    void DeleteLevel(Level level){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> myRepository.DeleteLevel(level) );
    }
    public LiveData<List< Level >> AllLevel(){
        return myRepository.AllLevel();
    }
    public LiveData<List< Float >> getAvg(int avg){
        return myRepository.getAvg( avg );
    }

    //For Question
    public void InsertQuestion( Question question) {
        MyDatabase.databaseWriteExecutor.execute( ( ) -> myRepository.InsertQuestion(question) );
    }
    void UpdateQuestion( Question question){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> myRepository.UpdateQuestion(question) );
    }
    void DeleteQuestion( Question question){
        MyDatabase.databaseWriteExecutor.execute( ( ) -> myRepository.DeleteQuestion(question) );
    }
    public LiveData<List< Question >> AllQuestion(){
        return myRepository.AllQuestion();
    }

    public LiveData<List< Question >> getQuestion( int level_no){
        return myRepository.getQuestion(level_no);
    }

}
