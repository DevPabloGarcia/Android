package com.example.cice.drawerlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CICE on 22/12/15.
 */
public class MainFragment extends Fragment{

    int color;

    public static MainFragment newInstance(int color) {

        Bundle args = new Bundle();
        args.putInt("color",color);

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        color = getArguments().getInt("color");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_main,container,false);
        layout.setBackgroundColor(color);
        return layout;

    }
}
