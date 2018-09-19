package com.ejemploporti.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ejemploporti.mistrastos.R;
import com.ejemploporti.api.ApiManager;
import com.ejemploporti.recyclerview.adapter.TrastoRecyclerAdapter;
import com.ejemploporti.model.Trasto;

import java.util.ArrayList;

/**
 * Created by CICE on 7/1/16.
 */
public class RecyclerViewFragment extends Fragment {

    private static final String ARG_TRASTOS = "trastos";

    RecyclerView recyclerView;
    TrastoRecyclerAdapter adapter;
    ApiManager apiManager;
    ArrayList<Trasto> trastos;

    public static RecyclerViewFragment newInstance(ArrayList<Trasto> trastos, boolean withFAB) {

        Bundle args = new Bundle();

        args.putParcelableArrayList(ARG_TRASTOS, trastos);

        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        this.trastos = args.getParcelableArrayList(ARG_TRASTOS);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_recycler_view,container,false);

        this.apiManager = new ApiManager(getContext());

        recyclerView = (RecyclerView)layout.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TrastoRecyclerAdapter(getContext());
        recyclerView.setAdapter(adapter);

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setDataSet(trastos);
    }

    public void insertTrasto(Trasto trasto){
        adapter.insertTrasto(trasto);
        recyclerView.scrollToPosition(0);
    }

    public void setDataSet(ArrayList<Trasto> trastos){

        this.trastos = trastos;
        getArguments().putParcelableArrayList(ARG_TRASTOS, trastos);

        if(adapter!=null)
            adapter.setDataSet(trastos);
    }
}
