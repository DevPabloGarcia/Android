package pablo.com.mipiso.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import pablo.com.mipiso.BaseApplication;
import pablo.com.mipiso.R;
import pablo.com.mipiso.di.component.ActivityComponent;
import pablo.com.mipiso.di.component.ApplicationComponent;
import pablo.com.mipiso.di.component.DaggerActivityComponent;
import pablo.com.mipiso.di.modules.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private static BaseActivity mContext;

    AlertDialog mAlertMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
        mContext = this;
    }

    @Override
    public void setContentView(int contentView) {
        super.setContentView(contentView);
        initializeButterKnife();
        onCreatePresenter();
    }

    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder().applicationComponent(getApplicationComponent()).activityModule(new ActivityModule(this)).build();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }

    public abstract void initializeButterKnife();

    public abstract void onCreatePresenter();

    public abstract void initializeDagger();

    @Override
    public void showToastMessage(int message) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show();
    }

    public static BaseActivity getContext() {
        return mContext;
    }

    /**
     * Show fragment and replace current section.
     */
    protected void showFragment(int containerViewId, Fragment fragment) {
        try {
            String tag = fragment.getClass().getSimpleName();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(containerViewId, fragment, tag).commit();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void hideKeyboard() {
        View view = this.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showAlertMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.button_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        mAlertMessage = builder.create();
        try {
            mAlertMessage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
