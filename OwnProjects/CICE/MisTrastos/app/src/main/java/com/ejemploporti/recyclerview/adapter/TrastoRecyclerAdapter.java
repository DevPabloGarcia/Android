package com.ejemploporti.recyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ejemploporti.mistrastos.R;
import com.ejemploporti.api.ApiManager;
import com.ejemploporti.recyclerview.viewholder.TrastoViewHolder;
import com.ejemploporti.activity.TrastoDetailActivity;
import com.ejemploporti.model.Trasto;

import java.util.ArrayList;

/**
 * Created by CICE on 12/1/16.
 */
public class TrastoRecyclerAdapter extends RecyclerView.Adapter<TrastoViewHolder> implements TrastoViewHolder.OnRowClickListener{

    ArrayList<Trasto> trastos;
    Context context;
    View view; //Guarreria.

    public TrastoRecyclerAdapter(Context context) {
        this.context = context;
        trastos = new ArrayList<>();
    }

    public TrastoRecyclerAdapter(Context context, ArrayList<Trasto> trastos) {
        this.trastos = trastos;
        this.context = context;
    }

    @Override
    public TrastoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = parent;
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_trasto,parent,false);

        return new TrastoViewHolder(layout,this);
    }

    @Override
    public void onBindViewHolder(TrastoViewHolder holder, int position) {
        holder.bind(trastos.get(position));
    }

    @Override
    public int getItemCount() {
        return trastos.size();
    }

    @Override
    public void onTrastoClicked(Trasto trasto) {
        Intent intent = new Intent(context, TrastoDetailActivity.class);
        intent.putExtra("trasto", trasto);
        context.startActivity(intent);
    }

    @Override
    public void onTrastoLongClicked(Trasto trasto) {

        final int index = trastos.indexOf(trasto);
        final Trasto trastoLocal = trasto;

        Snackbar.make(view,"Elimnado "+ trasto.getName(),Snackbar.LENGTH_SHORT)
                .setAction("Deshacer", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        insertTrastoInPosition(trastoLocal, index);
                    }
                }).setCallback(new Snackbar.Callback() {
            @Override
            public void onShown(Snackbar snackbar) {
                super.onShown(snackbar);
            }

            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT ||
                        event == Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE ||
                        event == Snackbar.Callback.DISMISS_EVENT_SWIPE) {
                    new ApiManager(context).deleteTrasto(trastoLocal);
                }
            }
        }).show();
        deleteTrasto(index);
    }

    public void deleteTrasto(int index){
        trastos.remove(index);
        notifyItemRemoved(index);
    }

    public void insertTrasto(Trasto trasto){
        trastos.add(0, trasto);
        notifyItemInserted(0);
    }

    public void insertTrastoInPosition(Trasto trasto, int position){
        trastos.add(position, trasto);
        notifyItemInserted(position);
    }

    public void setDataSet(ArrayList<Trasto> trastos){
        this.trastos = trastos;
        notifyDataSetChanged();
    }
}
