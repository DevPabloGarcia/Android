package com.example.cice.recyclerview;

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
public class BasicRecyclerViewAdapter extends RecyclerView.Adapter<BasicRecyclerViewAdapter.BasicViewHolder>{

    interface onRowClickListener{

        void onClickRow(String str);

    }

    ArrayList<String> arrayList;
    onRowClickListener mListener;

    public BasicRecyclerViewAdapter(ArrayList arrayList, onRowClickListener mListener) {
        this.arrayList = arrayList;
        this.mListener = mListener;
    }

    @Override
    public BasicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Creamos una vista y le metemos el xml row_basic que es lo que se pasa
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_basic,parent,false);
        return new BasicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BasicViewHolder holder, int position) {
        final String string = arrayList.get(position);
        holder.textView.setText(string);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickRow(arrayList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    /**
     * Clase View Holder. Corresponde a cada fila.
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
}
