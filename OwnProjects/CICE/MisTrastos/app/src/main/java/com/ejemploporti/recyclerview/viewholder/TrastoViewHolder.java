package com.ejemploporti.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejemploporti.mistrastos.R;
import com.ejemploporti.model.Trasto;

/**
 * Created by CICE on 12/1/16.
 */
public class TrastoViewHolder extends RecyclerView.ViewHolder {

    public interface OnRowClickListener{
        void onTrastoClicked(Trasto trasto);
        void onTrastoLongClicked(Trasto trasto);
    }

    private Trasto trasto;
    private ImageView image;
    private TextView textViewName, textViewInfo;
    private OnRowClickListener mListener;

    public TrastoViewHolder(View itemView, OnRowClickListener listener) {
        super(itemView);

        mListener = listener;
        image = (ImageView) itemView.findViewById(R.id.image_view);
        textViewName = (TextView) itemView.findViewById(R.id.text_view_name);
        textViewInfo = (TextView) itemView.findViewById(R.id.text_view_info);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTrastoClicked(trasto);
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.onTrastoLongClicked(trasto);

                return true;
            }
        });
    }

    public void bind(Trasto trasto){
        this.trasto = trasto;
        textViewName.setText(trasto.getName());
        textViewInfo.setText(trasto.getInfo());
        image.setImageBitmap(trasto.getImage());
    }
}
