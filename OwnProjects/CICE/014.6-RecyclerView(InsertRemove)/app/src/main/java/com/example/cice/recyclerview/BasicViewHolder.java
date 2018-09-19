package com.example.cice.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


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

    }
}
