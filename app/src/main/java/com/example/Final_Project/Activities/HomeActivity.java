package com.example.Final_Project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.Final_Project.Database.MyViewModel;
import com.example.Final_Project.Models.User;
import com.example.Final_Project.Models.Util;
import com.example.Final_Project.R;
import com.example.Final_Project.Services.SoundService;
import com.example.Final_Project.databinding.ActivityHomeBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.firebase.analytics.FirebaseAnalytics;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    private FirebaseAnalytics firebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAnalytics=FirebaseAnalytics.getInstance( this );

        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd( adRequest );




        MyViewModel myViewModel = new ViewModelProvider(this).get( MyViewModel.class);
        myViewModel.AllUser().observe(this, users -> {
            if (users.size() == 0) {
                myViewModel.InsertUser(new User(1, Util.DEFAULT_PLAYER ,
                        null, null, null, null));
                Util.editor.putString( Util.User_Name,Util.DEFAULT_PLAYER );
            }
        } );
        binding.HABtnLevel.startAnimation( AnimationUtils.loadAnimation( getBaseContext(), R.anim.button ));
        binding.HABtnAccount.startAnimation( AnimationUtils.loadAnimation( getBaseContext(), R.anim.button ));
        binding.HABtnSetting.startAnimation(    AnimationUtils.loadAnimation( getBaseContext(), R.anim.button ));


        binding.HABtnLevel.setOnClickListener( view ->{
                startActivity(new Intent(getBaseContext(), LevelsActivity.class));
//        finish();
    });

        binding.HABtnAccount.setOnClickListener( v -> {
            startActivity( new Intent( getBaseContext(),AccountActivity.class ) );
            Bundle bundle = new Bundle(  );
            bundle.putString( "User_Name",Util.preferences.getString( Util.User_Name,Util.DEFAULT_PLAYER ) );
            firebaseAnalytics.logEvent( "Account", bundle);
//            AdRequest adRequest = new AdRequest.Builder().build();
//            binding.adView.loadAd(adRequest);
            //            finish();
        } );
        binding.HABtnSetting.setOnClickListener( view ->{
        startActivity(new Intent(getBaseContext(), SettingsActivity.class)); });
//        finish();


        binding.AHBtnLogout.setOnClickListener( v -> finish() );
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(getBaseContext(), SoundService.class);
        stopService(intent);
    }

//    @Override
//    protected void onPause( ) {
//        super.onPause( );
//        Intent intent = new Intent(getBaseContext(), SoundService.class);
//        stopService(intent);    }
}
