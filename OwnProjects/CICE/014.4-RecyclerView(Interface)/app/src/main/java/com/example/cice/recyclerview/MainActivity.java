package com.example.cice.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements BasicRecyclerViewAdapter.onRowClickListener{

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        // 1º ponemos el layout manager que va a manejar las filas, un linear.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Creamos el arrayList que pasaremos.
        String[] strings = getResources().getStringArray(R.array.array);
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(strings));

        // Añadimos el adapter.
        recyclerView.setAdapter(new BasicRecyclerViewAdapter(arrayList,this));

        //Si estas seguro de que el tamaño de tu row del recycler view no va a cambiar en tiempo de ejecución, hay que poner
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onClickRow(String str) {
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("string",str);
        startActivity(intent);
    }

}
