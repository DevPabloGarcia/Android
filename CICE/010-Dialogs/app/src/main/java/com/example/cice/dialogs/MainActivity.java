package com.example.cice.dialogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showDialog(View view) {

        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.setCancelable(false); // lo del cancelable que se usa para cuando alguien pulse fuera se cierra o no, se tiene que poner aquí.
        dialogFragment.show(getSupportFragmentManager(), "DIALOG"); // Esta es la nueva manera de hacer show.
        /*  Que ventajas tiene? Si creamos la clase y solo tenemos que realizar estas dos lineas de código mientras
            si lo hacemos de la manera que hay debajo cada vez que queramos mostrar un dialog igual, tenemos que poner todo
            el codigo
        */

        // Forma mala de hacer un dialog.

        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mi primer dialog")
                .setMessage("Este sería el mensaje que queremos!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"cancel",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("NEUTRAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Neutral",Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(R.mipmap.ic_launcher);
        AlertDialog dialog = builder.create();
        dialog.show();*/
    }

    public void showInfoDialog(View view) {

        InfoDialogFragment.newInstance("Hola", "Este es el mensaje!").show(getSupportFragmentManager(), "DIALOG");
    }

    public void showLoginDialog(View view) {
        CustomDialogFragment customDialog = CustomDialogFragment.newInstance();
        customDialog.setCancelable(false);
        customDialog.show(getSupportFragmentManager(),"DIALOG");
    }
}
