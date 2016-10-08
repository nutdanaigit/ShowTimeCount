package ayp.aug.testbroadthree.Broadcast;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import ayp.aug.testbroadthree.GTDialogFragment;
import ayp.aug.testbroadthree.GTFragment;
import ayp.aug.testbroadthree.Service.GTService;

/**
 * Created by Nutdanai on 10/3/2016.
 */

public class GTBroadcast extends BroadcastReceiver {
    private static final String TAG = "GTBroadcast";

    public GTBroadcast() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Notification calling");
        if (getResultCode() != Activity.RESULT_OK) {
            Log.d(TAG, "onReceive: Cancel");
            return;
        }
        Notification notification = intent.getParcelableExtra(GTService.RECEIVER_SHOW_NOTIFICATION);

        //ไปเอา object notification มาจากตัว Intent
        NotificationManagerCompat.from(context).notify(0, notification);

        Log.i(TAG, "Notify new item displayed");
    }

}
