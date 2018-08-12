package pablogarcia.dotournament.activity.detail;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.apimanager.ApiManager;
import pablogarcia.dotournament.customview.CircularImageView;
import pablogarcia.dotournament.model.User;
import pablogarcia.dotournament.utils.Consts;

public class DetailUserActivity extends AppCompatActivity{

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.image_view) CircularImageView imageView;
    @Bind(R.id.text_view_name) TextView editTextName;
    @Bind(R.id.edit_text_surname) TextView editTextSurname;
    @Bind(R.id.edit_text_mail) TextView editTextMail;

    private ApiManager apiManager;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        ButterKnife.bind(this);

        apiManager = ApiManager.getInstance(this);
        apiManager.checkSession();

        setupToolbar();
        getIntents();
        setupUserInfo();
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

    public void onClickContact(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"+user.getEmail()));
        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    //region Private Methods

    private void setupToolbar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(getString(R.string.user_detail));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }
    }

    private void getIntents(){
        Intent intent = getIntent();
        user = intent.getParcelableExtra(Consts.USER_KEY);
    }

    private void setupUserInfo(){
        if(user!=null){
            apiManager.loadImageIntoImageView(this, user.getImage(),imageView);
            editTextName.setText(user.getName());
            editTextSurname.setText(user.getSurname());
            editTextMail.setText(user.getEmail());
        }
        else{
            Log.i("ERROR:", "No se pudo encontrar el Jugador");
        }
    }


    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    //endregion
}
