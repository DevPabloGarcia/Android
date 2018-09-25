package pablo.com.mipiso.managers.dataManager.repository;

import pablo.com.mipiso.managers.dataManager.BaseCallback;
import pablo.com.mipiso.model.Flat;
import pablo.com.mipiso.model.Paid;
import pablo.com.mipiso.model.User;

public interface DataRepository {

    void login(String userName, String userPass, final BaseCallback<User> listener);

    void registerUser(String userName, String userPass, final BaseCallback<User> listener);

    void getUser(String id, BaseCallback listener);

    void getUsers(BaseCallback listener);

    void saveFlat(Flat flat, BaseCallback listener);

    void getFlat(String id, BaseCallback listener);

    void getFlats(BaseCallback listener);

    void savePaid(Paid paid, BaseCallback listener);

    void getPaid(String id, BaseCallback listener);

}
