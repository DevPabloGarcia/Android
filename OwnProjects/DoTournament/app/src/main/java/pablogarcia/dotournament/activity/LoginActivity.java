package pablogarcia.dotournament.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.apimanager.ApiManager;
import pablogarcia.dotournament.customview.CustomProgressDialog;
import pablogarcia.dotournament.localdatabase.exception.LocalDatabaseException;
import pablogarcia.dotournament.model.Sport;

public class LoginActivity extends AppCompatActivity implements LogInCallback{

    @Bind(R.id.text_view_name) EditText editTextName;
    @Bind(R.id.edit_text_pass) EditText editTextPass;

    ApiManager apiManager;
    CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        this.apiManager = ApiManager.getInstance(this);
        this.setupData();
        this.skipLogin();

    }

    @Override
    public void done(ParseUser user, ParseException e) {
        customProgressDialog.dismiss();
        if(e == null){
            Log.i(getString(R.string.app_name), getString(R.string.log_user_logged));
            goToNextActivity(MainActivity.class);
        }
        else{
            Log.e(getString(R.string.app_name), e.getMessage());
            Toast.makeText(LoginActivity.this, getString(R.string.wrong_pass), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickLogIn(View view){

        String name = editTextName.getText().toString().trim();
        String pass = editTextPass.getText().toString().trim();

        ArrayList<EditText> editTexts = new ArrayList<>();
        editTexts.add(editTextName);
        editTexts.add(editTextPass);

        if(apiManager.validateInputs(editTexts,getString(R.string.empty_field_error))){
            customProgressDialog = new CustomProgressDialog(this, getString(R.string.progress_log_in_title), getString(R.string.progress_log_in_message));
            this.apiManager.logginUser(name, pass, this);
        }
    }

    public void onClickSignIn(View view) {
        goToNextActivity(RegisterUserActivity.class);
    }

    private void skipLogin(){
        if (apiManager.isLoggedIn()){
            goToNextActivity(MainActivity.class);
        }
    }

    private void goToNextActivity(Class<?> s){
        Intent intent = new Intent(this, s);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void setupData(){
        setupSportData();
    }

    private void setupSportData(){
        SportTask task = new SportTask();
        task.execute();
    }

    private class SportTask extends AsyncTask<Void, Void, ArrayList<Sport>> {

        @Override
        protected ArrayList<Sport> doInBackground(Void... params) {
            try {
                return apiManager.getAllSportsFromDatabase();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Sport> sports) {
            try {
                if (sports!=null){
                    apiManager.insertAllSportsInLocalDatabase(sports);
                }
            } catch (LocalDatabaseException e) {
                e.printStackTrace();
            }
            super.onPostExecute(sports);
        }
    }

}
