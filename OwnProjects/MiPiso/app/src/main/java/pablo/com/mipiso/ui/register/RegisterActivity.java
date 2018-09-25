package pablo.com.mipiso.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pablo.com.mipiso.R;
import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BaseActivity;
import pablo.com.mipiso.ui.main.MainActivity;

import static pablo.com.mipiso.utils.Consts.USER_ARGS;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @Inject
    RegisterPresenter registerPresenter;

    @BindView(R.id.user_name)
    EditText userName;

    @BindView(R.id.user_pass)
    EditText userPass;

    @BindView(R.id.user_pass_confirm)
    EditText userPassConfirm;

    @BindView(R.id.view_01)
    View view01;

    @BindView(R.id.register_progress)
    RelativeLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setBackgroundDrawableResource(R.drawable.background);
        setupEditTexts();
    }

    @Override
    public void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    public void onCreatePresenter() {
        registerPresenter.setView(this);
    }

    @Override
    public void initializeDagger() {
        getActivityComponent().inject(this);
    }

    @OnClick(R.id.register_button)
    public void onClickRegister(View view){
        registerPresenter.onClickRegister(userName.getText().toString().toUpperCase(), userPass.getText().toString(), userPassConfirm.getText().toString());
    }

    private void setupEditTexts() {

        userName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    registerPresenter.onGainFocus();
                }else{
                    registerPresenter.onLostFocus();
                }
            }
        });

        userPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    registerPresenter.onGainFocus();
                }else{
                    registerPresenter.onLostFocus();
                }
            }
        });

        userPassConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    registerPresenter.onGainFocus();
                }else{
                    registerPresenter.onLostFocus();
                }
            }
        });
    }

    @Override
    public void removeFocus() {
        userName.clearFocus();
        userPass.clearFocus();
        userPassConfirm.clearFocus();
    }

    @Override
    public void navigateMainActivity(User user) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(USER_ARGS, user);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left_start, R.anim.right_to_left_end);
    }

    @Override
    public void expandRegister() {
        view01.setVisibility(View.VISIBLE);
    }

    @Override
    public void collapseRegister() {
        view01.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }
}
