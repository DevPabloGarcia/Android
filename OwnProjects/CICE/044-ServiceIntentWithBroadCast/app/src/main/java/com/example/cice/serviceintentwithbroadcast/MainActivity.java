package com.example.cice.serviceintentwithbroadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private static final int ITERACIONES = 1000;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setMax(ITERACIONES);

        // Registramos el broadcast.
        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(DownloadProgressBroadcastReceiver.ACTION_PROGRESS);
        intentFilter.addAction(DownloadProgressBroadcastReceiver.ACTION_COMPLETE);

        DownloadProgressBroadcastReceiver receiver = new DownloadProgressBroadcastReceiver(progressBar);
        registerReceiver(receiver, intentFilter);
    }

    public void onClickDownload(View view) {

        Intent intentService = new Intent(this, DownloadIntentService.class);
        intentService.putExtra("iteraciones",ITERACIONES);
        startService(intentService);

    }
}
