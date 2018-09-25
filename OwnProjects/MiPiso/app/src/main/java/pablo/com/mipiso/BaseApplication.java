package pablo.com.mipiso;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseInstallation;

import pablo.com.mipiso.di.component.ApplicationComponent;
import pablo.com.mipiso.di.component.DaggerApplicationComponent;


public class BaseApplication extends Application {

    private static Context mContext;
    private ApplicationComponent mApplicationComponent;

    public BaseApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        setupParse();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return BaseApplication.mContext;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .build();
    }

    private void setupParse(){
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

}
