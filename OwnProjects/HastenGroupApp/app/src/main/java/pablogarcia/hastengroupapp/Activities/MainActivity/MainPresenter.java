package pablogarcia.hastengroupapp.Activities.MainActivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;

import pablogarcia.hastengroupapp.Model.Sport;

public class MainPresenter implements MainInteractor.OnGetSportListener{

    MainInteractor interactor;
    MainView mainView;

    public MainPresenter(MainInteractor interactor, MainView mainView) {
        this.interactor = interactor;
        this.mainView = mainView;
    }

    @Override
    public void onSuccess(ArrayList<Sport> sports) {
        if(mainView != null){
            mainView.setupTabLayout(sports);
        }
    }

    @Override
    public void onFailure() {

        Log.i("Error", "No Sports founded");
    }

    public void getSports(){
        if(interactor != null){
            interactor.getSports(this);
        }
    }

    public void setupViewPager(ViewPager viewPager, ArrayList<Sport> sports, FragmentManager fm){
        if(interactor != null){
            interactor.setupViewPager(viewPager, sports, fm);
        }
    }
}
