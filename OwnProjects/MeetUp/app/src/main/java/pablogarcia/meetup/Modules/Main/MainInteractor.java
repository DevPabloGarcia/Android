package pablogarcia.meetup.Modules.Main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pablogarcia.meetup.ApiManager;
import pablogarcia.meetup.Managers.SessionManager.OnSessionListener;
import pablogarcia.meetup.Modules.Fragments.MeetList.MeetListFragment;
import pablogarcia.meetup.Modules.Fragments.Settings.SettingsFragment;
import pablogarcia.meetup.R;


public class MainInteractor {

    interface OnMainListener{

        void openDrawer();

        void onReplaceFragment(Fragment fragment);


        void setUserImage(String image);

        void setUserName(String name);
    }

    private MeetListFragment meetListFragment;
    private SettingsFragment settingsFragment;

    public void setupNavigationView(NavigationView navigationView, final OnMainListener listener, final OnSessionListener sessionListener){

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
                        break;
                    case R.id.logOut:
                        ApiManager.getInstance().logOut(sessionListener);
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


    public void updateUserImage(OnMainListener listener){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        ApiManager apiManager = ApiManager.getInstance();

        if(currentUser != null){
            listener.setUserImage(apiManager.getFacebookImage(currentUser));
        }
    }

    public void updateUserName(OnMainListener listener){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            listener.setUserName(currentUser.getDisplayName());
        }
    }

}
