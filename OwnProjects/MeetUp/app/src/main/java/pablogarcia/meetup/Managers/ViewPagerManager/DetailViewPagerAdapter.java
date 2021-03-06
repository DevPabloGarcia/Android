package pablogarcia.meetup.Managers.ViewPagerManager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class DetailViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public DetailViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    public void setFragments(ArrayList<Fragment> fragments){
        this.fragments = fragments;
    }

}

