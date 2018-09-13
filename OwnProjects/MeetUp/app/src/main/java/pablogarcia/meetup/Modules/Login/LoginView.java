package pablogarcia.meetup.Modules.Login;


public interface LoginView {

    void navigateMainActivity();

    void navigateRegisterActivity();

    void showToastMessage(String message);

    void setupFacebookLoginButton();

}
