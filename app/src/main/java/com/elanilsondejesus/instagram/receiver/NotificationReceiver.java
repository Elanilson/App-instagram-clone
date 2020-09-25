package com.elanilsondejesus.instagram.receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.elanilsondejesus.instagram.R;
import com.elanilsondejesus.instagram.activity.MainActivity;


public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent repeatingIntent = new Intent(context, MainActivity.class);
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        createNotificationChannel(context);

        createNotification(context, pendingIntent);

        Log.e("NOTIFICACAO", "CHEGOU");
    }

    private void createNotification(Context context, PendingIntent pendingIntent){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notificacao")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setContentTitle("Hidratação sempre")
                .setContentText("Momento de se hidratar um pouco")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(100, builder.build());
    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notificacao", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
