package com.ejemploporti.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ejemploporti.mistrastos.R;
import com.ejemploporti.activity.MainActivity;
import com.ejemploporti.activity.NewTrastoActivity;
import com.ejemploporti.api.ApiManager;
import com.ejemploporti.model.Trasto;
import com.ejemploporti.viewpager.MainViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by CICE on 7/1/16.
 */
public class InventoryFragment extends Fragment {

    private static final int REQ_CODE = 1;

    Toolbar toolbar;
    AppBarLayout appBarLayout;
    TabLayout tabLayout;
    ViewPager viewPager;
    RecyclerViewFragment allTrastoFragment, onSaleFragment, notOnSaleFragment;
    MainActivity mainActivity;
    ApiManager apiManager;

    public static InventoryFragment newInstance() {

        return new InventoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        apiManager = new ApiManager(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_inventory,container,false);

        doBindings(layout);
        setupToolbar();
        setupTabLayout();

        //FAB
        FloatingActionButton fab = (FloatingActionButton) layout.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewTrastoActivity.class);
                startActivityForResult(intent, REQ_CODE);
            }
        });


        return layout;
    }

    private void doBindings(View layout){
        appBarLayout = (AppBarLayout) layout.findViewById(R.id.appbar_layout);
        toolbar = (Toolbar) layout.findViewById(R.id.toolbar);
        tabLayout = (TabLayout) layout.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) layout.findViewById(R.id.view_pager);
    }

    private void setupToolbar(){
        setHasOptionsMenu(true);
        mainActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mainActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Mis Trastos");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupViewPager(){

        ArrayList<Fragment> fragments = new ArrayList<>();

        allTrastoFragment = RecyclerViewFragment.newInstance(apiManager.getTrastos(),true);
        onSaleFragment = RecyclerViewFragment.newInstance(apiManager.getOnSaleTrastos(),false);
        notOnSaleFragment = RecyclerViewFragment.newInstance(apiManager.getNotOnSaleTrastos(),false);

        fragments.add(allTrastoFragment);
        fragments.add(onSaleFragment);
        fragments.add(notOnSaleFragment);
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(mainViewPagerAdapter);
    }

    private void setupTabLayout(){
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mainActivity.drawerLayout.openDrawer(GravityCompat.START);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        // Estamos cogiendo el fragment de la posición 0 y llamamos manualmente al metodo onActivityResult.
        /*getChildFragmentManager()
                .getFragments()
                .get(0)*/

        onSaleFragment.setDataSet(new ApiManager(getContext()).getOnSaleTrastos());
        notOnSaleFragment.setDataSet(new ApiManager(getContext()).getNotOnSaleTrastos());
        if (requestCode == REQ_CODE && resultCode == Activity.RESULT_OK) {
            Trasto trasto = data.getParcelableExtra("trasto");
            allTrastoFragment.insertTrasto(trasto);
        }

        //allTrastoFragment.onActivityResult(requestCode, resultCode, data);
    }
}
