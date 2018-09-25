package pablo.com.mipiso.di.component;


import dagger.Component;
import pablo.com.mipiso.di.PerActivity;
import pablo.com.mipiso.di.modules.ActivityModule;
import pablo.com.mipiso.ui.login.LoginActivity;
import pablo.com.mipiso.ui.main.MainActivity;
import pablo.com.mipiso.ui.main.taskList.TasksListFragment;
import pablo.com.mipiso.ui.register.RegisterActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(RegisterActivity registerActivity);

    void inject(TasksListFragment tasksListFragment);

}

