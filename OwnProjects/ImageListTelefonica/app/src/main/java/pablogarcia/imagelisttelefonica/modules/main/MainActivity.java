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

    /**
     * Create the view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // Update the theme in order to replace the splash screen
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the XML views
        ButterKnife.bind(this);

        // Setup the tolbar
        this.setupToolbar();

        // Create an istance of main presenter
        this.mainPresenter = new MainPresenter(this, new MainInteractor());

        // Obtain the images list data
        this.mainPresenter.getImageList();

    }

    /**
     * Setup the toolbar
     */
    @Override
    public void setupToolbar() {
        setSupportActionBar(this.toolbar);
    }

    /**
     * Show an toast with an error message
     */
    @Override
    public void showErrorToast() {
        Toast.makeText(this,R.string.serviceError, Toast.LENGTH_SHORT).show();
    }

    /**
     * Setup the recyclerview with a list of images
     * @param images
     */
    @Override
    public void setupRecyclerView(ArrayList<Image> images) {
        this.mainPresenter.setupRecyclerView(this.recyclerView, images, this, this);
    }

    /**
     * Navigate to the detail image view
     * @param image - The object data
     */
    @Override
    public void navigateDetailImage(Image image) {
        // Create a new intent
        Intent intent = new Intent(this, DetailActivity.class);
        // Put the image object
        intent.putExtra(Consts.KEY_DETAIL, image);
        // Navigate the next view
        startActivity(intent);
        // Set an animation
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * Row click action
     * @param image
     */
    @Override
    public void onClickImageRow(Image image) {
        this.navigateDetailImage(image);
    }
}
