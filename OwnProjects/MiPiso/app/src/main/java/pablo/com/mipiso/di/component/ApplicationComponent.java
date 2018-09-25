package pablo.com.mipiso.di.component;

import javax.inject.Singleton;

import dagger.Component;
import pablo.com.mipiso.di.modules.ApplicationModule;
import pablo.com.mipiso.di.modules.RepositoryModule;
import pablo.com.mipiso.ui.base.BaseActivity;
import pablo.com.mipiso.usecase.base.BaseUseCase;

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    void inject(BaseUseCase baseUseCase);


}
