package com.example.cice.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cice.sqlite.api.ApiManager;
import com.example.cice.sqlite.api.TrastoException;
import com.example.cice.sqlite.database.TrastosDatabaseManager;
import com.example.cice.sqlite.Model.Trasto;

public class MainActivity extends AppCompatActivity {

    TrastosDatabaseManager trastosDatabaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trastosDatabaseManager = new TrastosDatabaseManager(this);

        Trasto trasto = new Trasto();
        trasto.setName("Bicicleta");

        try {
            new ApiManager(this).insertTrasto(trasto);
        } catch (TrastoException e) {
            e.printStackTrace();
        }
    }
}
