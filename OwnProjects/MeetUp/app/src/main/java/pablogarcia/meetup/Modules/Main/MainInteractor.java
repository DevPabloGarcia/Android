package pablogarcia.meetup.Modules.Main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import pablogarcia.meetup.Modules.Fragments.MeetList.MeetListFragment;
import pablogarcia.meetup.Modules.Fragments.Settings.SettingsFragment;
import pablogarcia.meetup.R;


public class MainInteractor {

    interface OnMainListener{

        void openDrawer();

        void onReplaceFragment(Fragment fragment);
    }

    MeetListFragment meetListFragment;
    SettingsFragment settingsFragment;

    public void setupNavigationView(NavigationView navigationView, final OnMainListener listener){

        meetListFragment = MeetListFragment.newInstance();
        settingsFragment = SettingsFragment.newInstance();

        listener.onReplaceFragment(meetListFragment);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.events:
                        listener.onReplaceFragment(meetListFragment);
                        break;
                    case R.id.settings:
                        listener.onReplaceFragment(settingsFragment);
                }

                return false;
            }
        });

    }

    public void onOptionItemSelected(MenuItem item, OnMainListener listener){
        if(item.getItemId() == android.R.id.home){
            listener.openDrawer();
        }
    }

}
