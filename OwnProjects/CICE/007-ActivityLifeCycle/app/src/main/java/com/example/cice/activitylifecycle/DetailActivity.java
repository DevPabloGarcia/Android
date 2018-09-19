package com.example.cice.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.i("Ciclo: ", "Activity 2 onCreate");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ciclo: ", "Activity 2 onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ciclo: ", "Activity 2 onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ciclo: ", "Activity 2 onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ciclo: ", "Activity 2 onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ciclo: ", "Activity 2 onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ciclo: ", "Activity 2 onRestart");
    }
}
