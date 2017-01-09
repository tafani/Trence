package ba.edu.ibu.myapplication;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.ContentFrameLayout;
import android.widget.Toast;

/**
 * Created by User on 08/01/2017.
 */

public class AlertReceiver extends BroadcastReceiver{

    NotificationManager mNotificationManager;
    Notification myNotification;
    final static int MyID=33;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm received!", Toast.LENGTH_LONG).show();
        mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent myIntent = new Intent(context, MoreInforNotification.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MoreInforNotification.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(myIntent);

        PendingIntent contentIntent = stackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT
                        | PendingIntent.FLAG_ONE_SHOT);
        android.support.v4.app.NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.tafani)
                .setContentTitle("Hi")
                .setContentText("Check your Schedule");
        mBuilder.setAutoCancel(true);
        mBuilder.setContentIntent(contentIntent);

        mNotificationManager.notify(MyID, mBuilder.build());
    }


}
