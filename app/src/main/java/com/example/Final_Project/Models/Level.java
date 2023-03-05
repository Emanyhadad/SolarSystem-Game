package com.example.Final_Project.Models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Level {
    @PrimaryKey
    private int level_no;
    private int unlock_points;
    float LevelAvg;


    public Level( int level_no , int unlock_points ) {
        this.level_no = level_no;
       this.unlock_points = unlock_points;
    }
    @Ignore
    public Level() {
    }

    public int getLevel_no() {
        return level_no;
    }
    
    public void setLevel_no( int level_no ) {
        this.level_no = level_no;
    }

    public int getUnlock_points() {
        return unlock_points;
    }

    public void setUnlock_points( int unlock_points ) {
        this.unlock_points = unlock_points;
    }

    public float getLevelAvg( ) {
        return LevelAvg;
    }

    public void setLevelAvg( float levelAvg ) {
        LevelAvg = levelAvg;
    }
}
