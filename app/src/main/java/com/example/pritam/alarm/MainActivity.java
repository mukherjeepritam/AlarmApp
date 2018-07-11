package com.example.pritam.alarm;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    //To take an alarm manager
    AlarmManager alarmManager;
    TimePicker alarm_timePicker;
    TextView show;
    Context context;
    PendingIntent pendingIntent;

    @TargetApi(24)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.context = this;

        //initialize alarm manager
        alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);

        //Initialize our time picker
        alarm_timePicker =(TimePicker) findViewById(R.id.timePicker);

        //initialize our text to show
        show =(TextView) findViewById(R.id.show);

        //Create an instance of calender
        final Calendar calendar = Calendar.getInstance();

        //Create an intent to the alarm class
        final Intent myIntent = new Intent(this.context,Alarm_Receiver.class);

        //Initialize the start button
        Button turn_on =(Button)findViewById(R.id.set);

        //Create an on click listener
        turn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Method that change the text of textbox

                //setting calender instance with the hour and minute that we picked on time picker
                calendar.set(Calendar.HOUR_OF_DAY,alarm_timePicker.getHour());
                calendar.set(Calendar.MINUTE,alarm_timePicker.getMinute());

                //Get the int val of hr and min
                int hr = alarm_timePicker.getHour();
                int min = alarm_timePicker.getMinute();

                //Convert the int values to string

                String hr_str ;
                String min_str;

                if(hr>12){
                    hr_str=String.valueOf(hr-12);
                }else {
                    hr_str=String.valueOf(hr);
                }
                if (min<10){
                    min_str="0"+String.valueOf(min);
                }
                else{
                    min_str = String.valueOf(min);
                }

                set_alarm_text("Alarm set to " + hr_str +":" +min_str);

                //put is extra string into myintent tells
                // the clock that you are pressed the "alarm on" button

                myIntent.putExtra("extra","alarm on");

                //Create a pending intent that delays the intent until the specified calender time
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,myIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                //Set the alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        pendingIntent);

            }
        });

        //Initialize the stop button
        Button turn_off =(Button)findViewById(R.id.cancel);

        //Create an on click listener
        turn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Method that change the text of textbox
                set_alarm_text("Alarm off");
                //Cancel the alarm
                alarmManager.cancel(pendingIntent);

                //put is extra string into myintent tells
                // the clock that you are pressed the "alarm off" button

                myIntent.putExtra("extra","alarm off");

                //Stop the ringtone
                sendBroadcast(myIntent);

            }
        });
    }

    private void set_alarm_text(String output) {
        show.setText(output);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}