package pablogarcia.meetup.Managers.RecyclerViewManager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pablogarcia.meetup.Model.Meet;
import pablogarcia.meetup.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MeetViewHolder> {

    private ArrayList<Meet> meets;
    private OnClickMeetRow listener;

    public RecyclerViewAdapter(OnClickMeetRow listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MeetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_meet, parent, false);
        return new MeetViewHolder(view, this.listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetViewHolder holder, int position) {
        holder.bind(meets.get(position));
    }

    @Override
    public int getItemCount() {
        if(meets != null){
            return meets.size();
        }
        return 0;
    }

    public void setDataSet(ArrayList<Meet> meets){
        this.meets = meets;
        notifyDataSetChanged();
    }
}
