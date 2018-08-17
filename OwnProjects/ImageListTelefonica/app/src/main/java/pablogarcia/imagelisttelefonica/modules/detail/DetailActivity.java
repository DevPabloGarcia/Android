package pablogarcia.imagelisttelefonica.modules.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        this.detailPresenter = new DetailPresenter(this, new DetailInteractor());

        this.setupToolbar();
        this.getIntents();
        this.updateImage(this.image);


    }

    @Override
    public void getIntents() {
        Intent intent = getIntent();
        this.image = intent.getParcelableExtra(Consts.KEY_DETAIL);
    }

    @Override
    public void updateImage(Image image) {
        Picasso.get()
                .load(image.getImgePath())
                .fit()
                .centerCrop()
                .into(this.detailImageView);
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void navigateBack() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.detailPresenter.onOptionItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}
