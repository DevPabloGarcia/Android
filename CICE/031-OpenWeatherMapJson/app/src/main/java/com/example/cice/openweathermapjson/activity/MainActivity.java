package com.example.cice.openweathermapjson.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cice.openweathermapjson.R;
import com.example.cice.openweathermapjson.fragment.ForecastWeatherFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ForecastWeatherFragment forecastWeatherFragment = ForecastWeatherFragment.newInstance("Madrid,es", new ArrayList<String>());

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, forecastWeatherFragment).commit();

    }


}
