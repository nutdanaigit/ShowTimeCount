package ayp.aug.testbroadthree.Modern;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import ayp.aug.testbroadthree.GTFragment;

public class GTActivity extends GTSingleFragmentActivity {

    @Override
    protected Fragment onCreateFragment() {
        return GTFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        return  new Intent(context,GTActivity.class);
    }
}
