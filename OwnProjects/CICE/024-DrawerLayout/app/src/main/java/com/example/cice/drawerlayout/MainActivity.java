package com.example.cice.drawerlayout;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;

    MainFragment fragment1,fragment2,fragment3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindings();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.frame_layout,fragment1).commit();

        setupNavigationView();

        setupToolbar();

    }

    private void bindings(){

        fragment1 = MainFragment.newInstance(-451235);
        fragment2 = MainFragment.newInstance(-8156);
        fragment3 = MainFragment.newInstance(-127885157);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

    }

    private void setupNavigationView(){

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment1).commit();
                        break;
                    case R.id.nav_settings:
                        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment2).commit();
                        break;
                    case R.id.nav_social:
                        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment3).commit();
                        break;
                    default:
                        break;
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });

        navigationView.setItemIconTintList(ContextCompat.getColorStateList(this, R.color.my_icon_tint));
        navigationView.setItemTextColor(ContextCompat.getColorStateList(this, R.color.my_icon_tint));
        navigationView.setItemTextAppearance(R.style.myNavigationText);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       /* Se puede hacer esto o lo que viene abajo
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        */

        /* O se puede hacer esto*/
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            drawerLayout.openDrawer(GravityCompat.START);

        return super.onOptionsItemSelected(item);
    }

}
