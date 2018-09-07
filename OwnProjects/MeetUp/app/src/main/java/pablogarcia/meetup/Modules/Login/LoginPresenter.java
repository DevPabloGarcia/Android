package pablogarcia.meetup.Modules.Login;


import com.facebook.login.LoginResult;

import pablogarcia.meetup.Managers.AuthManager.OnAuthListener;

public class LoginPresenter implements OnAuthListener, LoginInteractor.OnLoginListener {

    private LoginInteractor loginInteractor;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginInteractor = new LoginInteractor();
        this.loginView = loginView;
    }

    public void checkUserLogged(){
        this.loginInteractor.checkUserLogged(this);
    }

    @Override
    public void onLoginSuccess(LoginResult loginResult) {
        loginInteractor.handleFacebookAccessToken(loginResult, this);
    }

    @Override
    public void onLoginFailure(String message) {
        loginView.showToastMessage(message);
    }

    @Override
    public void onLoginCancel() {}

    @Override
    public void onLoginComplete() {
        this.loginView.navigateMainActivity();
    }
}
