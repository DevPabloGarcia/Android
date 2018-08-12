package com.example.cice.customtextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TimeTextView timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextView = (TimeTextView) findViewById(R.id.time_text_view);
        //timeTextView.setDuration(5200);
    }
}
