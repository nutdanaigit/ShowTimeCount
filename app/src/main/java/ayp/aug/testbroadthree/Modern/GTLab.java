package ayp.aug.testbroadthree.Modern;

import android.content.Context;
import android.util.Log;
import android.widget.CompoundButton;

import ayp.aug.testbroadthree.Preference;
import ayp.aug.testbroadthree.R;

/**
 * Created by Nutdanai on 9/29/2016.
 */

public class GTLab {
    private static final String TAG = "GTLab";
    private static GTLab instance;
    private Context mContext;
    private int seconds;
    private String output;

    public GTLab(Context context) {
        mContext = context;
    }


    public static GTLab newInstance(Context context) {
        if (instance == null) {
            instance = new GTLab(context);
        }
        return instance;
    }


    public String formatTime(int millis) {
        output = "";
        seconds = millis;
        int minutes = seconds / 60;
        int hours = minutes / 60;

        seconds = seconds % 60;
        minutes = minutes % 60;
        hours = hours % 60;

        String secondsD = String.valueOf(seconds);
        String minutesD = String.valueOf(minutes);
        String hoursD = String.valueOf(hours);

        if (seconds < 10)
            secondsD = "0" + seconds;
        if (minutes < 10)
            minutesD = "0" + minutes;

        if (hours < 10)
            hoursD = "0" + hours;

        output = hoursD + " : " + minutesD + " : " + secondsD;
        return output;
    }

    public void setCheckBox(CompoundButton compoundButton) {
        switch (compoundButton.getId()) {
            case R.id.checkbox_sunday:
                Log.d(TAG, "setCheckBox: Sunday " + compoundButton.isChecked());
                Preference.setCheckSunday(mContext, compoundButton.isChecked());
                break;
            case R.id.checkbox_monday:
                Log.d(TAG, "setCheckBox: monday " + compoundButton.isChecked());
                Preference.setCheckMonday(mContext, compoundButton.isChecked());
                break;
            case R.id.checkbox_Tuesday:
                Log.d(TAG, "setCheckBox: Tuesday " + compoundButton.isChecked());
                Preference.setCheckTuesday(mContext, compoundButton.isChecked());
                break;
            case R.id.checkbox_wednesday:
                Log.d(TAG, "setCheckBox: Wednesday " + compoundButton.isChecked());
                Preference.setCheckWednesday(mContext, compoundButton.isChecked());
                break;
            case R.id.checkbox_thursday:
                Log.d(TAG, "setCheckBox: Thursday " + compoundButton.isChecked());
                Preference.setCheckThursday(mContext, compoundButton.isChecked());
                break;
            case R.id.checkbox_friday:
                Log.d(TAG, "setCheckBox: Friday " + compoundButton.isChecked());
                Preference.setCheckFriday(mContext, compoundButton.isChecked());
                break;
            case R.id.checkbox_saturday:
                Log.d(TAG, "setCheckBox: Saturday " + compoundButton.isChecked());
                Preference.setCheckSaturday(mContext, compoundButton.isChecked());
                break;
        }
    }

    public boolean getCheckBox(int i) {
        switch (i) {
            case R.id.checkbox_sunday:
                Log.d(TAG, "getCheckBox: sunday " + Preference.isCheckSunday(mContext));
                return Preference.isCheckSunday(mContext);
            case R.id.checkbox_monday:
                Log.d(TAG, "getCheckBox: monday " + Preference.isCheckMonday(mContext));
                return Preference.isCheckMonday(mContext);
            case R.id.checkbox_Tuesday:
                Log.d(TAG, "getCheckBox: tuesday " + Preference.isCheckTuesday(mContext));
                return Preference.isCheckTuesday(mContext);
            case R.id.checkbox_wednesday:
                Log.d(TAG, "getCheckBox: wednesday " + Preference.isCheckWednesday(mContext));
                return Preference.isCheckWednesday(mContext);
            case R.id.checkbox_thursday:
                Log.d(TAG, "getCheckBox: thursday " + Preference.isCheckThursday(mContext));
                return Preference.isCheckThursday(mContext);
            case R.id.checkbox_friday:
                Log.d(TAG, "getCheckBox: friday " + Preference.isCheckFriday(mContext));
                return Preference.isCheckFriday(mContext);
            case R.id.checkbox_saturday:
                Log.d(TAG, "getCheckBox: saturday " + Preference.isCheckSaturday(mContext));
                return Preference.isCheckSaturday(mContext);
            default:
                return false;
        }
    }
}
