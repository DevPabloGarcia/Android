package garcia.pablo.butterknife.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import garcia.pablo.butterknife.R;
import garcia.pablo.butterknife.model.Player;

/**
 * Created by V on 23/1/16.
 */
public class RecyclerViewAdapater extends RecyclerView.Adapter<PlayerViewHolder> {

    ArrayList<Player> players;
    Context context;

    public RecyclerViewAdapater(Context context) {
        this.context = context;
    }

    public RecyclerViewAdapater (Context context, ArrayList<Player> players){
        this.context = context;
        this.players = players;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.row_player,parent,false);
        return new PlayerViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.bind(players.get(position));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
