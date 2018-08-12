package pablogarcia.dotournament.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.apimanager.ApiManager;
import pablogarcia.dotournament.back4app.database.tables.TableUser;
import pablogarcia.dotournament.fragment.MainFragment;
import pablogarcia.dotournament.fragment.PlayersFragment;
import pablogarcia.dotournament.fragment.SportFragment;
import pablogarcia.dotournament.fragment.ViewPagerFragment;

public class MainActivity extends AppCompatActivity implements ViewPagerFragment.ContainerActivityCallback{

    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.navigation_view) NavigationView navigationView;
    @Bind(R.id.tab_layout) TabLayout tabLayout;
    @Bind(R.id.FAB_add) FloatingActionButton fabNew;

    private FragmentManager fragmentManager;
    private MainFragment viewPagerFragment, playerFragment, sportFragment;
    private ApiManager apiManager;
    private ParseUser currentUser;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        ButterKnife.bind(this);

        apiManager = ApiManager.getInstance(this);
        apiManager.checkSession();
        currentUser = ParseUser.getCurrentUser();

        this.setupToolbar();
        this.setupFragments();
        this.replaceWithFirstFragment();
        this.setupNavigationView();

    }

    //region Override Methods

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupTabLayout(ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showFab(boolean show) {
        if(show){
            fabNew.show();
        }else{
            fabNew.hide();
        }
    }

    @Override
    public void onBackPressed() {
        apiManager.logOut();
        super.onBackPressed();
    }

    //endregion

    //region Setup

    private void setupFragments(){
        fragmentManager = getSupportFragmentManager();
        viewPagerFragment = ViewPagerFragment.newInstance();
        playerFragment = PlayersFragment.newInstance();
        sportFragment = SportFragment.newInstance();
    }

    private void setupToolbar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    private void setupHeaderView(){
        View headerView = navigationView.getHeaderView(0);
        setupImageHeaderView(headerView);
        setupNameHeaderView(headerView);
    }

    private void setupImageHeaderView(View headerView){
        String imageUrl = currentUser.getParseFile(TableUser.COLUMN_IMAGE).getUrl().replace("https","http");
        ImageView imageView = (ImageView)headerView.findViewById(R.id.image_view);
        apiManager.loadImageIntoImageView(this, imageUrl, imageView);
    }

    private void setupNameHeaderView(View headerView){
        ((TextView)headerView.findViewById(R.id.text_view_name)).setText(currentUser.getUsername());
    }

    private void setupNavigationView(){
        setupHeaderView();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_match:
                        putViewPagerFragment();
                        break;
                    case R.id.nav_players:
                        putPlayerFragment();
                        break;
                    case R.id.nav_sports:
                        putSportFragment();
                        break;
                    case R.id.nav_log_out:
                        apiManager.logOut();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        break;
                    case R.id.nav_social:
                        apiManager.shareInSocialNetworks(context, getString(R.string.share_app));

                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    //endregion

    //region Fragments Methods

    private void replaceWithFirstFragment(){
        putViewPagerFragment();
    }

    private void putViewPagerFragment(){
        showFab(true);
        fragmentManager.beginTransaction().replace(R.id.frame_layout, viewPagerFragment).commit();
        tabLayout.setVisibility(View.VISIBLE);
    }

    private void putPlayerFragment(){
        showFab(false);
        fragmentManager.beginTransaction().replace(R.id.frame_layout, playerFragment).commit();
        tabLayout.setVisibility(View.GONE);
    }

    private void putSportFragment(){
        showFab(false);
        fragmentManager.beginTransaction().replace(R.id.frame_layout, sportFragment).commit();
        tabLayout.setVisibility(View.GONE);
    }

    //endregion

    public void onFABClick(View view) {
        ViewPagerFragment fragment = (ViewPagerFragment) fragmentManager.findFragmentById(R.id.frame_layout);
        fragment.add();
    }

}