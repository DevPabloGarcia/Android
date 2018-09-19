package com.example.cice.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by CICE on 15/12/15.
 */
public class BasicRecyclerViewAdapter extends RecyclerView.Adapter<BasicViewHolder>{

    ArrayList<String> arrayList;

    public BasicRecyclerViewAdapter(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public BasicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Creamos una vista y le metemos el xml row_basic que es lo que se pasa
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_basic,parent,false);
        return new BasicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BasicViewHolder holder,final int position) {
        String string = arrayList.get(position);
        holder.textView.setText(string);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),""+holder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addString(int position,String string){
        this.arrayList.add(string);
        notifyItemInserted(position);
    }

    public void removeString(int position){
        this.arrayList.remove(position);
        notifyItemInserted(position);
    }

    public void setDataSet(ArrayList<String> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();

    }


}
