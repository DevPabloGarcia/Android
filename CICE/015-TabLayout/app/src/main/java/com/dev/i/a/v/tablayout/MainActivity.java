package com.dev.i.a.v.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Fragment fragment1,fragment2,fragment3,fragment4;
    TabLayout tabLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindings();
        setupToolbar();
        setupViewPager();
        configureTabLayout();

    }

    private void bindings(){
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        fragment1 = Tab1Fragment.newInstance();
        fragment2 = Tab2Fragment.newInstance();
        fragment3 = Tab1Fragment.newInstance();
        fragment4 = Tab2Fragment.newInstance();
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setupToolbar(){

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Tab Layout Aplication");
        }
    }

    private void setupViewPager(){
        // Creamos los fragments y su lista.
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);

        //añadimos el adapter al viewpager.
        viewPager.setAdapter(new TabViewPagerAdapter(getSupportFragmentManager(), fragments));
    }

    private void configureTabLayout(){

        tabLayout.setupWithViewPager(viewPager);

        // Color de la barra de selección.
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.tabIndicator));

        // Ancho de la barra de selección.
        tabLayout.setSelectedTabIndicatorHeight(Helper.dpToPx(this, 5));

        // Darle color a la letra cuando este seleccionada y cuando no.
        tabLayout.setTabTextColors(ContextCompat.getColor(this,R.color.tabNoSelectedText),ContextCompat.getColor(this,R.color.tabSelectedText));

        // Damos a la barra de tabs un color de background.
        tabLayout.setBackgroundResource(R.color.colorAccent);

        // Podemos coger cada tab individualmente y darle estilo por separado.

        TabLayout.Tab tab = tabLayout.getTabAt(0);
        if (tab != null) {
            tab.setIcon(R.mipmap.ic_launcher);
        }

        TabLayout.Tab tab2 = tabLayout.getTabAt(1);
        View customTab = LayoutInflater.from(this).inflate(R.layout.custom_tab,null,false);
        tab2.setCustomView(customTab);

        // Podemos desde aqui manejar eventos, por ejemplo, cambiamos el color dependiendo de cual se seleccione.
        /* Esto da problemas.

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimary));
                        break;
                    case 1:
                        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(MainActivity.this,R.color.tabIndicator));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }
}
