package pablo.com.mipiso.ui.register;

import pablo.com.mipiso.R;
import pablo.com.mipiso.managers.dataManager.BaseCallback;
import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BasePresenter;
import pablo.com.mipiso.usecase.base.RegisterUseCase;

public class RegisterPresenter extends BasePresenter<RegisterView> implements BaseCallback<User>{

    RegisterUseCase registerUseCase;

    public RegisterPresenter(RegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase;
    }

    public void onClickRegister(String userName, String userPass, String userPassConfirm){
        if(getView() != null){

            if(userName.isEmpty() || userPass.isEmpty() || userPassConfirm.isEmpty()){

                getView().showToastMessage(R.string.error_empty_fields);

            }else if(!userPass.equals(userPassConfirm)){

                getView().showToastMessage(R.string.error_pass_confirm);

            }else{
                getView().hideKeyboard();
                getView().expandRegister();
                getView().removeFocus();
                getView().showProgress();

                registerUseCase.registerUser(userName, userPass, this);
            }
        }

    }

    public void onGainFocus(){
        if(getView() != null){
            getView().collapseRegister();
        }
    }

    public void onLostFocus(){
        if(getView() != null){
            getView().expandRegister();
        }
    }

    @Override
    public void onSuccess(User user) {
        if(getView() != null){
            getView().removeFocus();
            getView().hideProgress();
            getView().navigateMainActivity(user);
        }
    }

    @Override
    public void onCancel(Exception e) {
        if(getView() != null){
            getView().hideProgress();
            getView().showAlertMessage(e.getMessage());
        }
    }
}
