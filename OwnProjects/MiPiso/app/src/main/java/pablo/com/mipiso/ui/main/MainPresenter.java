package pablo.com.mipiso.ui.main;

import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainView> {

    private User user;

    public void setUser(User user){
        this.user = user;
    }

    public void onSelectedTaskTab(){
        if(this.getView()!=null){
            this.getView().showTaskFragment();
        }
    }

    public void onSelectedPaidTab(){
        if(this.getView()!=null){
            this.getView().showPaidFragment();
        }
    }

    public void onSelectedFlatTab(){
        if(this.getView()!=null){
            if(this.user.getFlat() != null){
                this.getView().showFlatFragment();
            }else{
                this.getView().showNoFlatFragment();
            }
        }
    }

    public void onSelectedSettingTab(){
        if(this.getView()!=null){
            this.getView().showSettingsFragment();
        }
    }

}
