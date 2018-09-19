package com.dev.i.a.v.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by V on 11/1/16.
 */
public class Tab1Fragment extends Fragment {

    public static Tab1Fragment newInstance() {
        return new Tab1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_tab_1,container,false);
        return layout;
    }
}
