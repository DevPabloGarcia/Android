package com.ejemploporti.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ejemploporti.fragment.InventoryFragment;
import com.ejemploporti.mistrastos.R;
import com.ejemploporti.fragment.CategoryFragment;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    NavigationView navigationView;
    Fragment fragmentIventory, fragmentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        fragmentIventory = InventoryFragment.newInstance();
        fragmentCategory = CategoryFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragmentIventory)
                .commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.inventory:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragmentIventory).commit();
                        break;
                    case R.id.categories:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragmentCategory).commit();
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
