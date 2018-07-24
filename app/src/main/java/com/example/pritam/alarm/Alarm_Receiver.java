package com.example.pritam.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alarm_Receiver extends BroadcastReceiver {

    private static final String TAG = "Alarm_Receiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.

        Log.e(TAG,"Yay!");

        //fetch extra string from intent
        String get_your_string = intent.getExtras().getString("extra");

        Log.e(TAG , get_your_string);

        //create an intent to the ringtone service
        Intent service_intent = new Intent(context,Ringtone.class);

        //Pass the extra string from Main Activity to the Ringtone
        service_intent.putExtra("extra",get_your_string);

        //Start the ringtone service
        context.startService(service_intent);

    }
}
