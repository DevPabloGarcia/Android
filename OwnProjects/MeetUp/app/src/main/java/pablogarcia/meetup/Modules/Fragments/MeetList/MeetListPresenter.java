package pablogarcia.meetup.Modules.Fragments.MeetList;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;


public class MeetListPresenter implements MeetListInteractor.OnMeetListListener {

    private MeetListView meetListView;
    private MeetListInteractor meetListInteractor;

    public MeetListPresenter(MeetListView meetListView, MeetListInteractor meetListInteractor) {
        this.meetListView = meetListView;
        this.meetListInteractor = meetListInteractor;
    }

    public void setupViewPager(ViewPager viewPager, FragmentManager fm){
        this.meetListInteractor.setupViewPager(viewPager, fm);
    }

    public void setupFab(FloatingActionButton fab){
        this.meetListInteractor.setupFab(fab,this);
    }

    @Override
    public void onClickFab() {
        this.meetListView.navigateNewMeet();
    }
}
