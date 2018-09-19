package com.example.cice.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Ciclo: ", "Activity 1 onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ciclo: ", "Activity 1 onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ciclo: ", "Activity 1 onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ciclo: ", "Activity 1 onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ciclo: ", "Activity 1 onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ciclo: ", "Activity 1 onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ciclo: ", "Activity 1 onRestart");
    }

    public void onClickCambio(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}
