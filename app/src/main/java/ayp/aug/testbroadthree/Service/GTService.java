package ayp.aug.testbroadthree.Service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import ayp.aug.testbroadthree.GTFragment;
import ayp.aug.testbroadthree.Modern.GTActivity;
import ayp.aug.testbroadthree.Preference;

/**
 * Created by Nutdanai on 10/3/2016.
 */

public class GTService extends IntentService {
    private static final String TAG = "GTService";
    public static final String ACTION_SHOW_NOTIFICATION = "ayp.aug.testbroadthree.ACTION_SHOW_NOTIFICATION";
    public static final String RECEIVER_SHOW_NOTIFICATION = "ayp.aug.testbroadthree.RECEIVER_SHOW_NOTIFICATION";
    public static final String REQUEST_CODE="request_code";

    public GTService() {
        super(TAG);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, GTService.class);
    }

    public static void setServiceAlarm(Context context,boolean isOn) {
        Intent i = GTService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0, i, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if(isOn) {
            Log.d(TAG, "Time " + Preference.getDate(context));
            am.set(AlarmManager.RTC_WAKEUP, Preference.getDate(context), pi);
            Log.d(TAG, "Run by Alarm Manager");
        }

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");

        Intent i = GTActivity.newIntent(this);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        builder.setTicker("Ticker!");
        builder.setContentTitle("Time Up !! ");
        builder.setContentText("Time = " + Preference.getAlarmOn(this));
        builder.setSmallIcon(android.R.drawable.ic_notification_overlay);
//        Ringtone ringtone = RingtoneManager.getRingtone(this,soundUri);
//        ringtone.play();
        builder.setSound(soundUri);
        builder.setContentIntent(pi);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        builder.setAutoCancel(true);

        Notification notification = builder.build();
//        notification.flags |= Notification.FLAG_INSISTENT;

//        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
//        nmc.notify(0, notification);

        sendBackgroundNotification(0,notification);
        new GTScreen().on(GTService.this);
    }


    private void sendBackgroundNotification(int requestCode,Notification notification){
        Intent intent = new Intent(ACTION_SHOW_NOTIFICATION);
        intent.putExtra(REQUEST_CODE,requestCode);
        intent.putExtra(RECEIVER_SHOW_NOTIFICATION,notification);

        sendOrderedBroadcast(intent,RECEIVER_SHOW_NOTIFICATION,null,null, Activity.RESULT_OK,null,null);
    }
}
