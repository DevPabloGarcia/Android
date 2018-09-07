package pablogarcia.meetup.Modules.Main;


import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;


public interface MainView {

    void setupNavigationView(NavigationView navigationView);

    void openDrawer();

    void replaceFragment(Fragment fragment);

    void navigateLogin();

    void updateUserInfo();

    void setUserImage(String image);

    void setUserName(String name);

}
