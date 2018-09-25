package pablo.com.mipiso.di.modules;

import dagger.Module;
import dagger.Provides;
import pablo.com.mipiso.ui.base.BaseActivity;
import pablo.com.mipiso.ui.login.LoginPresenter;
import pablo.com.mipiso.ui.main.MainPresenter;
import pablo.com.mipiso.ui.main.taskList.TasksListPresenter;
import pablo.com.mipiso.ui.register.RegisterPresenter;
import pablo.com.mipiso.usecase.base.GetUsersUseCase;
import pablo.com.mipiso.usecase.base.LoginUseCase;
import pablo.com.mipiso.usecase.base.RegisterUseCase;

@Module
public class ActivityModule {

    private final BaseActivity mActivity;

    public ActivityModule(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    LoginPresenter loginPresenter(LoginUseCase loginUseCase){
        return new LoginPresenter(loginUseCase);
    }

    @Provides
    MainPresenter mainPresenter(){
        return new MainPresenter();
    }

    @Provides
    RegisterPresenter registerPresenter(RegisterUseCase registerUseCase){
        return new RegisterPresenter(registerUseCase);
    }

    @Provides
    TasksListPresenter tasksListPresenter(GetUsersUseCase getUsersUseCase){
        return new TasksListPresenter(getUsersUseCase);
    }

    @Provides
    GetUsersUseCase getUsersUseCase() {
        return new GetUsersUseCase();
    }

    @Provides
    LoginUseCase loginUseCase(){
        return new LoginUseCase();
    }

    @Provides
    RegisterUseCase registerUseCase(){return  new RegisterUseCase();}
}
