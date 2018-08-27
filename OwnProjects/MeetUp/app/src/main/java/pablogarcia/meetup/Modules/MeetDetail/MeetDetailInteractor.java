package pablogarcia.meetup.Modules.MeetDetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import java.util.ArrayList;

import pablogarcia.meetup.Managers.LocalRepositoryManager.LocalRepository;
import pablogarcia.meetup.Managers.ViewPagerManager.DetailViewPagerAdapter;
import pablogarcia.meetup.Managers.ViewPagerManager.ViewPagerAdapter;
import pablogarcia.meetup.Model.Meet;
import pablogarcia.meetup.Modules.Fragments.DetailImage.DetailImageFragment;
import pablogarcia.meetup.Modules.Fragments.RecyclerView.RecyclerViewFragment;

public class MeetDetailInteractor {

    interface OnDetailListener{

        void navigateBack();

    }

    DetailViewPagerAdapter detailViewPagerAdapter;

    public void onOptionItemSelected(MenuItem item, OnDetailListener listener){

        switch (item.getItemId()){
            case android.R.id.home:
                listener.navigateBack();
                break;
        }
    }

    public void setupViewPager(ViewPager viewPager, FragmentManager fm, Meet meet, ViewPager.OnPageChangeListener pageChangeListener){

        ArrayList<Fragment> fragments = new ArrayList<>();

        DetailImageFragment f1 = DetailImageFragment.newInstance(meet);
        DetailImageFragment f2 = DetailImageFragment.newInstance(meet);

        fragments.add(f1);
        fragments.add(f2);

        this.detailViewPagerAdapter = new DetailViewPagerAdapter(fm);
        this.detailViewPagerAdapter.setFragments(fragments);

        viewPager.setAdapter(this.detailViewPagerAdapter);
        viewPager.addOnPageChangeListener(pageChangeListener);
    }
}
