package com.example.Final_Project.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
@TypeConverters({DateConverter.class})
public class User {
    @PrimaryKey
    public int id;
    @NonNull
    public String UserName;
    public String email;
    public String birthday;
    public String Gender;
    public String country;

    public User( int id , @NonNull String userName , String email , String birthday , String gender , String country ) {
        this.id = id;
        UserName = userName;
        this.email = email;
        this.birthday = birthday;
        Gender = gender;
        this.country = country;
    }

    public User( ) {
    }
}
