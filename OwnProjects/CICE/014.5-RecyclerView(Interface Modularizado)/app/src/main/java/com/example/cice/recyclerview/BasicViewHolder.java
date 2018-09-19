package com.example.cice.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by CICE on 15/12/15.
 */
public class BasicViewHolder extends RecyclerView.ViewHolder {

    // Objeto que se representa en un viewHolder.
    String objString;

    LinearLayout linearLayout;
    TextView textView;
    OnRowClickListener mListener;
    View row;

    public BasicViewHolder(View row, final OnRowClickListener mListener){
        super(row);

        this.row = row;
        this.mListener = mListener;

        // Binding con los elementos de la vista.
        linearLayout = (LinearLayout)row.findViewById(R.id.linear_layout);
        textView = (TextView)row.findViewById(R.id.text_view);



    }

    public void bind(String str){

        this.objString = str;
        textView.setText(str);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRowClick(objString);
            }
        });



    }
}
