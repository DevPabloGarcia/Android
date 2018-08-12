package pablogarcia.hastengroupapp.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pablogarcia.hastengroupapp.Model.Player;
import pablogarcia.hastengroupapp.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<PlayerViewHolder>{

    private ArrayList<Player> players;


    public RecyclerViewAdapter() {
        this.players = new ArrayList<>();
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_row, parent, false);
        return new PlayerViewHolder(layout);
    }


    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.bind(players.get(position));

    }

    @Override
    public int getItemCount() {
        if(players != null) {
            return players.size();
        }
        return 0;
    }

    public void setDataSet(ArrayList<Player> players){
        this.players = players;
        notifyDataSetChanged();
    }
}
