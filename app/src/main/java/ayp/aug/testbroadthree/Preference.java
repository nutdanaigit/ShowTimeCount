package ayp.aug.testbroadthree;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.Date;

/**
 * Created by Nutdanai on 9/28/2016.
 */

public class Preference {

    private static final String PREF_SET_ALARM_ON = "PREF_SET_ALARM_ON";
    private static final String PREF_SET_ALARM_TEST = "PREF_SET_ALARM_ON_TEST";
    private static final String PREF_GET_TIME_IN = "PREF_GET_TIME_IN";

    private static final String PREF_IS_ON_MONDAY = "PREF_IS_ON_MONDAY";
    private static final String PREF_IS_ON_TUESDAY = "PREF_IS_ON_TUESDAY";
    private static final String PREF_IS_ON_WEDNESDAY = "PREF_IS_ON_WEDNESDAY";
    private static final String PREF_IS_ON_THURSDAY = "PREF_IS_ON_THURSDAY";
    private static final String PREF_IS_ON_FRIDAY = "PREF_IS_ON_FRIDAY";
    private static final String PREF_IS_ON_SATURDAY= "PREF_IS_ON_SATURDAY";
    private static final String PREF_IS_ON_SUNDAY = "PREF_IS_ON_SUNDAY";

    public static int getTimeIn(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(PREF_GET_TIME_IN,0);
    }

    public static void setTimeIn(Context context,int timeIn){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(PREF_GET_TIME_IN,timeIn).apply();
    }

    public static String getAlarmOn(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_SET_ALARM_ON,null);
    }
    public static void setAlarmOn(Context context , String s){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PREF_SET_ALARM_ON,s).apply();
    }

    public static Long getDate(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(PREF_SET_ALARM_TEST,0);
    }
    public static void setDate(Context context, long setTime){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(PREF_SET_ALARM_TEST,setTime).apply();
    }


    public static boolean isCheckMonday(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_IS_ON_MONDAY,false);
    }
    public static boolean isCheckTuesday(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_IS_ON_TUESDAY,false);
    }
    public static boolean isCheckWednesday(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_IS_ON_WEDNESDAY,false);
    }
    public static boolean isCheckThursday(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_IS_ON_THURSDAY,false);
    }
    public static boolean isCheckFriday(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_IS_ON_FRIDAY,false);
    }
    public static boolean isCheckSaturday(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_IS_ON_SATURDAY,false);
    }
    public static boolean isCheckSunday(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_IS_ON_SUNDAY,false);
    }

    public static void setCheckMonday(Context context, boolean isOn){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_ON_MONDAY,isOn).apply();
    }
    public static void setCheckTuesday(Context context, boolean isOn){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_ON_TUESDAY,isOn).apply();
    }
    public static void setCheckWednesday(Context context, boolean isOn){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_ON_WEDNESDAY,isOn).apply();
    }
    public static void setCheckThursday(Context context, boolean isOn){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_ON_THURSDAY,isOn).apply();
    }
    public static void setCheckFriday(Context context, boolean isOn){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_ON_FRIDAY,isOn).apply();
    }
    public static void setCheckSaturday(Context context, boolean isOn){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_ON_SATURDAY,isOn).apply();
    }
    public static void setCheckSunday(Context context, boolean isOn){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_IS_ON_SUNDAY,isOn).apply();
    }

}
