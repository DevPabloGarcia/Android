package com.example.cice.serviceintentwithbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by CICE on 15/3/16.
 */
public class DownloadProgressBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION_PROGRESS = "com.example.cice.serviceintentwithbroadcast.PROGRESS";
    public static final String ACTION_COMPLETE = "com.example.cice.serviceintentwithbroadcast.COMPLETE";

    ProgressBar progressBar;

    public DownloadProgressBroadcastReceiver(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(ACTION_PROGRESS)){

            int iteration = intent.getIntExtra("iteration", 0);
            progressBar.setProgress(iteration);

        }else if(intent.getAction().equals(ACTION_COMPLETE)){

            Toast.makeText(context, "Descarga Completada", Toast.LENGTH_SHORT).show();

        }
    }
}
