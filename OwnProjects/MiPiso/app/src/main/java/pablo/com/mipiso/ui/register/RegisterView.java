package pablo.com.mipiso.ui.register;

import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BaseView;

public interface RegisterView extends BaseView{

    void expandRegister();

    void collapseRegister();

    void removeFocus();

    void navigateMainActivity(User user);

}
