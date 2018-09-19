package com.garcia.pablo.cice.appstart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Lanza la activity DetailActivity.
     * @param view Vista en la que se hizo click.
     */
    public void onClickMyButton(View view){

        /*
            Creamos el intent que almacenará la información que queremos pasar.
            Los Parametros son: Contexto de la app y activity a la que vamos a ir.
        */
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);

        // Creamos un texto que queremos pasar.
        String texto = "Este es el texto que pasamos a detail.";

        // Añadimos el texto al intent para que lo pase a la nueva activity.
        intent.putExtra("texto",texto);

        // Iniciamos la nueva Actividad.
        startActivity(intent);
    }
}
