package pablo.com.mipiso.ui.login;

import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BaseView;

public interface LoginView extends BaseView{

    void enableLoginButton();

    void disableLoginButton();

    void navigateMainActivity(User user);

    void navigateRegisterActivity();

    void expandLogin();

    void collapseLogin();

    void removeFocus();

}
