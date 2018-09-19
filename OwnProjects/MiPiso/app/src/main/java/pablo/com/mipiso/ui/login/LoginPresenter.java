package pablo.com.mipiso.ui.login;

import pablo.com.mipiso.R;
import pablo.com.mipiso.ui.base.BasePresenter;

public class LoginPresenter extends BasePresenter {

    public void onClickLogin(String user, String pass){
        user = "";
        if(!user.isEmpty() && !pass.isEmpty()){

        }else{
            if(this.getView() != null){
                this.getView().showToastMessage(R.string.error_empty_fields);
            }
        }
    }
}
