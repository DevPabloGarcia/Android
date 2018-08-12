package com.example.cice.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by cice on 1/12/15.
 */
public class InfoDialogFragment extends AppCompatDialogFragment {

    String titulo,mensaje;

    /*  Los fragment tienen un ciclo de vida parecido a un activity, es decir cuando se gira la pantalla
        se va a onDestroy y despues a onCreate, ¿Que pasa con eso? que el sistema crea uno nuevo, llama al
        constructor vacio, por tanto no es valido crear un un constructor parametrizado. Para que se queden los
        argumentos hay que hacerlo un bundle.
        Como lo realiza: Cuando se gira la pantalla antes de destruir el dialog, guarda el bundle en savedInstanceState
        de esa manera cuando se vuelve a llamar al onCreate se puede acceder a este bundle y utilizar los argumentos que se han pasado.
    */

    /**
     * Si se escribe newInstance y se pulsa enter, AndroidStudio te crea solo la funcion que tenemos aqui abajo.
     * */
    // Creamos un método estatico para que se pueda llamar instanciando la clase. Este método esta parametrizado de esa manera seria muy semejante a tener un constructor.
    public static InfoDialogFragment newInstance(String title,String msg){

        InfoDialogFragment infoDialogFragment = new InfoDialogFragment();
        /*
        * Vamos a pasar aqui los parametros para que si se gira la pantalla no se pierdan.
        * */
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("msg",msg);
        infoDialogFragment.setArguments(args);

        return infoDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        this.titulo = bundle.getString("title");
        this.mensaje = bundle.getString("msg");
    }

    public InfoDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        Context context = getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo)
                .setMessage(mensaje)
                .setIcon(R.mipmap.ic_launcher);
        AlertDialog dialog = builder.create();
        return dialog;
        // Si ponemos aqui el click, la funcion tiene que ser la misma para todos los dialogs.
    }
}
