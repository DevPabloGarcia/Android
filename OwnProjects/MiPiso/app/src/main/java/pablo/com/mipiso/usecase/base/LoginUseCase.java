package pablo.com.mipiso.usecase.base;

import pablo.com.mipiso.managers.dataManager.BaseCallback;
import pablo.com.mipiso.model.User;

public class LoginUseCase extends BaseUseCase {

    public void getData(String id, BaseCallback listener){
        mRepository.getUser(id, listener);
    }

    public void login(String userName, String userPass, BaseCallback<User> listener){
        mRepository.login(userName, userPass, listener);
    }
}
