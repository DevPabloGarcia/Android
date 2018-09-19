package com.example.cice.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager;
    Sensor accelerometerSensor, magneticFieldSensor;
    TextView textViewDeg;

    float[] mGravity;
    float[] mGeomagnetic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDeg = (TextView) findViewById(R.id.text_view_deg);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if (accelerometerSensor==null) {
            Toast.makeText(MainActivity.this, "No hay acelerómetro", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Hay acelerómetro", Toast.LENGTH_SHORT).show();
        }

        if (magneticFieldSensor==null) {
            Toast.makeText(MainActivity.this, "No hay acelerómetro", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Hay acelerómetro", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //activo la escucha del sensor
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
        Log.i("Sensor", "Empezamos a escuchar");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //desactivo la escucha del sensor
        sensorManager.unregisterListener(this, accelerometerSensor);
        sensorManager.unregisterListener(this, magneticFieldSensor);

        //sensorManager.unregisterListener(this);

        Log.i("Sensor", "Dejamos de escuchar");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                float azimut = orientation[0]; // orientation contains: azimut, pitch and roll
                float pitch = orientation[1];
                float roll = orientation[2];

                textViewDeg.setText(String.format("%.0f", Math.toDegrees(pitch)));
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
