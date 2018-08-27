package pablogarcia.meetup.Modules.Fragments.MeetList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.Modules.Main.MainActivity;
import pablogarcia.meetup.Modules.NewMeet.NewMeetActivity;
import pablogarcia.meetup.R;

public class MeetListFragment extends Fragment implements MeetListView{

    private MeetListPresenter meetListPresenter;
    private MainActivity mainActivity;

    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.fab) FloatingActionButton fab;

    public static MeetListFragment newInstance() {
        
        Bundle args = new Bundle();

        MeetListFragment fragment = new MeetListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meet_list, container, false);
        ButterKnife.bind(this, view);

        this.meetListPresenter = new MeetListPresenter(this, new MeetListInteractor());
        this.meetListPresenter.setupViewPager(this.viewPager, getChildFragmentManager());
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.setupToolbar();
        this.setupFab();


        return view;
    }

    @Override
    public void setupToolbar() {
        this.toolbar.setNavigationIcon(R.drawable.ic_menu);
        mainActivity.setSupportActionBar(this.toolbar);
        ActionBar actionBar = mainActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void navigateNewMeet() {
        Intent intent = new Intent(getContext(), NewMeetActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void setupFab(){
        meetListPresenter.setupFab(fab);
    }

}
