package pablo.com.mipiso.usecase.base;

import pablo.com.mipiso.managers.dataManager.BaseCallback;

public class GetUsersUseCase extends BaseUseCase{

    public void getData(BaseCallback listener){
        mRepository.getUsers(listener);
    }

}
