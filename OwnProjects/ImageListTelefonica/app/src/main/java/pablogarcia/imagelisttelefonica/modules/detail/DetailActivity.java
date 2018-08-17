package pablogarcia.imagelisttelefonica.modules.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.imagelisttelefonica.R;
import pablogarcia.imagelisttelefonica.model.Image;
import pablogarcia.imagelisttelefonica.utils.Consts;

public class DetailActivity extends AppCompatActivity implements DetailView{

    private DetailPresenter detailPresenter;
    private Image image;

    @BindView(R.id.detailImageView) ImageView detailImageView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Update the theme in order to replace the splash screen
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get the XML views
        ButterKnife.bind(this);

        // Create an istance of detail presenter
        this.detailPresenter = new DetailPresenter(this, new DetailInteractor());

        // Setup the toolbar
        this.setupToolbar();

        // Get the intent data
        this.getIntents();

        // Update the imageview
        this.detailPresenter.updateImage(this.image, this.detailImageView);


    }

    /**
     * Get the data from the previous activity
     */
    @Override
    public void getIntents() {
        Intent intent = getIntent();
        this.image = intent.getParcelableExtra(Consts.KEY_DETAIL);
    }



    /**
     * Setup tootbal with back button
     */
    @Override
    public void setupToolbar() {
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Navigate to the previous activity
     */
    @Override
    public void navigateBack() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    /**
     * Set action to the toolbar button
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.detailPresenter.onOptionItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}
