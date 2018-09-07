package pablogarcia.meetup.Modules.Main;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import pablogarcia.meetup.Managers.SessionManager.OnSessionListener;

public class MainPresenter implements MainInteractor.OnMainListener, OnSessionListener{

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractor();
    }

    public void setupNavigationView(NavigationView navigationView){
        mainInteractor.setupNavigationView(navigationView, this, this);
    }

    public void onOptionItemSelected(MenuItem item){
        mainInteractor.onOptionItemSelected(item, this);
    }

    public void updateUserImage(){
        this.mainInteractor.updateUserImage(this);
    }

    public void updateUserName(){
        this.mainInteractor.updateUserName(this);
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

    @Override
    public void setUserImage(String image) {
        if(this.mainView != null){
            this.mainView.setUserImage(image);
        }
    }

    @Override
    public void setUserName(String name) {
        if(this.mainView != null){
            this.mainView.setUserName(name);
        }
    }


    @Override
    public void onLogOutFinished() {
        if(this.mainView != null){
            this.mainView.navigateLogin();
        }
    }
}
