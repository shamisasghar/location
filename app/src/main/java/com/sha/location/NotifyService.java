package com.sha.location;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SHA on 18/8/2017.
 */

public class NotifyService extends Service {
    final static String ACTION = "NotifyServiceAction";
    final static String STOP_SERVICE = "";
    final static int RQS_STOP_SERVICE = 1;
    private Timer timer;
    private TimerTask task;
    Timer timerObj = new Timer();

    NotifyServiceReceiver notifyServiceReceiver;

    private static final int MY_NOTIFICATION_ID=1;
    private NotificationManager notificationManager;
    private Notification myNotification;
    private final String myBlog = "http://android-er.blogspot.com/";

    @Override
    public void onCreate() {
// TODO Auto-generated method stub
        notifyServiceReceiver = new NotifyServiceReceiver();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
// TODO Auto-generated method stub


        TimerTask timerTaskObj = new TimerTask() {
            public void run() {
                //perform your action here
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ACTION);
                registerReceiver(notifyServiceReceiver, intentFilter);

// Send Notification
                notificationManager =
                        (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                myNotification = new Notification(R.drawable.next,
                        "Notification!",
                        System.currentTimeMillis());
                Context context = getApplicationContext();
                String notificationTitle = "Exercise of Notification!";
                String notificationText = "http://android-er.blogspot.com/";
                NotificationCompat.Builder builder =new NotificationCompat.Builder(context)
                        .setContentTitle(notificationTitle)
                        .setContentText(notificationText);

                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myBlog));
                PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_ONE_SHOT);
                myNotification.defaults |= Notification.DEFAULT_SOUND;
                myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
                builder.setContentIntent(pendingIntent);
                notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
                // myNotification.(context, notificationTitle, notificationText, pendingIntent);

            }
        };
        timerObj.schedule(timerTaskObj, 0, 15000);


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
// TODO Auto-generated method stub
        this.unregisterReceiver(notifyServiceReceiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
// TODO Auto-generated method stub
        return null;
    }

    public class NotifyServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            int rqs = arg1.getIntExtra("RQS", 0);
            if (rqs == RQS_STOP_SERVICE){
                stopSelf();
            }
        }
    }

    @Override
    public boolean stopService(Intent name) {
        // TODO Auto-generated method stub
      //  timer.cancel();
        timerObj.cancel();
        task.cancel();
        return super.stopService(name);

    }

}
