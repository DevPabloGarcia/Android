package com.example.cice.animatelayoutchanges;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_view = (TextView) findViewById(R.id.text_view);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
    }


    public void onClickShow(View view) {

        frameLayout.setVisibility(View.VISIBLE);

    }

    public void onClickHide(View view) {

        frameLayout.setVisibility(View.GONE);

    }
}
