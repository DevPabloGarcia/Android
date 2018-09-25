package pablo.com.mipiso.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pablo.com.mipiso.BaseApplication;

@Module
public class ApplicationModule {

    private final BaseApplication mApplication;


    public ApplicationModule(BaseApplication application) {
        mApplication = application;
    }

    /**
     * Provide a single application object.
     *
     * @return a new BaseApplication object
     **/
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }
}
