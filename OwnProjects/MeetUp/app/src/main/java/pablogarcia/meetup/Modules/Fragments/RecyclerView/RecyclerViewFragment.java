package pablogarcia.meetup.Modules.Fragments.RecyclerView;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.Managers.RecyclerViewManager.OnClickMeetRow;
import pablogarcia.meetup.Model.Meet;
import pablogarcia.meetup.Modules.MeetDetail.MeetDetailActivity;
import pablogarcia.meetup.R;
import pablogarcia.meetup.Utils.Consts;

public class RecyclerViewFragment extends Fragment implements RecyclerViewView, OnClickMeetRow{

    private static final String MEET_ARGS = "MEET";

    private RecyclerViewPresenter recyclerViewPresenter;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    public static RecyclerViewFragment newInstance(ArrayList<Meet> meets) {
        
        Bundle args = new Bundle();

        args.putParcelableArrayList(MEET_ARGS, meets);
        RecyclerViewFragment fragment = new RecyclerViewFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        ButterKnife.bind(this, view);
        this.recyclerViewPresenter = new RecyclerViewPresenter(this, new RecyclerViewInteractor());
        this.recyclerViewPresenter.onCreateView(this.recyclerView, container.getContext(), getArguments().<Meet>getParcelableArrayList(MEET_ARGS), this);

        return view;
    }

    @Override
    public void onClickMeetRow(Meet meet) {
        this.navigateMeetDetailView(meet);
    }

    @Override
    public void navigateMeetDetailView(Meet meet) {
        Intent intent = new Intent(getContext(), MeetDetailActivity.class);
        intent.putExtra(Consts.MEET_KEY, meet);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }



}
