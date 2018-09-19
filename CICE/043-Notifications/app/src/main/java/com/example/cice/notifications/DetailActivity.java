package com.example.cice.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toast.makeText(DetailActivity.this, getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();
    }
}
