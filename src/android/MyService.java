package com.example.plugin;

import org.apache.cordova.*;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

public class MyService extends Service {

    Handler mHandler = new Handler();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mHandler.postDelayed(ToastRunnable, 5000);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    Runnable ToastRunnable = new Runnable() {
        public void run() {
            Context context = getApplicationContext();

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(context)
                .setSmallIcon(context.getApplicationInfo().icon)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("It works!")
                .setTicker("Ticker")
                .setContentText("Testing the plugin")
                .setNumber(1)
                .setAutoCancel(true);

            mNotificationManager.notify("App Name", 228, mBuilder.build());

            mHandler.postDelayed( ToastRunnable, 5000);
        }
    };

}
