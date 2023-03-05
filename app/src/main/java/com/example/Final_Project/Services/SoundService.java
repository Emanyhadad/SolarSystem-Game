package com.example.Final_Project.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.Final_Project.R;


public class SoundService extends Service {

    MediaPlayer mp ;
    public SoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.sound);
        mp.setLooping( true );

//         عشان لمن يخلص الصوت تتطفي السيرفس
        mp.setOnCompletionListener( mediaPlayer -> stopSelf() );
    }
    // يتم استدعاءها عند تشغبل السيرفس
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mp.isPlaying())
            mp.start();
        mp.setLooping( true );
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying() && mp != null){
            mp.stop();
//            mp.release();
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}