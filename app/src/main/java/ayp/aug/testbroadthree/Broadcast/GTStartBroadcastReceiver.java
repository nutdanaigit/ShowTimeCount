package ayp.aug.testbroadthree.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ayp.aug.testbroadthree.Modern.GTLab;
import ayp.aug.testbroadthree.Preference;
import ayp.aug.testbroadthree.Service.GTService;

/**
 * Created by Nutdanai on 10/4/2016.
 */

public class GTStartBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "GTStartBroadcast";
    private boolean isOn = true;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"Receiver booties call");



        if(Calendar.getInstance().getTime().getTime() < Preference.getDate(context)) {
            Log.d(TAG,"It has time before time up");
            GTService.setServiceAlarm(context,isOn);
        }else{
            GTService.setServiceAlarm(context,!isOn);
        }

    }
}
