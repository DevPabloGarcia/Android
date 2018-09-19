package com.example.cice.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by cice on 1/12/15.
 */
public class CustomDialogFragment extends AppCompatDialogFragment {

    //  Estos dos metodos los ponemos aunque no los utilizamos por si en algun momento queremos utilizarlos.
    public static CustomDialogFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CustomDialogFragment fragment = new CustomDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  Lo escribimos por si queremos recoger argumentos y usarlos.
        //  Bundle args = getArguments();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //  return super.onCreateDialog(savedInstanceState);
        Context context = getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //  Inflamos la view.
        View layout = LayoutInflater.from(context).inflate(R.layout.custom_dialog,null);
        builder.setView(layout);
        //  se puede hacer tambien. Pero es mejor el codigo de arriba.
        //  builder.setView(R.layout.custom_dialog);
        //  Para poner el Evento Onclick a los botones hay que hacerlo desde aquí y no desde el xml

        final EditText editTextUser = (EditText)layout.findViewById(R.id.edit_text_nameUser);
        final EditText passTextUser = (EditText)layout.findViewById(R.id.edit_text_passUser);
        Button buttonCancel = (Button)layout.findViewById(R.id.button_cancel);
        Button buttonLogin = (Button)layout.findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = editTextUser.getText().toString().trim();
                String pss = passTextUser.getText().toString().trim();
                // Comprobamos si el usuario ha dejado vacio los campos
                if(usr.isEmpty()){
                    editTextUser.setError("No puede estar vacio"); // Mensaje de error
                    editTextUser.requestFocus();
                    return;
                }
                if(pss.isEmpty()){
                    passTextUser.setError("No puede estar vacio"); // Mensaje de error
                    passTextUser.requestFocus();
                    return;
                }

                // Aqui hariamos el login.
                if(usr.equals(pss)){
                    Toast.makeText(v.getContext(),"LOGIN CORRECTO",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(v.getContext(),"LOGIN ERRONEO",Toast.LENGTH_SHORT).show();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"CANCEL",Toast.LENGTH_SHORT).show();
                CustomDialogFragment.this.dismiss();
            }
        });

        return builder.create();

    }


}
