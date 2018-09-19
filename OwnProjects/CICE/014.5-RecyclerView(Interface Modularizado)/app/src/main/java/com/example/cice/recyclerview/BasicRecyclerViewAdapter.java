package com.example.cice.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by CICE on 15/12/15.
 */
public class BasicRecyclerViewAdapter extends RecyclerView.Adapter<BasicViewHolder>{

    ArrayList<String> arrayList;
    OnRowClickListener mListener;

    public BasicRecyclerViewAdapter(ArrayList arrayList,OnRowClickListener mListener) {
        this.arrayList = arrayList;
        this.mListener = mListener;
    }

    @Override
    public BasicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Creamos una vista y le metemos el xml row_basic que es lo que se pasa
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_basic,parent,false);
        return new BasicViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(BasicViewHolder holder, int position) {
        holder.bind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
