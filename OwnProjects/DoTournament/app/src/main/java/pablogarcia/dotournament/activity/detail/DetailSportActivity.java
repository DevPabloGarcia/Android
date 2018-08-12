package pablogarcia.dotournament.activity.detail;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.apimanager.ApiManager;
import pablogarcia.dotournament.model.Sport;
import pablogarcia.dotournament.utils.Consts;

public class DetailSportActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.image_view) ImageView imageView;
    @Bind(R.id.text_view_name) TextView textViewName;
    @Bind(R.id.text_view_description) TextView textViewDescription;

    private ApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sport);

        ButterKnife.bind(this);

        apiManager = ApiManager.getInstance(this);
        setupToolbar();
        Sport sport = getSportFromIntent();
        bind(sport);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }
    }

    private void bind(Sport sport){
        if(sport.getImageBitmap()!= null){
            imageView.setImageBitmap(sport.getImageBitmap());
        }else{
            apiManager.loadImageIntoImageView(this, sport.getImage(),imageView);
        }
        textViewName.setText(sport.getName());
        textViewDescription.setText(sport.getDescription());
    }

    private Sport getSportFromIntent(){
        Intent intent = getIntent();
        return intent.getParcelableExtra(Consts.SPORT_KEY);
    }


}
