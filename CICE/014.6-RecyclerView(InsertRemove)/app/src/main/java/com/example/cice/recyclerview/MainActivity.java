package com.example.cice.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        // setup recyclerView
        // 1º ponemos el layout manager que va a manejar las filas
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Creamos el arrayList que pasaremos.
        String[] strings = getResources().getStringArray(R.array.array);
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(strings));

        // Añadimos el adapter.
        recyclerView.setAdapter(new BasicRecyclerViewAdapter(arrayList));

        //Si estas seguro de que el tamaño de tu row del recycler view no va a cambiar en tiempo de ejecución, hay que poner
        recyclerView.setHasFixedSize(true);
    }

    public void onFABClick(View view) {
        BasicRecyclerViewAdapter adapter = (BasicRecyclerViewAdapter) recyclerView.getAdapter();
        adapter.arrayList.add(1,"Nuevo elemento");
        adapter.notifyItemInserted(1);

        // Cambiamos los valores de alguna fila y notificamos el cambio.
        // adapter.strings.set(1,"String cambiando 1");
        // adapter.strings.set(2,"String cambiando 2");

        //Notificar la insercion de mas de un elemento.
        //adapter.notifyItemRangeInserted(1,3);

        //Notificar el cambio de valor de un elemento.
        //adapter.notifyItemChanged(num_row_elem);

        //Notificar la insercion de un elemento.
        //adapter.notifyItemInserted(num_row_inserted);

        //Notificar el cambio completo del data set.
        //adapter.notifyDataSetChanged();
    }
}
