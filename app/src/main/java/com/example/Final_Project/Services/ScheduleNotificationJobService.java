package com.example.Final_Project.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.Final_Project.Activities.SplashActivity;
import com.example.Final_Project.R;

public class ScheduleNotificationJobService extends JobService {
    public static final String CHANNEL_ID = "CHANNEL_ID";
    private static final String CHANNEL_NAME = "CHANNEL";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        displayNotification();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        SplashActivity.jobScheduler.cancel(1);
        return true;
    }
    private void displayNotification(){
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("channel description");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, SplashActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getBaseContext(),0,intent, 0);
//TODO : Text & Icon
        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (getBaseContext(),CHANNEL_ID);
        builder.setSmallIcon( R.drawable.system)
                .setContentTitle(getString( R.string.titleN))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getString( R.string.bigtext)))
                .addAction(R.drawable.ic_baseline_close_24,getString( R.string.flynow),pi);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1,builder.build());
    }
}

