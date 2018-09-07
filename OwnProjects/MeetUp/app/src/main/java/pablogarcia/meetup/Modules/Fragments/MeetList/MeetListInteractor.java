package pablogarcia.meetup.Modules.Fragments.MeetList;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import pablogarcia.meetup.Managers.LocalRepositoryManager.LocalRepository;
import pablogarcia.meetup.Managers.ViewPagerManager.ViewPagerAdapter;
import pablogarcia.meetup.Model.Meet;
import pablogarcia.meetup.Modules.Fragments.RecyclerView.RecyclerViewFragment;

public class MeetListInteractor {

    public interface OnMeetListListener{

        void onClickFab();

    }

    ViewPagerAdapter viewPagerAdapter;

    public void setupViewPager(ViewPager viewPager, FragmentManager fm){

        ArrayList<Fragment> fragments = new ArrayList<>();

        RecyclerViewFragment fragmentCurrent = RecyclerViewFragment.newInstance(true);
        RecyclerViewFragment fragmentFinished = RecyclerViewFragment.newInstance(false);

        fragments.add(fragmentCurrent);
        fragments.add(fragmentFinished);

        this.viewPagerAdapter = new ViewPagerAdapter(fm);
        this.viewPagerAdapter.setFragments(fragments);

        viewPager.setAdapter(this.viewPagerAdapter);
    }

    public void setupFab(FloatingActionButton fab, final OnMeetListListener mListener){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickFab();
            }
        });
    }
}
