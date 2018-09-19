package pablo.com.mipiso.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import pablo.com.mipiso.di.ActivityComponent;
import pablo.com.mipiso.di.ActivityModule;
import pablo.com.mipiso.di.DaggerActivityComponent;

public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();

    }

    @Override
    public void setContentView(int contentView) {
        super.setContentView(contentView);
        initializeButterKnife();
        onCreatePresenter();
    }

    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();
    }

    public abstract void initializeButterKnife();

    public abstract void onCreatePresenter();

    public abstract void initializeDagger();

    @Override
    public void showToastMessage(int message) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show();
    }
}
