package ayp.aug.testbroadthree;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Nutdanai on 9/29/2016.
 */

public class GTDialogFragment extends DialogFragment {


    public static GTDialogFragment newInstance() {

        Bundle args = new Bundle();

        GTDialogFragment fragment = new GTDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(android.R.layout.select_dialog_item,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Time Up !!!");
        builder.setMessage("If you want to stop this Alert, you can press 'ok' ");
        builder.setView(v);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        return builder.create();

    }
}
