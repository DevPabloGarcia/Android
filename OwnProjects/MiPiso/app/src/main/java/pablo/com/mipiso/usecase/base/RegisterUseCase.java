package pablo.com.mipiso.usecase.base;

import pablo.com.mipiso.managers.dataManager.BaseCallback;
import pablo.com.mipiso.model.User;

public class RegisterUseCase extends BaseUseCase {

    public void registerUser(String userName, String userPass, BaseCallback<User> callback){
        mRepository.registerUser(userName, userPass, callback);
    }

}
