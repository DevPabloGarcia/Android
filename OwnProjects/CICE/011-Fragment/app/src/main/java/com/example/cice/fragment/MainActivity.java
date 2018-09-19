package com.example.cice.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Como no estamos pasando ningun parámetro, podriamos hacer new MainFragment() en vez de MainFragment.newInstance()
        MainFragment f = MainFragment.newInstance();

        // Ya tenemos el framento y el marco, ahora hay que meterlo. Asignamos el fragment a la vista.
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame,f)
                .commit();


    }
}
