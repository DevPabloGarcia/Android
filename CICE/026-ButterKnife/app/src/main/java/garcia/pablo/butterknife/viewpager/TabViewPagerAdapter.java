package garcia.pablo.butterknife.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by V on 23/1/16.
 */
public class TabViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentArrayList;

    public TabViewPagerAdapter(FragmentManager fm ,ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Añadir Jugador";
            case 1:
                return "Lista Jugadores";
            default:
                return "";
        }
    }
}
