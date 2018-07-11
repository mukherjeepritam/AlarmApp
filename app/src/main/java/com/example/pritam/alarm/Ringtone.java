package com.example.pritam.alarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Ringtone extends Service {



    MediaPlayer mediaPlayer;
    int startId;
    boolean isRunning;

    @Override
    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        //fetch the extra string values
        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone state:extra is",state);


        //This convert the extra string from this intent
        //to start IDs 0 or 1

        assert state !=null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                Log.e("Start ID is ",state);
                break;
            default:
                startId = 0;
                break;
        }

        //if else statement

        //if there is no music playing and the user pressed alarm on
        //music should start
        if (!this.isRunning && startId == 1){

        }

        //If there is a music playing and the user pressed alarm off
        //music should stop playing
        else if (this.isRunning && startId == 0){

        }

        //these are if the user presses random buttons
        //just to bug-proof the app
        //if there is no music playing and the user press alarm off do nothing
        else if (!this.isRunning && startId == 0){

        }

        //if there is music playing and user press alarm of
        //do nothing
        else if (this.isRunning && startId == 1){

        }

        //can't think of anything else just to catch the odd event
        else {

        }



        mediaPlayer = MediaPlayer.create(this,R.raw.ring);
        mediaPlayer.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}
