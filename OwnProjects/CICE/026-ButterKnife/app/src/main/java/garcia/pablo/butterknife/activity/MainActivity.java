package garcia.pablo.butterknife.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;
import garcia.pablo.butterknife.R;
import garcia.pablo.butterknife.api.ApiManager;
import garcia.pablo.butterknife.api.PlayerException;
import garcia.pablo.butterknife.database.DataBaseManager;
import garcia.pablo.butterknife.database.PlayerDataBaseManager;
import garcia.pablo.butterknife.fragment.FragmentAdd;
import garcia.pablo.butterknife.fragment.FragmentList;
import garcia.pablo.butterknife.model.Player;
import garcia.pablo.butterknife.viewpager.TabViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.view_pager) ViewPager viewPager;
    @Bind(R.id.tab_layout) TabLayout tabLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;

    PlayerDataBaseManager playerDataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Player player1 = new Player("Pablo","García Núñez", "Bela", "Gil de Biedma nº101","pablogarcia.sg@gmail.com","70255453k",25,1900);

        playerDataBaseManager = new PlayerDataBaseManager(this);

        try {
            new ApiManager(this).insertPlayer(player1);
        } catch (PlayerException e) {
            e.printStackTrace();
        }

        this.setupToolbar();
        this.setupTabPager();
        this.setupTabLayout();

    }

    public void setupToolbar(){
        setSupportActionBar(toolbar);
    }


    public void setupTabPager(){
        Fragment fragmentAdd = new FragmentAdd();
        Fragment fragmentList = new FragmentList();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(fragmentAdd);
        fragments.add(fragmentList);

        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(tabViewPagerAdapter);
    }

    public void setupTabLayout(){
        tabLayout.setupWithViewPager(viewPager);
    }

}
