package ayp.aug.testbroadthree;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nutdanai on 10/10/2016.
 */

public class TestTimeActivity extends AppCompatActivity {
    private static final String TAG = "TestTimeActivity";
    TextView txtTestOne;
    TextView txtTestTwo;
    TextView txtCheck;
    Date timeCurrent, time2 ,time3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_time);

        txtTestOne = (TextView) findViewById(R.id.txt_one);
        txtTestTwo = (TextView) findViewById(R.id.txt_two);
        txtCheck = (TextView) findViewById(R.id.txt_show_check);


        Calendar cal1 = Calendar.getInstance(); // Time1 set to Compare
        cal1.set(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), cal1.get(Calendar.DATE), 8, 00,00);

        Calendar cal2 = Calendar.getInstance();  // Time2 set to Compare
        cal2.set(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), cal2.get(Calendar.DATE), 12, 00,00);

        // check Test time to compare
        Calendar cal3 = Calendar.getInstance();  // Time2 set to Compare
        cal3.set(cal3.get(Calendar.YEAR), cal3.get(Calendar.MONTH), cal3.get(Calendar.DATE), 23, 00,00);

        try {
//            timeCurrent = Calendar.getInstance().getTime();
            timeCurrent = cal3.getTime();
            time2 = cal1.getTime();
            time3 = cal2.getTime();
        }catch (Exception e){}

        Log.d(TAG, "TimeCurrent = "+ timeCurrent);
        Log.d(TAG, "Time2 = "+ time2);
        Log.d(TAG, "Time3 = "+ time3);


        if(timeCurrent.after(time2) || timeCurrent.equals(time2)){
            Log.d(TAG, "onCreate: After or Equals " + timeCurrent.after(time2) + " , "+timeCurrent.equals(time2) );
            checkTimeAfterTimeOne();
        }else{
            Log.d(TAG, "onCreate: Before " + timeCurrent.before(time2) );
            // return
        }

    }

    public void checkTimeAfterTimeOne(){
       if(timeCurrent.before(time3) || timeCurrent.equals(time3)){
            Log.d(TAG, "checkTimeAfterTimeOne: Before or Equals " + timeCurrent.before(time3) + " , " + timeCurrent.equals(time3)  );
           // do something
        }else{
            Log.d(TAG, "checkTimeAfterTimeOne: After");
           // return
        }
    }
}
