package com.example.cice.serviceintentwithbroadcast;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by CICE on 15/3/16.
 */

public class DownloadIntentService extends IntentService{

    public DownloadIntentService() {
        super("Download Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int iteraciones = intent.getIntExtra("iteraciones",0);

        for(int i = 0; i < iteraciones; i++){
            runtask();
            Intent broadcastIntent = new Intent();
            broadcastIntent.putExtra("iteration", i + 1);
            broadcastIntent.setAction(DownloadProgressBroadcastReceiver.ACTION_PROGRESS);
            sendBroadcast(broadcastIntent);

            Log.i("Pablo", "iteracion "+i);
        }

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(DownloadProgressBroadcastReceiver.ACTION_COMPLETE);
        sendBroadcast(broadcastIntent);

        Log.i("Pablo", "FINAL ");
    }

    public void runtask(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
