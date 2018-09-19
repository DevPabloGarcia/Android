package com.example.cice.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final int REQUEST_PERMISSION = 1;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("Location: ", String.format("Provider: %s / Longitud: %f / Latitud %f",
                location.getProvider(),
                location.getLongitude(),
                location.getLatitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        if(provider.equals(LocationManager.GPS_PROVIDER)){
            //LocationProvider.TEMPORARILY_UNAVAILABLE;
            if (status == LocationProvider.TEMPORARILY_UNAVAILABLE){
                Toast.makeText(MainActivity.this, "GPS no disponible temporalmente.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onProviderEnabled(String provider) {

        if(provider.equals(LocationManager.GPS_PROVIDER)){
            Toast.makeText(MainActivity.this, "Bien hecho!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        if(provider.equals(LocationManager.GPS_PROVIDER)){
            Toast.makeText(MainActivity.this, "No voy a ser preciso. Activa el GPS", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerListener();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    return;
                }
            }

            registerListener();

        }

    }

    public void registerListener() {

        // Empezamos a escuchar localizacion.

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION);

            return;

        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        // Terminamos de escuchar localizacion

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
        }else{
            locationManager.removeUpdates(this);
        }

    }
}
