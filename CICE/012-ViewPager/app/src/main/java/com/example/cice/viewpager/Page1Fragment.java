package com.example.cice.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cice on 3/12/15.
 */
public class Page1Fragment extends Fragment {

    public static Page1Fragment newInstance() {

        Bundle args = new Bundle();
        Page1Fragment fragment = new Page1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Cuando se crea le estoy inflando el xml, es decir, como sabemos un xml no se puede mostrar por si mismo
    // Tenemos que inflarlo en una vista, por lo tanto cuando se llama esta
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_1,container,false);
    }
}
