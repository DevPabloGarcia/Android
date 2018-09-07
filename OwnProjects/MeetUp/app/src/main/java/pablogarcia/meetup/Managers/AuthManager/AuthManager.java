package pablogarcia.meetup.Managers.AuthManager;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

public class AuthManager implements FacebookCallback<LoginResult>{

    private OnAuthListener onAuthListener;

    public AuthManager(OnAuthListener onAuthListener) {
        this.onAuthListener = onAuthListener;
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        onAuthListener.onLoginSuccess(loginResult);
    }

    @Override
    public void onCancel() {
        onAuthListener.onLoginCancel();
    }

    @Override
    public void onError(FacebookException error) {
        onAuthListener.onLoginFailure(error.getMessage());
    }
}
