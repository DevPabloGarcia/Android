package com.example.cice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cice on 3/12/15.
 */
public class MainFragment extends Fragment{

    public static MainFragment newInstance() {
        
        Bundle args = new Bundle();
        // Ponemos argumentos
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Recogemos argumentos.
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_main,container,false);
        //Unimos la vista que con el controlador ( vista:fragment_mail.xml Controlador: MainFragment)
        return view;
    }
    
    
}
