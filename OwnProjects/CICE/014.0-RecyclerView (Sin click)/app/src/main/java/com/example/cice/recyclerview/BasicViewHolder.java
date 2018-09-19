package com.example.cice.recyclerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CICE on 15/12/15.
 */
public class BasicViewHolder extends RecyclerView.ViewHolder {

    LinearLayout linearLayout;
    TextView textView;

    public BasicViewHolder(View row){
        super(row);

        // Binding con los elementos de la vista.
        linearLayout = (LinearLayout)row.findViewById(R.id.linear_layout);
        textView = (TextView)row.findViewById(R.id.text_view);

        //Añadimos un click a la fila entera
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DetailActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
