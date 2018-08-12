package pablogarcia.dotournament.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseException;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.activity.detail.DetailMatchActivity;
import pablogarcia.dotournament.apimanager.ApiManager;
import pablogarcia.dotournament.model.Match;
import pablogarcia.dotournament.recyclerview.adapter.MatchsRecyclerviewAdapter;
import pablogarcia.dotournament.recyclerview.clickrowinterface.OnClickMatchRow;
import pablogarcia.dotournament.utils.Consts;

/**
 * Created by CICE on 22/3/16.
 */
public class MatchsFragment extends Fragment implements OnClickMatchRow{

    public interface OnActivityResultCallback{
        void onActivityResultCallback();
    }

    private static final String MATCHES_CONDITION = "matches_condition";
    
    @Bind(R.id.recycler_view) RecyclerView recyclerViewTournaments;
    @Bind(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;

    private ApiManager apiManager;
    private MatchsRecyclerviewAdapter adapter;
    private int matchesCondition;
    private MatchTask task;
    private OnActivityResultCallback mListener;

    public static MatchsFragment newInstance(int matchesCondition) {

        Bundle args = new Bundle();
        args.putInt(MATCHES_CONDITION, matchesCondition);
        MatchsFragment fragment = new MatchsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiManager = ApiManager.getInstance(getContext());
        matchesCondition = getArguments().getInt(MATCHES_CONDITION, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.swipe_recycler_view, container, false);

        ButterKnife.bind(this, layout);

        this.setupRecyclerView();
        this.setupSwipeRefreshLayout();
        this.setupData();

        return layout;
    }

    protected void setupData(){
        task = new MatchTask();
        task.execute(matchesCondition);
    }

    protected void setmListener(OnActivityResultCallback listener){
        mListener = listener;
    }

    private void setupRecyclerView(){

        recyclerViewTournaments.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MatchsRecyclerviewAdapter(this);
        recyclerViewTournaments.setAdapter(adapter);
    }

    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshListener());
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
    }

    private void showRefreshing(){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    private void hideRefreshing(){
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Consts.MATCH_CODE_ADDED) {
            if (resultCode == Activity.RESULT_OK) {
                Match match = data.getParcelableExtra(Consts.MATCH_KEY_ADDED);
                adapter.addItem(match);
            }
        } else if (requestCode == Consts.MATCH_CODE_PLAYER) {
            if (resultCode == Activity.RESULT_OK) {
                mListener.onActivityResultCallback();
            }
        }
    }

    public void onClickMatchRow(Match match) {
        navigateToMatchDetailActivity(match);
    }

    private void navigateToMatchDetailActivity(Match match){
        Intent intent = new Intent(getContext(), DetailMatchActivity.class);
        intent.putExtra(Consts.MATCH_KEY_PLAYER, match);
        startActivityForResult(intent, Consts.MATCH_CODE_PLAYER);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private class MatchTask extends AsyncTask<Integer, Void, ArrayList<Match>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showRefreshing();
        }

        @Override
        protected ArrayList<Match> doInBackground(Integer... params) {
            try {
                return apiManager.getMatchesFromDatabase(params[0]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Match> matches) {

            hideRefreshing();

            if(matches != null){
                adapter.setDataSet(matches);
            }
            super.onPostExecute(matches);
        }
    }

    private class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh() {
            task.cancel(true);
            task = new MatchTask();
            task.execute(matchesCondition);
        }
    }
}
