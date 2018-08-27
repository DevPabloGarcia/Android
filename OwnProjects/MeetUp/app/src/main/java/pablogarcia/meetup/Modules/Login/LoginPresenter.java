package pablogarcia.meetup.Modules.Login;

import android.util.Log;

public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener{

    LoginInteractor loginInteractor;
    LoginView loginView;

    public LoginPresenter(LoginInteractor loginInteractor, LoginView loginView) {
        this.loginInteractor = loginInteractor;
        this.loginView = loginView;
    }

    public void login(String userName, String userPass){
        loginInteractor.login(userName, userPass, this);
    }


    @Override
    public void onSucces() {
        loginView.navigateMainActivity();
    }

    @Override
    public void onFail() {
        Log.e("Error", "Something Wrong!");
    }

    @Override
    public void onUserNameError() {
        if(loginView != null){
            loginView.showUserNameError();
        }
    }

    @Override
    public void onUserPassError() {
        if(loginView != null){
            loginView.showUserPassError();
        }
    }
}
