package com.example.Final_Project.Services;

import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;

public class CancelNotificationJobService extends JobService {
    @Override
    public boolean onStartJob( JobParameters params) {
        int notificationId = params.getExtras().getInt("notificationId");
        NotificationManager notificationManager = (NotificationManager)
                getSystemService( Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
        jobFinished(params, false);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}

