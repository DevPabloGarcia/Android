package com.garcia.pablo.cice.appstart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Cogemos el intent
        Intent intent = getIntent();

        // Cogemos el valor que pasamos en la MainActivity con el nombre que le dimos
        String finalString = intent.getStringExtra("texto");

        // Realizamos el binding del textView de la detailActivity
        textView = (TextView) findViewById(R.id.finalTextView);

        // Asignamos el texto al textView.
        textView.setText(finalString);

    }
}
