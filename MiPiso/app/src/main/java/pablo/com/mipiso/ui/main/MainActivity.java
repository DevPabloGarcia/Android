package pablo.com.mipiso.ui.main;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import pablo.com.mipiso.R;
import pablo.com.mipiso.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainView{

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    public void onCreatePresenter() {
        mainPresenter.setView(this);
    }

    @Override
    public void initializeDagger() {
        getActivityComponent().inject(this);
    }

}
