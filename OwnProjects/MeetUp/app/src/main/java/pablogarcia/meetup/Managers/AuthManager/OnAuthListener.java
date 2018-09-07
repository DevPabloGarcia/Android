package pablogarcia.meetup.Managers.AuthManager;

import com.facebook.login.LoginResult;

public interface OnAuthListener {

    void onLoginSuccess(LoginResult loginResult);
    void onLoginFailure(String message);
    void onLoginCancel();

}
