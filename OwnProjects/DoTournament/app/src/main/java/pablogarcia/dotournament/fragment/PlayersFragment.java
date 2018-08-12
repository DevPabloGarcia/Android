package pablogarcia.dotournament.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.activity.detail.DetailUserActivity;
import pablogarcia.dotournament.apimanager.ApiManager;
import pablogarcia.dotournament.model.User;
import pablogarcia.dotournament.recyclerview.adapter.PlayerRecyclerviewAdapter;
import pablogarcia.dotournament.recyclerview.clickrowinterface.OnClickPlayerRow;
import pablogarcia.dotournament.utils.Consts;

/**
 * Created by CICE on 22/3/16.
 */
public class PlayersFragment extends MainFragment implements FindCallback<ParseUser>, OnClickPlayerRow {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private PlayerRecyclerviewAdapter adapter;
    private ApiManager apiManager;

    public static PlayersFragment newInstance() {

        Bundle args = new Bundle();

        PlayersFragment fragment = new PlayersFragment();
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

    private void setupRecyclerView(){
        apiManager.getAllPlayersFromDatabase(this);
        ArrayList<User> users = new ArrayList<>();

        adapter = new PlayerRecyclerviewAdapter(users, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setupToolbar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar!= null){
            actionBar.setTitle(getString(R.string.players));
        }
    }

    @Override
    public void done(List<ParseUser> users, ParseException e) {
        if (e==null){
            adapter.setDataSet(apiManager.parseUsersToPlayers(users));
        }else{
            e.printStackTrace();
        }
    }

    @Override
    public void onClickPlayerRow(User user) {
        Intent intent = new Intent(getContext(), DetailUserActivity.class);
        intent.putExtra(Consts.USER_KEY, user);
        getContext().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
