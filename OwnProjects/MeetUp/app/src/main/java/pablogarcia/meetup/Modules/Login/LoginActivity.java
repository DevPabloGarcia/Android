package pablogarcia.meetup.Modules.Login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.Modules.Main.MainActivity;
import pablogarcia.meetup.R;

public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.userName) EditText userName;
    @BindView(R.id.userPass) EditText userPass;
    @BindView(R.id.userNameInputLayout) TextInputLayout userNameLayout;
    @BindView(R.id.userPassInputLayout) TextInputLayout userPassLayout;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(new LoginInteractor(), this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClickButtonLogin(View view){
        loginPresenter.login(userName.getText().toString(), userPass.getText().toString());
    }

    @Override
    public void navigateMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    @Override
    public void showUserNameError() {
        userNameLayout.setErrorEnabled(true);
        userNameLayout.setError("Error");
    }

    @Override
    public void showUserPassError() {
        userPassLayout.setErrorEnabled(true);
        userPassLayout.setError("Error");
    }
}
