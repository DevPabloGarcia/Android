package pablo.com.mipiso.ui.main;

import pablo.com.mipiso.ui.base.BaseView;

public interface MainView extends BaseView{

    void showTaskFragment();

    void showFlatFragment();

    void showNoFlatFragment();

    void showPaidFragment();

    void showSettingsFragment();

    void setupBottomNavigation();

}
