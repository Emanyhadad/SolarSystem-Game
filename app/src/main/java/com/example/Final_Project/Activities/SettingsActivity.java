package com.example.Final_Project.Activities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.Final_Project.Database.MyViewModel;
import com.example.Final_Project.Models.User;
import com.example.Final_Project.Models.Util;
import com.example.Final_Project.R;
import com.example.Final_Project.Services.NoticesService;
import com.example.Final_Project.Services.Notification;
import com.example.Final_Project.Services.SoundService;
import com.example.Final_Project.databinding.ActivitySettingsBinding;


public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding binding;
    private boolean sound = false;
    public static boolean Notices = false;
    Intent  intent;
    public static JobScheduler jobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intent = new Intent(getBaseContext(), SoundService.class);
        MyViewModel myViewModel = new ViewModelProvider(this).get( MyViewModel.class);


        if( Util.preferences.getBoolean( Util.SP_SOUND , true ) ){
            binding.ASButSound.setButtonDrawable( R.drawable.soundaudioon );
        }
        else {binding.ASButSound.setButtonDrawable( R.drawable.soundaudiooff );}

        binding.ASButSound.setOnClickListener( view -> {
            if (!sound) {
                Util.editor.putBoolean(Util.SP_SOUND, true).apply();
                binding.ASButSound.setButtonDrawable( R.drawable.soundaudioon );
                startService(intent);
                sound = true;
            } else {
                Util.editor.putBoolean(Util.SP_SOUND, false).apply();
                binding.ASButSound.setButtonDrawable( R.drawable.soundaudiooff );
                stopService(intent);
                sound = false;
            }
        } );

        binding.ASButDelete.setOnClickListener( v -> myViewModel.AllUser().observe(this, users -> {
            if (users.size() == 1) {
                myViewModel.DeleteUser(users.get( 0 ) );
                Util.editor.clear();
                myViewModel.InsertUser(new User(1, Util.DEFAULT_PLAYER ,
                        null, null, null, null));
                Util.editor.putString( Util.User_Name,Util.DEFAULT_PLAYER );
                Util.editor.apply();
                Toast.makeText( this , "Delete Done" , Toast.LENGTH_SHORT ).show( );
            }
        } ) );

//        binding.SettingProfile.setOnClickListener( view ->
//                startActivity(new Intent(getBaseContext(), AccountActivity.class)) );

        if( Util.preferences.getBoolean( Util.SP_Notification , true ) ){
            binding.ASButNotices.setButtonDrawable( R.drawable.notifications );
        }
        else {binding.ASButNotices.setButtonDrawable( R.drawable.notificationsoff );}

        binding.ASButNotices.setOnClickListener( v -> {
//            NotificationManager notificationManager =
//                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if ( !Notices ){
//                if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
//                notificationManager.setInterruptionFilter
//                        (NotificationManager.INTERRUPTION_FILTER_NONE);
//                cancelJob();
//                updateJob();   }
                Util.editor.putBoolean(Util.SP_Notification, true).apply();
                binding.ASButNotices.setButtonDrawable( R.drawable.notifications );
                jobService();
                Notices = true;

            }else {
//                if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
//                    notificationManager.setInterruptionFilter
//                            (NotificationManager.INTERRUPTION_FILTER_UNKNOWN);
                Util.editor.putBoolean(Util.SP_Notification, false).apply();
                binding.ASButNotices.setButtonDrawable( R.drawable.notificationsoff );
                Notification.deleteNotificationChannel(getBaseContext());
//                jobScheduler.cancel( 1 );
                Notices = false;
//            }
        }} );

        binding.ASButUpdate.setOnClickListener( v -> {
            //TODO : Update in Firebase
        } );
        binding.ASButHistory.setOnClickListener( v -> startActivity(
                new Intent( SettingsActivity.this,HistoryActivity.class ) ) );

    }
    public void jobService() {
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(getBaseContext(), NoticesService.class);
        JobInfo jobInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            jobInfo = new JobInfo.Builder(1, componentName)
                    .setPeriodic(24*60* 60 * 1000, JobInfo.getMinFlexMillis())
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setPersisted( true )
                    .build();
        }
        jobScheduler.schedule(jobInfo);
    }

//    @Override
//    protected void onDestroy( ) {
//        super.onDestroy( );
//        stopService(intent);
//
//    }
//    private void cancelJob() {
//        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        scheduler.cancel(1);
//    }
//
//    private void updateJob() {
//        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(this, CancelNotificationJobService.class))
//                .setPersisted(false)
//                .build();
//        scheduler.schedule(jobInfo);
//    }
//

//    @Override
//    protected void onPause( ) {
//        super.onPause( );
//        stopService(intent);
//    }
}