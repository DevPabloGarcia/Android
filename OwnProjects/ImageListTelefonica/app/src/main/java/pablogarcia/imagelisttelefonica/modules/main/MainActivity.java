package pablogarcia.imagelisttelefonica.modules.main;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.imagelisttelefonica.R;
import pablogarcia.imagelisttelefonica.managers.recyclerViewManager.OnClickImageRow;
import pablogarcia.imagelisttelefonica.model.Image;
import pablogarcia.imagelisttelefonica.modules.detail.DetailActivity;
import pablogarcia.imagelisttelefonica.utils.Consts;

public class MainActivity extends AppCompatActivity implements MainView, OnClickImageRow{

    private MainPresenter mainPresenter;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        this.setupToolbar();

        this.mainPresenter = new MainPresenter(this, new MainInteractor());
        this.mainPresenter.getImageList();

    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(this.toolbar);
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(this,R.string.serviceError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupRecyclerView(ArrayList<Image> images) {
        this.mainPresenter.setupRecyclerView(this.recyclerView, images, this, this);
    }

    @Override
    public void navigateDetailImage(Image image) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Consts.KEY_DETAIL, image);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onClickImageRow(Image image) {
        this.navigateDetailImage(image);
    }
}
