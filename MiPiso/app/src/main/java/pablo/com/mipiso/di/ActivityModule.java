package pablo.com.mipiso.di;

import dagger.Module;
import dagger.Provides;
import pablo.com.mipiso.ui.base.BaseActivity;
import pablo.com.mipiso.ui.login.LoginPresenter;
import pablo.com.mipiso.ui.main.MainPresenter;

@Module
public class ActivityModule {

    private final BaseActivity mActivity;

    public ActivityModule(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    LoginPresenter loginPresenter(){
        return new LoginPresenter();
    }

    @Provides
    MainPresenter mainPresenter(){
        return new MainPresenter();
    }
}
