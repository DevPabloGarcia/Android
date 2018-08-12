package pablogarcia.dotournament.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pablogarcia.dotournament.R;
import pablogarcia.dotournament.activity.NewMatchActivity;
import pablogarcia.dotournament.utils.Consts;
import pablogarcia.dotournament.viewpager.MainViewPagerAdapter;

/**
 * Created by CICE on 22/3/16.
 */
public class ViewPagerFragment extends MainFragment implements MatchsFragment.OnActivityResultCallback {

    public interface ContainerActivityCallback {
        void setupTabLayout(ViewPager viewPager);
        void showFab(boolean show);
    }

    private static final int USER_MATCHES_TAB_POSITION = 1;

    private ViewPager viewPager;
    private ContainerActivityCallback mListener;
    private MatchsFragment enableMatchesFragment, userMatchesFragment, finishedMatchesFragment;

    public static ViewPagerFragment newInstance() {

        Bundle args = new Bundle();

        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_view_pager, container, false);

        this.doBinding(layout);
        this.setupToolbar();
        this.setupViewPager();
        this.setupTabLayout();
        this.setupScrollBehaviour(true);

        return layout;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (ContainerActivityCallback) getActivity();
        }catch (ClassCastException e){
            Log.e("Activity", e.getMessage());
        }

    }

    private void doBinding(View view){
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);

    }

    private void setupViewPager(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        enableMatchesFragment = MatchsFragment.newInstance(Consts.ALL_MATCHES);
        userMatchesFragment = MatchsFragment.newInstance(Consts.USER_MATCHES);
        finishedMatchesFragment = MatchsFragment.newInstance(Consts.FINISHED_MATCHES);

        enableMatchesFragment.setmListener(this);
        userMatchesFragment.setmListener(this);
        finishedMatchesFragment.setmListener(this);

        fragments.add(enableMatchesFragment);
        fragments.add(userMatchesFragment);
        fragments.add(finishedMatchesFragment);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    private void setupTabLayout(){
        mListener.setupTabLayout(viewPager);
    }

    public void add() {
        Intent intent = new Intent(getContext(), NewMatchActivity.class);
        startActivityForResult(intent, Consts.MATCH_CODE_ADDED);
        ((Activity)getContext()).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void setupToolbar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar!= null){
            actionBar.setTitle(getString(R.string.matchs));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Consts.MATCH_CODE_ADDED){
            if(resultCode == Activity.RESULT_OK){
                userMatchesFragment.onActivityResult(requestCode, resultCode,data);
                viewPager.setCurrentItem(USER_MATCHES_TAB_POSITION);
            }
        }
    }

    @Override
    public void onActivityResultCallback() {
        viewPager.setCurrentItem(USER_MATCHES_TAB_POSITION);
        userMatchesFragment.setupData();
        enableMatchesFragment.setupData();
    }

}
