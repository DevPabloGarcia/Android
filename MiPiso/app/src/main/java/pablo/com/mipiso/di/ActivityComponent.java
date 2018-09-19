package pablo.com.mipiso.di;


import javax.inject.Singleton;

import dagger.Component;
import pablo.com.mipiso.ui.login.LoginActivity;
import pablo.com.mipiso.ui.main.MainActivity;

@Singleton
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);
}
