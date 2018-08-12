package pablogarcia.dotournament.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by CICE on 22/3/16.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> fragments;

    public MainViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Disponibles";
                break;
            case 1:
                title = "Mis Partidos";
                break;
            case 2:
                title = "Finalizados";
                break;
        }
        return  title;
    }
}
