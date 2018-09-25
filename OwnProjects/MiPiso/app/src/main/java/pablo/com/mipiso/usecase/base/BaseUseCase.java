package pablo.com.mipiso.usecase.base;

import javax.inject.Inject;

import pablo.com.mipiso.di.component.DaggerApplicationComponent;
import pablo.com.mipiso.di.modules.RepositoryModule;
import pablo.com.mipiso.managers.dataManager.repository.DataRepository;

public abstract class BaseUseCase {

    @Inject
    DataRepository mRepository;

    public BaseUseCase() {
        DaggerApplicationComponent.builder()
                .repositoryModule(new RepositoryModule())
                .build()
                .inject(this);
    }
}
