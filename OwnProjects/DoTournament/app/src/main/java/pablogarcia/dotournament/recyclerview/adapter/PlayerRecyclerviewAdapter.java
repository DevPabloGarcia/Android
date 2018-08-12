package pablogarcia.dotournament.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pablogarcia.dotournament.R;
import pablogarcia.dotournament.model.User;
import pablogarcia.dotournament.recyclerview.clickrowinterface.OnClickPlayerRow;
import pablogarcia.dotournament.recyclerview.viewholder.PlayerViewHolder;

/**
 * Created by V on 23/3/16.
 */
public class PlayerRecyclerviewAdapter extends RecyclerView.Adapter<PlayerViewHolder>{

    private ArrayList<User> users;
    private OnClickPlayerRow mListener;

    public PlayerRecyclerviewAdapter(ArrayList<User> users, OnClickPlayerRow listener) {
        this.users = users;
        this.mListener = listener;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_player, parent, false);
        return new PlayerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, final int position) {
        holder.bind(users.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickPlayerRow(users.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onViewRecycled(PlayerViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cancelTask();
    }

    public void setDataSet(ArrayList<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

}
