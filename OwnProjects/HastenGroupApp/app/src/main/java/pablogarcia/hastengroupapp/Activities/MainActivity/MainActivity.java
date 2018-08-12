package pablogarcia.hastengroupapp.Activities.MainActivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.hastengroupapp.ApiManager;
import pablogarcia.hastengroupapp.Model.Sport;
import pablogarcia.hastengroupapp.R;


public class MainActivity extends AppCompatActivity implements MainView{

    private MainPresenter presenter;

    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewPager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new MainPresenter(new MainInteractor(), this);

        this.setupToolbar();

        this.presenter.getSports();

    }

    @Override
    public void setupToolbar(){
        setSupportActionBar(this.toolbar);
    }

    @Override
    public void setupTabLayout(ArrayList<Sport> sports){
        presenter.setupViewPager(this.viewPager, sports, getSupportFragmentManager());
        tabLayout.setupWithViewPager(this.viewPager);
    }
}
