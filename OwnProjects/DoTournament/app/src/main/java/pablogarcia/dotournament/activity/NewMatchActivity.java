package pablogarcia.dotournament.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.apimanager.ApiManager;
import pablogarcia.dotournament.customview.CustomProgressDialog;
import pablogarcia.dotournament.fragment.DatePickerFragment;
import pablogarcia.dotournament.fragment.TimePickerFragment;
import pablogarcia.dotournament.model.Match;
import pablogarcia.dotournament.model.PlaceTournament;
import pablogarcia.dotournament.model.Sport;
import pablogarcia.dotournament.spinner.adapter.SportSpinnerAdapter;
import pablogarcia.dotournament.utils.Consts;

public class NewMatchActivity extends AppCompatActivity{

    private static final String DATE_KEY    = "date_key";
    private static final String TIME_KEY    = "time_key";
    private static final int PLACE_PICKER_REQUEST = 0;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.edit_text_date) EditText editTextDate;
    @Bind(R.id.edit_text_init_time) EditText editTextInitTime;
    @Bind(R.id.edit_text_end_time) EditText editTextEndTime;
    @Bind(R.id.edit_text_num_players) EditText editTextNumPlayers;
    @Bind(R.id.spinner_sport) Spinner spinnerSport;
    @Bind(R.id.place_picker) EditText editTextPlacePicker;

    private ApiManager apiManager;
    private Date matchDate;
    private Date initTime;
    private Date endTime;
    private PlaceTournament place;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);

        context = this;
        ButterKnife.bind(this);
        apiManager = ApiManager.getInstance(this);

        setupToolbar();
        setupSpinner();
        setupEditTexts();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {

                Place placeGoogle = PlacePicker.getPlace(context, data);

                place = new PlaceTournament(placeGoogle.getLatLng(), placeGoogle.getName().toString(), placeGoogle.getAddress().toString());

                editTextPlacePicker.setText(place.getName());
            }
        }
    }

    //region Setup

    private void setupToolbar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(getString(R.string.add_match));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }
    }

    private void setupSpinner(){

        SportSpinnerAdapter spinnerAdapter = new SportSpinnerAdapter(this, R.layout.sport_spinner_item);
        spinnerAdapter.setDataSet(apiManager.getAllSportsFromLocalDatabase());
        spinnerAdapter.setDropDownViewResource(R.layout.sport_spinner_item_dropdown);
        spinnerSport.setAdapter(spinnerAdapter);

    }

    private void setupEditTexts(){
        setupEditTextDate();
        setupEditTextInitTime();
        setupEditTextEndTime();
        setupEditTextPlacePicker();
    }

    private void setupEditTextDate(){
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void setupEditTextInitTime(){
        editTextInitTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hourString = apiManager.getFinalTimeString(hourOfDay);
                        String minuteString = apiManager.getFinalTimeString(minute);

                        String finalTime =  hourString + ":" + minuteString;
                        editTextInitTime.setText(finalTime);
                        editTextInitTime.setError(null);
                        initTime = apiManager.getDateFormat(1900, 1, 1, hourOfDay, minute);
                    }
                });
            }
        });
    }

    private void setupEditTextEndTime(){
        editTextEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hourString = apiManager.getFinalTimeString(hourOfDay);
                        String minuteString = apiManager.getFinalTimeString(minute);

                        String finalTime =  hourString + ":" + minuteString;
                        editTextEndTime.setText(finalTime);
                        editTextEndTime.setError(null);
                        endTime = apiManager.getDateFormat(1900,1,1,hourOfDay, minute);
                    }
                });
            }
        });
    }

    private void setupEditTextPlacePicker(){
        final Activity activity = this;
        editTextPlacePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(activity), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //endregion

    //region Date Picker

    private void showDatePicker(){

        DatePickerFragment fragment = new DatePickerFragment();
        DatePickerDialog.OnDateSetListener listener = createOnDateSetListener();
        fragment.setmListener(listener);
        fragment.show(getSupportFragmentManager(), DATE_KEY);

    }

    private DatePickerDialog.OnDateSetListener createOnDateSetListener(){
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String finalDate = dayOfMonth+"-"+monthOfYear+"-"+year;
                editTextDate.setText(String.valueOf(finalDate));
                editTextDate.setError(null);
                matchDate = apiManager.getDateFormat(year, monthOfYear, dayOfMonth, 10 ,0);
            }
        };
    }

    //endregion

    //region Time Picker
    private void showTimePicker(TimePickerDialog.OnTimeSetListener listener){

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setmListener(listener);
        fragment.show(getSupportFragmentManager(), TIME_KEY);
    }

    //endregion

    //region Private Methods

    private ArrayList<EditText> getEditTextList(){

        ArrayList<EditText> list = new ArrayList<>();

        list.add(editTextDate);
        list.add(editTextInitTime);
        list.add(editTextEndTime);
        list.add(editTextNumPlayers);
        list.add(editTextPlacePicker);

        return list;
    }

    private Match createMatch() {

        int numPlayers = Integer.valueOf(editTextNumPlayers.getText().toString().trim());
        Sport sport = (Sport) spinnerSport.getSelectedItem();
        return new Match(sport, matchDate, initTime, endTime, numPlayers, place, apiManager.getCurrentUser());
    }

    private void returnPreviousActivity(Match match){

        Toast.makeText(NewMatchActivity.this, getString(R.string.toast_match_inserted), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();

        intent.putExtra(Consts.MATCH_KEY_ADDED, match);
        setResult(RESULT_OK, intent);

        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    //endregion

    public void onClickSubmit(View view) {

        if (apiManager.validateInputsTime(editTextInitTime, editTextEndTime, getString(R.string.wrong_time_error))) {

            ArrayList<EditText> list = getEditTextList();
            if (apiManager.validateInputs(list, getString(R.string.empty_field_error))) {
                Match match = createMatch();
                MatchTask task = new MatchTask();
                task.execute(match);
            }
        }

    }

    private class MatchTask extends AsyncTask<Match, Void, Match> {

        CustomProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new CustomProgressDialog(context,context.getString(R.string.progress_create_match_title),context.getString(R.string.progress_create_match_message));
            super.onPreExecute();
        }

        @Override
        protected Match doInBackground(Match... params) {
            try {

                String id = apiManager.saveMatchInDatabase(params[0]);
                params[0].setId(id);
                params[0].addPlayerToMatch(apiManager.getCurrentUser());

                return params[0];
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Match match) {
            dialog.dismiss();
            if (match != null){
                returnPreviousActivity(match);
            }
            super.onPostExecute(match);
        }
    }


}
