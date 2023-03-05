package com.example.Final_Project.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.Final_Project.Adapter_Listener_Dialog.AdapterLevel;
import com.example.Final_Project.Database.MyViewModel;
import com.example.Final_Project.Json.Read_Pars_Insert;
import com.example.Final_Project.Models.Level;
import com.example.Final_Project.Models.Util;
import com.example.Final_Project.databinding.ActivityLevelsBinding;

import java.util.ArrayList;
import java.util.List;

public class LevelsActivity extends AppCompatActivity {
    ActivityLevelsBinding binding;
    List<Level> level;

    @SuppressLint( "NotifyDataSetChanged" )
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ALUserName.setText( Util.DEFAULT_PLAYER );
        binding.ALtvScore.setText(String.valueOf(Util.preferences.getInt( Util.Score , 0   )));

        MyViewModel myViewModel = new ViewModelProvider(this).get( MyViewModel.class);

        String language = getApplicationContext().getResources().getConfiguration().locale.getLanguage();
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && language.equals("en")) {
            // Language is English
            String assets = Read_Pars_Insert.readFromAssets(getApplicationContext(), "json/jsonEnglish.json" );
            Read_Pars_Insert p = new Read_Pars_Insert(this);
            p.readJson(assets);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && language.equals("ar")) {
            // Language is Arabic
            String assets = Read_Pars_Insert.readFromAssets(getApplicationContext(), "json/jsonArabic.json" );
            Read_Pars_Insert p = new Read_Pars_Insert(this);
            p.readJson(assets);
        }


        level= new ArrayList<>();
        myViewModel.AllLevel().observe(this, levels -> {
            level = levels;
            AdapterLevel adapterLevel = new AdapterLevel(getApplicationContext(),level, position -> {
                Intent intent = new Intent(getBaseContext(), QuestionsActivity.class);
                intent.putExtra( Util.LevelNo,position);
                startActivity(intent);
                finish();
            } );
            binding.StartPlayingRecycler.setAdapter(adapterLevel);
            binding.StartPlayingRecycler.setLayoutManager
                    (new GridLayoutManager( getApplicationContext(),3 ) );
            adapterLevel.notifyDataSetChanged();
        } );
    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Intent intent = new Intent(getBaseContext(), SoundService.class);
//        stopService(intent);
//    }

//    @Override
//    protected void onPause( ) {
//        super.onPause( );
//        Intent intent = new Intent(getBaseContext(), SoundService.class);
//        stopService(intent);    }
}