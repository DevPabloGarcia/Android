package pablo.com.mipiso.managers.dataManager.repository;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import pablo.com.mipiso.managers.dataManager.BaseCallback;
import pablo.com.mipiso.managers.mappingManager.UserMapper;
import pablo.com.mipiso.model.Flat;
import pablo.com.mipiso.model.Paid;
import pablo.com.mipiso.model.User;

public class ParseRepository implements DataRepository {

    @Override
    public void getUsers(BaseCallback listener) {

    }

    @Override
    public void saveFlat(Flat flat, BaseCallback listener) {

    }

    @Override
    public void getFlat(String id, BaseCallback listener) {

    }

    @Override
    public void getFlats(BaseCallback listener) {

    }

    @Override
    public void savePaid(Paid paid, BaseCallback listener) {

    }

    @Override
    public void getPaid(String id, BaseCallback listener) {

    }

    @Override
    public void login(String userName, String userPass, final BaseCallback<User> callback){
        ParseUser.logInInBackground(userName, userPass, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null){
                    callback.onSuccess(new UserMapper().map(user));
                }else{
                    callback.onCancel(e);
                }
            }
        });
    }

    @Override
    public void registerUser(String userName, String userPass, final BaseCallback<User> callback){

        final ParseUser user = new ParseUser();

        user.setUsername(userName);
        user.setPassword(userPass);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    callback.onSuccess(new UserMapper().map(ParseUser.getCurrentUser()));
                }else{
                    callback.onCancel(e);
                }
            }
        });
    }

    @Override
    public void getUser(String id, BaseCallback listener) {

    }

}
