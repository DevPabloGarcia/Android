package com.example.cice.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements BasicRecyclerViewAdapter.onRowClickListener{

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Cogemos el String que nos viene de la activity anterior.
        String string = getIntent().getStringExtra("string");
        TextView textView = (TextView) findViewById(R.id.final_text_view);
        textView.setText(string);

        /**
         * Vamos a crear un nuevo recyclerview en esta activity al cual vamos a darle un click
         * diferente del que esta en MainActivity.
         */

        //binding
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("String 1");arrayList.add("String 2");arrayList.add("String 3");
        arrayList.add("String 4");arrayList.add("String 5");arrayList.add("String 6");
        arrayList.add("String 7");arrayList.add("String 8");arrayList.add("String 9");
        arrayList.add("String 10");arrayList.add("String 11");arrayList.add("String 12");
        arrayList.add("String 13");arrayList.add("String 14");arrayList.add("String 15");

        BasicRecyclerViewAdapter basicRecyclerViewAdapter = new BasicRecyclerViewAdapter(arrayList,this);
        recyclerView.setAdapter(basicRecyclerViewAdapter);
    }

    @Override
    public void onClickRow(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
