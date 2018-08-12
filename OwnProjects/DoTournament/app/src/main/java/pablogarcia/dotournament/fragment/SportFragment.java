package pablogarcia.dotournament.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.activity.detail.DetailSportActivity;
import pablogarcia.dotournament.apimanager.ApiManager;
import pablogarcia.dotournament.model.Sport;
import pablogarcia.dotournament.recyclerview.adapter.SportRecyclerviewAdapter;
import pablogarcia.dotournament.recyclerview.clickrowinterface.OnClickSportRow;
import pablogarcia.dotournament.utils.Consts;

/**
 * Created by CICE on 22/3/16.
 */
public class SportFragment extends MainFragment implements OnClickSportRow{

    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private ApiManager apiManager;

    public static SportFragment newInstance() {

        Bundle args = new Bundle();
        SportFragment fragment = new SportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.recycler_view, container, false);

        ButterKnife.bind(this, layout);

        this.apiManager = ApiManager.getInstance(getContext());

        this.setupToolbar();
        this.setupRecyclerView();
        this.setupScrollBehaviour(false);

        return layout;
    }

    @Override
    public void onClickSportRow(Sport sport) {
        navigateToSportDetailActivity(sport);
    }

    @Override
    public void setupToolbar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar!= null){
            actionBar.setTitle(getString(R.string.sports));
        }
    }

    private void setupRecyclerView(){

        SportRecyclerviewAdapter adapter = new SportRecyclerviewAdapter(this);
        adapter.setDataSet(apiManager.getAllSportsFromLocalDatabase());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

    private void navigateToSportDetailActivity(Sport sport){
        Intent intent = new Intent(getContext(), DetailSportActivity.class);
        intent.putExtra(Consts.SPORT_KEY, sport);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
