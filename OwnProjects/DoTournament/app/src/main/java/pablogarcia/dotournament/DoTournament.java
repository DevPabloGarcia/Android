package pablogarcia.dotournament;

import android.app.Application;

import com.parse.Parse;

import pablogarcia.dotournament.utils.Consts;

/**
 * Created by CICE on 12/4/16.
 */
public class DoTournament extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(Consts.BACK4APP_APP_KEY)
                .server(Consts.BACK4APP_SERVER)
                .clientKey(Consts.BACK4APP_CLIENT_KEY)
                .enableLocalDataStore()
                .build()
        );

    }

}
