package com.example.cice.intentextraserializable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView modelo,marca,km;
    Car myCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        doBindings();

        myCar = getCar();
        marca.setText(myCar.getMarca());
        modelo.setText(myCar.getModelo());
        km.setText(String.valueOf(myCar.getKm()));
    }

    private Car getCar(){
        Intent i = getIntent();
        return (Car)i.getSerializableExtra("car");
    }

    private CarParcelable getParcelableCar(){
        Intent i = getIntent();
        return (CarParcelable)i.getParcelableExtra("carParcelable");
    }

    private void doBindings(){
        this.marca = (TextView) findViewById(R.id.marca);
        this.modelo = (TextView) findViewById(R.id.modelo);
        this.km = (TextView) findViewById(R.id.km);
    }
}
