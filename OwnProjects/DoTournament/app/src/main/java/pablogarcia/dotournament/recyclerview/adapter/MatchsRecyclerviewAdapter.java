package pablogarcia.dotournament.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pablogarcia.dotournament.R;
import pablogarcia.dotournament.model.Match;
import pablogarcia.dotournament.recyclerview.clickrowinterface.OnClickMatchRow;
import pablogarcia.dotournament.recyclerview.viewholder.MatchesViewHolder;

/**
 * Created by V on 25/3/16.
 */
public class MatchsRecyclerviewAdapter extends RecyclerView.Adapter<MatchesViewHolder> {

    private ArrayList<Match> matches;
    private OnClickMatchRow mListener;

    public MatchsRecyclerviewAdapter(OnClickMatchRow listener) {
        this.matches = new ArrayList<>();
        this.mListener = listener;
    }

    @Override
    public MatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_match, parent, false);
        return new MatchesViewHolder(layout, mListener);
    }

    @Override
    public void onBindViewHolder(MatchesViewHolder holder, int position) {
        holder.bind(matches.get(position));

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    @Override
    public void onViewRecycled(MatchesViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cancelTask();
    }

    public void setDataSet(ArrayList<Match> matches){
        this.matches = matches;
        notifyDataSetChanged();
    }

    public void addItem(Match match){

        int index = calculateIndex(match);
        addMatchInIndex(index, match);
        notifyDataSetChanged();
    }

    public void replaceItem(Match match){
        int index = 0;
        while((index < matches.size()) && !(match.getId().equals(matches.get(index).getId()))){
            index++;
        }
        if(index != matches.size()) {
            matches.set(index, match);
            notifyItemChanged(index);
        }
    }

    private int calculateIndex(Match match){
        int index = 0;
        while((index < matches.size()) && (match.getDate().compareTo(matches.get(index).getDate()) > 0)){
            index++;
        }
        return index;
    }

    private void addMatchInIndex(int index, Match match){
        if(index >= matches.size()){
            this.matches.add(match);
        }else{
            this.matches.add(index,match);
        }
    }
}
