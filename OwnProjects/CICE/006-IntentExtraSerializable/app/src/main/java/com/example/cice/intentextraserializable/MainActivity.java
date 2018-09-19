package com.example.cice.intentextraserializable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Car myCar;
    //CarParcelable myCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPulsaButton(View view) {

        myCar = new Car();
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("car",myCar);

        /*
        myCar = new CarParcelable();
        intent.putExtra("parcelableCar",myCar);
        */

        startActivity(intent);

    }
}
