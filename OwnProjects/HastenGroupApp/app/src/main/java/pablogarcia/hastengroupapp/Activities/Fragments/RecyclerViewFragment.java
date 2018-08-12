package pablogarcia.hastengroupapp.Activities.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.hastengroupapp.Model.Player;
import pablogarcia.hastengroupapp.R;
import pablogarcia.hastengroupapp.RecyclerView.RecyclerViewAdapter;

public class RecyclerViewFragment extends Fragment {

    private static final String ARG_PLAYERS = "players";

    @BindView(R.id.playersRecyclerView) RecyclerView recyclerView;

    private RecyclerViewAdapter adapter;
    private ArrayList<Player> players;


    public static RecyclerViewFragment newInstance(ArrayList<Player> players) {
        
        Bundle args = new Bundle();

        args.putParcelableArrayList(ARG_PLAYERS, players);

        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        setDataSet(getArguments().<Player>getParcelableArrayList(ARG_PLAYERS));

        return view;
    }

    public void setDataSet(ArrayList<Player> players){
        this.players = players;
        getArguments().putParcelableArrayList(ARG_PLAYERS, players);
        if(adapter != null){
            adapter.setDataSet(players);
        }
    }
}
