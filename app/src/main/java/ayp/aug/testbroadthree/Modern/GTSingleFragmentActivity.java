package ayp.aug.testbroadthree.Modern;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ayp.aug.testbroadthree.R;

/**
 * Created by Nutdanai on 9/28/2016.
 */

public abstract class GTSingleFragmentActivity extends AppCompatActivity {

    private static final String TAG = "SingleFragmentActivity";

        @LayoutRes
        protected int getLayoutResId()
        {
            return R.layout.activity_gt;
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(getLayoutResId());

            Log.d(TAG,"On create activity");

            FragmentManager fm = getSupportFragmentManager();
            Fragment f =fm.findFragmentById(R.id.fragment_container);

            if( f == null){
                f = onCreateFragment(); //CreateFragment Get Method form onCreateFragment
                //
                fm.beginTransaction()
                        .add(R.id.fragment_container,f)
                        .commit();
                Log.d(TAG," Fragment is created");
            }else{
                Log.d(TAG," Fragment have already been created");
            }
        }
        protected abstract Fragment onCreateFragment();

}
