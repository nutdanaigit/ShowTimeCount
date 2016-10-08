package ayp.aug.testbroadthree.Broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.util.Log;

import ayp.aug.testbroadthree.Service.GTService;

/**
 * Created by Nutdanai on 10/3/2016.
 */

public class GTVisibleFragment extends Fragment {

     private static final String TAG = "GTVisibleFragment";

    public GTVisibleFragment() {
    }

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive");
            setResultCode(Activity.RESULT_CANCELED);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        IntentFilter filter = new IntentFilter(GTService.ACTION_SHOW_NOTIFICATION);
        getActivity().registerReceiver(mBroadcastReceiver, filter, GTService.RECEIVER_SHOW_NOTIFICATION, null);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }
}
