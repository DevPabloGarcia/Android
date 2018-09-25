package pablo.com.mipiso.ui.base;

import android.app.Activity;

public interface BaseView {

    void showToastMessage(int message);

    void showProgress();

    void hideProgress();

    void hideKeyboard();

    void showAlertMessage(String message);

}
