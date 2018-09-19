package pablo.com.mipiso.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pablo.com.mipiso.R;
import pablo.com.mipiso.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginView{

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.login_button)
    Button buttonLogin;

    @BindView(R.id.user_name)
    TextView userName;

    @BindView(R.id.user_pass)
    TextView userPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

    @Override
    public void enableLoginButton() {
        buttonLogin.setEnabled(true);
    }

    @Override
    public void disableLoginButton() {
        buttonLogin.setEnabled(false);
    }
}
