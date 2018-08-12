package pablogarcia.hastengroupapp.Activities.StartActivity;

import android.os.Handler;


public class StartInteractor {

    interface OnShowProgressBar {

        void onSuccess();

        void onError();

    }

    public void navigateMainActivity(final OnShowProgressBar listener){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess();
            }
        },2000);


    }


}
