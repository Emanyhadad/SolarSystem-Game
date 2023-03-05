package com.example.Final_Project.Activities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.Final_Project.Database.MyViewModel;
import com.example.Final_Project.Models.User;
import com.example.Final_Project.Models.Util;
import com.example.Final_Project.R;
import com.example.Final_Project.Services.NoticesService;
import com.example.Final_Project.Services.SoundService;

public class SplashActivity extends AppCompatActivity {
    public static JobScheduler jobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_splash);

        Util.preferences = getSharedPreferences(Util.SP_NAME, MODE_PRIVATE);
        Util.editor = Util.preferences.edit();

        //for User
        MyViewModel myViewModel = new ViewModelProvider(this).get( MyViewModel.class);
        myViewModel.AllUser().observe(this, users -> {
            if (users.size() == 0) {
                myViewModel.InsertUser(new User(1, Util.DEFAULT_PLAYER ,
                        null, null, null, null));
                Util.editor.putString( Util.User_Name,Util.DEFAULT_PLAYER );
                Util.editor.apply();
            }
        } );

        //for Notification
        if(Util.preferences.getBoolean( Util.SP_Notification ,true)){
            jobService();
        }

        //for SOUND
        Intent intent = new Intent(getBaseContext(), SoundService.class);
        if ( !Util.preferences.getBoolean( Util.SP_SOUND , true ) ) {
            stopService(intent);
        }
        else if ( Util.preferences.getBoolean( Util.SP_SOUND , true ) ) {
            startService(intent);
        }

        //for Last Question
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
//                    if (! (Util.preferences.getInt( Util.FragmentQ,-1 ) <0) ){
//                    Intent intent = new Intent(getBaseContext(), QuestionsActivity.class);
//                    startActivity(intent);
//                }
 //                   else {
                        startActivity( new Intent( getBaseContext(),HomeActivity.class ) );
 //                   }
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
    public void jobService() {
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(getBaseContext(), NoticesService.class);
        JobInfo jobInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            jobInfo = new JobInfo.Builder(1, componentName)
                    .setPeriodic(24*60 * 60 * 1000, JobInfo.getMinFlexMillis())
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .build();
        }
        jobScheduler.schedule(jobInfo);
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