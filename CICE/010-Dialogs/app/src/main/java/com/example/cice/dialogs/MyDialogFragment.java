package com.example.cice.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

/**
 * Created by cice on 1/12/15.
 */
public class MyDialogFragment extends AppCompatDialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState); Esta frase la borramos. Devuelve un Dialog.

        final Context context = getContext();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Mi primer dialog")
                .setMessage("Este sería el mensaje que queremos!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("NEUTRAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"Neutral",Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(R.mipmap.ic_launcher);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
