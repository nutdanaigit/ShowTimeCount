package ayp.aug.testbroadthree;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ayp.aug.testbroadthree.Broadcast.GTVisibleFragment;
import ayp.aug.testbroadthree.Modern.GTLab;
import ayp.aug.testbroadthree.Service.GTService;

/**
 * Created by Nutdanai on 9/28/2016.
 */

public class GTFragment extends GTVisibleFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "GTFragment";
    private static final int REQUEST_CODE_OK = 1;
    private boolean REQUEST_CODE_TEMP;
    private static final String TimeUp = "Finish";
    private int i;
    private int setTemp;
    private int hourEdit;
    private int minuteEdit;
    private int secondEdit;


    Thread thread;
    Calendar calendar;
//    MyCount count;

    private GTLab mGtLab;
    private Button mButtonShowTime;
    private EditText mEditTextTime;
    private TextView mTextViewTimeUp;
    private TextView mTxtSetTimeIn;
    private static int[] size = {
            R.id.checkbox_sunday,
            R.id.checkbox_monday,
            R.id.checkbox_Tuesday,
            R.id.checkbox_wednesday,
            R.id.checkbox_thursday,
            R.id.checkbox_friday,
            R.id.checkbox_saturday
    };

    private CheckBox[] mCheckBoxes = new CheckBox[size.length];

    public static GTFragment newInstance() {
        Bundle args = new Bundle();
        GTFragment fragment = new GTFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View v = inflater.inflate(R.layout.fragment_gt, container, false);
        mButtonShowTime = (Button) v.findViewById(R.id.btn_count);
        mEditTextTime = (EditText) v.findViewById(R.id.edit_text);
        mTextViewTimeUp = (TextView) v.findViewById(R.id.txt_show_time);
        mTxtSetTimeIn = (TextView) v.findViewById(R.id.txt_time_in);
        mGtLab = GTLab.newInstance(getActivity());

        for (i = 0; i < mCheckBoxes.length; i++) {
            mCheckBoxes[i] = (CheckBox) v.findViewById(size[i]);
            mCheckBoxes[i].setChecked(mGtLab.getCheckBox(size[i]));
            Log.d(TAG, "onCreateView: getId = " + size[i]);
            mCheckBoxes[i].setOnCheckedChangeListener(this);
        }

        mButtonShowTime.setEnabled(false);
        UpdateUI();


        mTxtSetTimeIn.setOnClickListener(this);
        mButtonShowTime.setOnClickListener(this);

        return v;
    }

    public void UpdateUI() {

        if (Calendar.getInstance().getTime().getTime() < Preference.getDate(getActivity())) {
            mTextViewTimeUp.setText(getTimeFinish());
            // Do setTime and callThread
            Log.d(TAG, "UpdateUI Calendar: " + Calendar.getInstance().getTime().getTime());
            Log.d(TAG, "UpdateUI Preference: " + Preference.getDate(getActivity()));
            final Calendar cal1 = Calendar.getInstance();
            final Calendar cal2 = Calendar.getInstance();
            cal1.setTimeInMillis(Preference.getDate(getActivity()));
            cal2.setTimeInMillis(Calendar.getInstance().getTime().getTime());
            Log.d(TAG, "UpdateUI: " + cal1.getTime().toString());
            Log.d(TAG, "UpdateUI cal 2 : " + cal2.getTime().toString());

            int hourCal1 = cal1.get(Calendar.HOUR) * 60 * 60;
            int minuteCal1 = cal1.get(Calendar.MINUTE) * 60;
            int secondCal1 = cal1.get(Calendar.SECOND);

            int hourCal2 = cal2.get(Calendar.HOUR) * 60 * 60;
            int minuteCal2 = cal2.get(Calendar.MINUTE) * 60;
            int secondCal2 = cal2.get(Calendar.SECOND);

            setTemp = (hourCal1 + minuteCal1 + secondCal1) - (hourCal2 + minuteCal2 + secondCal2);
            callThreadCount();
            Log.d(TAG, "UpdateUI The last: " + setTemp);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_count:
                mButtonShowTime.setEnabled(false);
                getDoing();
                break;
            case R.id.txt_time_in:
                Log.d(TAG, "onClick: Txt_time_in");
                int hour = Calendar.getInstance().get(Calendar.HOUR);
                int minute = Calendar.getInstance().get(Calendar.MINUTE);
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String setMinute = (i1 == 0) ? "00" : (i1 < 10) ? "0" + String.valueOf(i1) : String.valueOf(i1);
                        String setHour = (i < 10) ? "0" + String.valueOf(i) : String.valueOf(i);
                        Preference.setTimeIn(getActivity(), timePicker.getId());
                        Log.d(TAG, "onTimeSet: " + timePicker.getId());
//                            String ap = (calendarTimeIn.getTime().after())?" AM":" PM";
                        String setTimeIn = setHour + ":" + setMinute;
                        mTxtSetTimeIn.setText(setTimeIn);
                    }
                }, hour, minute, false).show();
                break;
        }
    }

    public void getDoing() {
        calendar = Calendar.getInstance();
        setTemp = Integer.valueOf(mEditTextTime.getText().toString());
        calTimeFinish();
        mTextViewTimeUp.setText(getTimeFinish());
        GTService.setServiceAlarm(getActivity(), true);

        setTemp = setTemp * 60;
        callThreadCount();
    }

    public String getTimeFinish() {
        return Preference.getAlarmOn(getActivity());
    }

    public void calTimeFinish() {
        getTime();
        Log.d(TAG, "Time : " + hourEdit + ":" + minuteEdit + ":" + secondEdit);

        minuteEdit = minuteEdit + setTemp;
        calendar.set(Calendar.HOUR, hourEdit);
        calendar.set(Calendar.MINUTE, minuteEdit);
        calendar.set(Calendar.SECOND, secondEdit);
        getTime();
        Log.d(TAG, "Time2 : " + hourEdit + ":" + minuteEdit + ":" + secondEdit);
        Preference.setDate(getActivity(), calendar.getTime().getTime());
        Preference.setAlarmOn(getActivity(), getFormattedDate());
    }

    public String getFormattedDate() {
        SimpleDateFormat df = new SimpleDateFormat(" hh:mm:ss a"); //called without pattern
        Log.d(TAG, "GetFormat" + calendar.getTime().toString());
        return df.format(calendar.getTime());
    }

    public void getTime() {
        hourEdit = calendar.get(Calendar.HOUR);
        minuteEdit = calendar.get(Calendar.MINUTE);
        secondEdit = calendar.get(Calendar.SECOND);
    }


    public void callThreadCount() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    callUI();
                    setTemp--;
                    if (setTemp == 0) {
                        callDialog();
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void callDialog() {
        if (REQUEST_CODE_TEMP == true) {
            FragmentManager fm = getFragmentManager();
            GTDialogFragment gtDialogFragment = GTDialogFragment.newInstance();
            gtDialogFragment.show(fm, "Test");
        }
    }

    public void callUI() {
        mButtonShowTime.post(new Runnable() {
            @Override
            public void run() {
                String s;
                s = (setTemp != 0) ? mGtLab.formatTime(setTemp) : TimeUp;
                if (s.equals(TimeUp)) mButtonShowTime.setEnabled(true);
                mButtonShowTime.setText(s);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        REQUEST_CODE_TEMP = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        REQUEST_CODE_TEMP = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        thread.interrupt();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        mGtLab.setCheckBox(compoundButton);
        Log.d(TAG, "CheckBox: " + compoundButton.getText().toString() + " status: " + compoundButton.isChecked() + " id: " + compoundButton.length());
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        String dayOfWeek = dateFormat.format(date);
        if (dayOfWeek.equals(compoundButton.getText().toString())) {
            if (mGtLab.getCheckBox(compoundButton.getId())) {
                Log.d(TAG, "onCheckedChanged: " + Calendar.getInstance().getTime() + " : " + Preference.getTimeIn(getActivity()));
//                if() {
                    getDoing();
//                }
            }

        }

    }

}
