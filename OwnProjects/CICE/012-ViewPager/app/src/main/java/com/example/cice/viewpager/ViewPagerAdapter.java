package com.example.cice.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by cice on 3/12/15.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    // declaramos el array de los fragments.
    ArrayList<Fragment> fragmentArrayList;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragmentArrayList.get(position);
    }

    // Aqui declaramos que como máximo se va a llamar a NUM_PAGES paginas es decir nunca accederemos a una posicion vacía.
    @Override
    public int getCount() {
        return this.fragmentArrayList.size();
    }

    public void addFramgent(Fragment fragment){
       this.fragmentArrayList.add(fragment);
    }
}
