package pablo.com.mipiso.di.modules;

import dagger.Module;
import dagger.Provides;
import pablo.com.mipiso.managers.dataManager.repository.DataRepository;
import pablo.com.mipiso.managers.dataManager.repository.MockDataRepository;

@Module
public class RepositoryModule {

    private final DataRepository mDataRepository;

    public RepositoryModule() {
        mDataRepository = new MockDataRepository();
    }

    @Provides
    DataRepository dataRepository() {
        return mDataRepository;
    }

}
