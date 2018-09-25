package pablo.com.mipiso.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pablo.com.mipiso.R;
import pablo.com.mipiso.managers.dataManager.repository.MockDataRepository;
import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BaseActivity;
import pablo.com.mipiso.ui.main.MainActivity;
import pablo.com.mipiso.ui.register.RegisterActivity;

import static pablo.com.mipiso.utils.Consts.USER_ARGS;

public class LoginActivity extends BaseActivity implements LoginView{

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.login_button)
    Button buttonLogin;

    @BindView(R.id.user_name)
    TextView userName;

    @BindView(R.id.user_pass)
    TextView userPass;

    @BindView(R.id.register_button)
    Button registerButton;

    @BindView(R.id.view_01)
    View view01;

    @BindView(R.id.login_progress)
    RelativeLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setBackgroundDrawableResource(R.drawable.background);
        setupEditTexts();
    }

    @Override
    public void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    public void onCreatePresenter() {
        loginPresenter.setView(this);
    }

    @Override
    public void initializeDagger() {
        getActivityComponent().inject(this);
    }

    @OnClick(R.id.login_button)
    public void onClickLoginButton(View view) {
        loginPresenter.onClickLogin(userName.getText().toString().toUpperCase(), userPass.getText().toString());
    }

    @OnClick(R.id.register_button)
    public void onClickRegisterButton(View view){
        loginPresenter.onClickRegister();
    }

    @Override
    public void enableLoginButton() {
        buttonLogin.setEnabled(true);
    }

    @Override
    public void disableLoginButton() {
        buttonLogin.setEnabled(false);
    }

    @Override
    public void navigateMainActivity(User user){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(USER_ARGS, user);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left_start, R.anim.right_to_left_end);
        finish();
    }

    @Override
    public void navigateRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left_start, R.anim.right_to_left_end);
    }

    @Override
    public void expandLogin() {
        view01.setVisibility(View.VISIBLE);
    }

    @Override
    public void collapseLogin() {
        view01.setVisibility(View.GONE);
    }

    @Override
    public void removeFocus() {
        userName.clearFocus();
        userPass.clearFocus();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }


    private void setupEditTexts() {

        userName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    loginPresenter.onGainFocus();
                }else{
                    loginPresenter.onLostFocus();
                }
            }
        });

        userPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    loginPresenter.onGainFocus();
                }else{
                    loginPresenter.onLostFocus();
                }
            }
        });
    }


}
