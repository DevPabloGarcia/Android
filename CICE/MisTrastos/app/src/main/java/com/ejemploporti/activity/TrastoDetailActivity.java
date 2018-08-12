package com.ejemploporti.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.ejemploporti.mistrastos.R;
import com.ejemploporti.model.Trasto;

public class TrastoDetailActivity extends AppCompatActivity {

    Trasto trasto;
    TextView editTextName, editTextInfo, editTextPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trasto_detail);

        trasto = getIntent().getParcelableExtra("trasto");

        this.setupToolbar();
        this.bindings();


        editTextName.setText(trasto.getName());
        editTextInfo.setText(trasto.getInfo());
        editTextPrice.setText(String.valueOf(trasto.getPrice()));
    }

    private void bindings(){
        editTextName = (EditText) findViewById(R.id.edit_text_name);
        editTextInfo = (EditText) findViewById(R.id.edit_text_info);
        editTextPrice = (EditText) findViewById(R.id.edit_text_price);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
