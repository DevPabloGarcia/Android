package pablo.com.mipiso.ui.login;


import pablo.com.mipiso.R;
import pablo.com.mipiso.managers.appPreferences.AppPreferences;
import pablo.com.mipiso.managers.dataManager.BaseCallback;
import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BasePresenter;
import pablo.com.mipiso.usecase.base.LoginUseCase;

public class LoginPresenter extends BasePresenter<LoginView> implements BaseCallback<User>{

    private LoginUseCase loginUseCase;

    public LoginPresenter(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    public void onClickLogin(String userName, String userPass){
        if(getView() != null) {

            if (!userName.isEmpty() && !userPass.isEmpty()) {

                getView().hideKeyboard();
                getView().expandLogin();
                getView().removeFocus();
                getView().showProgress();

                loginUseCase.login(userName, userPass,this);

            } else {

                this.getView().showToastMessage(R.string.error_empty_fields);

            }
        }
    }

    public void onClickRegister(){
        if(this.getView() != null){
            this.getView().removeFocus();
            this.getView().navigateRegisterActivity();
        }
    }

    @Override
    public void onSuccess(User user) {
        if(this.getView()!=null){
            this.getView().hideProgress();
            this.getView().navigateMainActivity(user);
        }
    }

    @Override
    public void onCancel(Exception e) {
        this.getView().hideProgress();
    }

    public void onGainFocus(){
        if(getView() != null){
            getView().collapseLogin();
        }
    }

    public void onLostFocus(){
        if(getView() != null){
            getView().expandLogin();
        }
    }

}
