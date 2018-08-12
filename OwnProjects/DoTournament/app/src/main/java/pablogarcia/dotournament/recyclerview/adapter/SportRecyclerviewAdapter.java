package pablogarcia.dotournament.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pablogarcia.dotournament.R;
import pablogarcia.dotournament.model.Sport;
import pablogarcia.dotournament.recyclerview.clickrowinterface.OnClickSportRow;
import pablogarcia.dotournament.recyclerview.viewholder.SportViewHolder;

/**
 * Created by V on 25/3/16.
 */
public class SportRecyclerviewAdapter extends RecyclerView.Adapter<SportViewHolder> {

    ArrayList<Sport> sports;
    OnClickSportRow mListener;

    public SportRecyclerviewAdapter(OnClickSportRow listener) {
        this.sports = new ArrayList<>();
        this.mListener = listener;
    }

    @Override
    public SportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sport, parent, false);

        return new SportViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(SportViewHolder holder, final int position) {

        holder.bind(sports.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickSportRow(sports.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return sports.size();
    }

    @Override
    public void onViewRecycled(SportViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cancelTask();
    }

    public void setDataSet(ArrayList<Sport> sports){
        this.sports = sports;
        notifyDataSetChanged();
    }

}
