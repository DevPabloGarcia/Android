package pablogarcia.hastengroupapp.Activities.StartActivity;

import android.content.Context;
import android.util.Log;

public class StartPresenter implements StartInteractor.OnShowProgressBar{

    private StartView startView;
    private StartInteractor startInteractor;

    public StartPresenter(StartView startView, StartInteractor startInteractor) {
        this.startView = startView;
        this.startInteractor = startInteractor;
    }


    public void navigateMainActivity(){
        if(startView != null){
            startView.showProgressBar();
        }
        startInteractor.navigateMainActivity(this);
    }

    public void onDestroy(){
        startView = null;
    }

    @Override
    public void onSuccess() {
        startView.navigateMainActivity();
    }

    @Override
    public void onError() {
        Log.e("Error", "Something wrong");
    }
}
