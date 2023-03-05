package com.example.Final_Project.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Final_Project.Models.Util;
import com.example.Final_Project.R;
import com.example.Final_Project.databinding.ActivityHistoryBinding;

public class HistoryActivity extends AppCompatActivity {
ActivityHistoryBinding binding;
    @SuppressLint( "SetTextI18n" )
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding=ActivityHistoryBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );

        binding.AHTvCLevel2.setText( String.valueOf( Util.preferences.getInt( Util.CountLevel,0 ) ) );

        binding.HATvCQ.setText( String.valueOf( Util.preferences.getInt( Util.CountQus,0 ) ) );
        binding.HATvCTQ.setText( String.valueOf(
                Util.preferences.getInt( Util.CountTQus,0 ) ));
        binding.HATvCFQ.setText(
                String.valueOf( Util.preferences.getInt( Util.CountFQus,0 )));

        Animation animation = AnimationUtils.loadAnimation( this, R.anim.splash );
        binding.imageView7.setAnimation( animation );

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