package pablogarcia.dotournament.activity.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseException;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.apimanager.ApiManager;
import pablogarcia.dotournament.model.Match;
import pablogarcia.dotournament.model.User;
import pablogarcia.dotournament.recyclerview.adapter.PlayerRecyclerviewAdapter;
import pablogarcia.dotournament.recyclerview.clickrowinterface.OnClickPlayerRow;
import pablogarcia.dotournament.utils.Consts;

public class DetailMatchActivity extends AppCompatActivity implements OnMapReadyCallback, OnClickPlayerRow {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_view_sport) TextView textViewSport;
    @Bind(R.id.text_view_date) TextView textViewDate;
    @Bind(R.id.text_view_init_time) TextView textViewInitTime;
    @Bind(R.id.text_view_end_time) TextView textViewEndTime;
    @Bind(R.id.text_view_creator_name) TextView textViewCreatorName;
    @Bind(R.id.text_view_creator_email) TextView textViewCreatorEmail;
    @Bind(R.id.button_join_quite_to_match) Button buttonJoinQuiteToMatch;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    private ApiManager apiManager;
    private Match match;
    private PlayerRecyclerviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_match);

        ButterKnife.bind(this);
        apiManager = ApiManager.getInstance(this);

        setupToolbar();
        getIntents();
        setupView();
        setupMap();
        setupRecyclerView();
        setupButton();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                returnPreviousActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        returnPreviousActivity();
    }

    private void returnPreviousActivity(){

        Intent intent = new Intent();

        intent.putExtra(Consts.MATCH_KEY_PLAYER, match);
        setResult(Activity.RESULT_OK, intent);

        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    private void getIntents(){
        Intent intent = getIntent();
        match = intent.getParcelableExtra(Consts.MATCH_KEY_PLAYER);
    }

    private void setupToolbar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(getString(R.string.toolbar_title_join_match));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }
    }

    private void setupView(){
        if(match!=null){
            textViewSport.setText(match.getSport().getName());
            textViewDate.setText(apiManager.convertDateInString(match.getDate()));
            textViewInitTime.setText(apiManager.convertTimeInString(match.getInitTime()));
            textViewEndTime.setText(apiManager.convertTimeInString(match.getEndTime()));
            textViewCreatorName.setText(match.getOwner().getName());
            textViewCreatorEmail.setText(match.getOwner().getEmail());
        }
    }

    private void setupRecyclerView(){

        adapter = new PlayerRecyclerviewAdapter(match.getPlayers(), this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setupButton(){
        if(!match.isPlayerInMatch(apiManager.getCurrentUser().getId())){

            if(match.isAvailableSites()){
                buttonJoinQuiteToMatch.setText(getString(R.string.join_match));
                buttonJoinQuiteToMatch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickJoin();
                    }
                });
            }else{
                buttonJoinQuiteToMatch.setText(getString(R.string.match_full));
                buttonJoinQuiteToMatch.setEnabled(false);
            }
        }else{
            buttonJoinQuiteToMatch.setText(getString(R.string.remove_from_match));
            buttonJoinQuiteToMatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickRemove();
                }
            });
        }
    }

    private void setupMap(){
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    public void onClickJoin() {
        JoinPlayerTask task = new JoinPlayerTask();
        task.execute();
    }

    public void onClickRemove(){
        RemovePlayerTask task = new RemovePlayerTask();
        task.execute();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try{
            double lat = match.getPlace().getLatLng().latitude;
            double lng = match.getPlace().getLatLng().longitude;
            String name = match.getPlace().getName();
            String address = match.getPlace().getAddress();

            LatLng point = new LatLng(lat, lng);

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .title(name)
                    .snippet(address)
                    .draggable(false)
                    .visible(true));

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void onClickPlayerRow(User user) {
        Intent intent = new Intent(this, DetailUserActivity.class);
        intent.putExtra(Consts.USER_KEY, user);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private class JoinPlayerTask extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                apiManager.addPlayerToMatchInDatabase(match.getId());
                return true;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if(success){
                Toast.makeText(DetailMatchActivity.this, "Añadido al partido", Toast.LENGTH_SHORT).show();
                match.addPlayerToMatch(apiManager.getCurrentUser());
                setupButton();
            }else{
                Toast.makeText(DetailMatchActivity.this, "Mal", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class RemovePlayerTask extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                apiManager.removePlayerFromMatch(match.getId());
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if(success){
                Toast.makeText(DetailMatchActivity.this, "Quitado del partido", Toast.LENGTH_SHORT).show();
                match.removePlayerFromMatch(apiManager.getCurrentUser());
                setupButton();
            }else{
                Toast.makeText(DetailMatchActivity.this, "Mal", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
