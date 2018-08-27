package pablogarcia.meetup.Modules.Main;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.R;

public class MainActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;
    @BindView(R.id.navigationView) NavigationView navigationView;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        this.mainPresenter = new MainPresenter(this, new MainInteractor());

        this.setupNavigationView(this.navigationView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mainPresenter.onOptionItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupNavigationView(NavigationView navigationView) {
        mainPresenter.setupNavigationView(navigationView);
    }


    @Override
    public void openDrawer() {
        this.drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

}
