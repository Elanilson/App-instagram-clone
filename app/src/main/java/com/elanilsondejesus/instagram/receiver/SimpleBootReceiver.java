package com.elanilsondejesus.instagram.receiver;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import com.elanilsondejesus.instagram.R;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;



import static android.content.Context.ALARM_SERVICE;

public class SimpleBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Set the alarm here.

            SharedPreferences sharedPref = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
            long tempo = sharedPref.getLong(context.getString(R.string.lblIntervalo), TimeUnit.MINUTES.toMillis(30));

            Calendar calendar = Calendar.getInstance();

            Intent intent1 = new Intent(context, com.elanilsondejesus.instagram.receiver.NotificationReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), tempo, pendingIntent);

        }
    }
}

