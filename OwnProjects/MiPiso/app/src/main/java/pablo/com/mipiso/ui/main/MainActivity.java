package pablo.com.mipiso.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablo.com.mipiso.R;
import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BaseActivity;
import pablo.com.mipiso.ui.main.noFlat.NoFlatFragment;
import pablo.com.mipiso.ui.main.taskList.TasksListFragment;

import static pablo.com.mipiso.utils.Consts.USER_ARGS;

public class MainActivity extends BaseActivity implements MainView{

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView navigationView;

    @BindView(R.id.main_progress)
    RelativeLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigation();
        showTaskFragment();
    }

    @Override
    public void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    public void onCreatePresenter() {
        User user = null;
        if(getIntent()!= null){
             user = getIntent().getParcelableExtra(USER_ARGS);
        }
        mainPresenter.setUser(user);
        mainPresenter.setView(this);
    }

    @Override
    public void initializeDagger() {
        getActivityComponent().inject(this);
    }


    @Override
    public void showTaskFragment() {
        TasksListFragment fragment = TasksListFragment.newInstance();
        showFragment(R.id.content_frame, fragment);
    }

    @Override
    public void showNoFlatFragment() {
        NoFlatFragment fragment = NoFlatFragment.newInstance();
        showFragment(R.id.content_frame, fragment);
    }

    @Override
    public void showFlatFragment() {

    }

    @Override
    public void showPaidFragment() {

    }

    @Override
    public void showSettingsFragment() {

    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void setupBottomNavigation() {
        this.navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab_task:
                        mainPresenter.onSelectedTaskTab();
                        return true;
                    case R.id.tab_paid:
                        mainPresenter.onSelectedPaidTab();
                        return true;
                    case R.id.tab_flat:
                        mainPresenter.onSelectedFlatTab();
                        return true;
                    case R.id.tab_settings:
                        mainPresenter.onSelectedSettingTab();
                        return true;
                }
                return false;
            }
        });
    }

}
