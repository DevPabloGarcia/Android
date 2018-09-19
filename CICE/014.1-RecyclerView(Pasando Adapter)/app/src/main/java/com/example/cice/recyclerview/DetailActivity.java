package com.example.cice.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String string = getIntent().getStringExtra("string");
        TextView textView = (TextView) findViewById(R.id.final_text_view);
        textView.setText(string);
    }
}
