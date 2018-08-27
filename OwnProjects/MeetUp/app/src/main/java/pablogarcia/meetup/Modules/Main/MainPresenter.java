package pablogarcia.meetup.Modules.Main;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

public class MainPresenter implements MainInteractor.OnMainListener{

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenter(MainView mainView, MainInteractor mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    public void setupNavigationView(NavigationView navigationView){
        mainInteractor.setupNavigationView(navigationView, this);
    }

    public void onOptionItemSelected(MenuItem item){
        mainInteractor.onOptionItemSelected(item, this);
    }

    @Override
    public void openDrawer() {
        if(this.mainView != null){
            this.mainView.openDrawer();
        }
    }

    @Override
    public void onReplaceFragment(Fragment fragment) {
        if(this.mainView != null){
            this.mainView.replaceFragment(fragment);
        }
    }


}
