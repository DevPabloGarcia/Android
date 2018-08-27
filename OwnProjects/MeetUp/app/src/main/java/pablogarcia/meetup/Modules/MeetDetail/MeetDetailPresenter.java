package pablogarcia.meetup.Modules.MeetDetail;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import pablogarcia.meetup.Model.Meet;

public class MeetDetailPresenter implements MeetDetailInteractor.OnDetailListener{

    private MeetDetailView meetDetailView;
    private MeetDetailInteractor meetDetailInteractor;

    public MeetDetailPresenter(MeetDetailView meetDetailView, MeetDetailInteractor meetDetailInteractor) {
        this.meetDetailView = meetDetailView;
        this.meetDetailInteractor = meetDetailInteractor;
    }

    public void onOptionItemSelected(MenuItem item){
        this.meetDetailInteractor.onOptionItemSelected(item, this);
    }

    @Override
    public void navigateBack() {
        this.meetDetailView.navigateBack();
    }

    public void setupViewPager(ViewPager viewPager, FragmentManager fm, Meet meet, ViewPager.OnPageChangeListener pageChangeListener){
        this.meetDetailInteractor.setupViewPager(viewPager, fm, meet, pageChangeListener);
    }
}
