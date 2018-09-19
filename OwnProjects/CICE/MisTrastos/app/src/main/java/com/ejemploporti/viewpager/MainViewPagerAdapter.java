package com.ejemploporti.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by CICE on 7/1/16.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments;

    public MainViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String strToReturn = "";
        switch (position){
            case 0:
                strToReturn =  "Inventario";
                break;
            case 1:
                strToReturn = "En Venta";
                break;
            case 2:
                strToReturn = "No a la venta";
                break;
        }

        return strToReturn;
    }
}
