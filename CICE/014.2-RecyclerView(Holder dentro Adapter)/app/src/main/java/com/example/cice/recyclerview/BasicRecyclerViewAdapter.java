package com.example.cice.recyclerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CICE on 15/12/15.
 */
public class BasicRecyclerViewAdapter
        extends RecyclerView.Adapter<BasicRecyclerViewAdapter.BasicViewHolder>{

    ArrayList<String> arrayList;

    public BasicRecyclerViewAdapter(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public BasicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Creamos una vista y le metemos el xml row_basic que es lo que se pasa
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_basic, parent, false);
        return new BasicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BasicViewHolder holder, int position) {
        String string = arrayList.get(position);
        holder.textView.setText(string);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // Ponemos el basicViewHolder dentro del adapter.
    public class BasicViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView textView;

        public BasicViewHolder(View row){
            super(row);

            // Binding con los elementos de la vista.
            linearLayout = (LinearLayout)row.findViewById(R.id.linear_layout);
            textView = (TextView)row.findViewById(R.id.text_view);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),DetailActivity.class);
                    intent.putExtra("string",arrayList.get(getAdapterPosition()));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

}
