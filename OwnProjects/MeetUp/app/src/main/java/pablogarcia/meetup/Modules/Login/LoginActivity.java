package pablogarcia.meetup.Modules.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.Managers.AuthManager.AuthManager;
import pablogarcia.meetup.Modules.Main.MainActivity;
import pablogarcia.meetup.Modules.Register.RegisterActivity;
import pablogarcia.meetup.R;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private LoginPresenter loginPresenter;
    private CallbackManager callbackManager;

    @BindView(R.id.facebook_login_button) LoginButton facebookLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bind the xml views
        ButterKnife.bind(this);

        // Create an instance for login presenter
        this.loginPresenter = new LoginPresenter(this);

        // Setup the facebook login button
        this.setupFacebookLoginButton();

        // Check if the user is logged
        this.loginPresenter.checkUserLogged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Navigate to the main activity
     */
    @Override
    public void navigateMainActivity() {
        // Create a new intent instance
        Intent intent = new Intent(this, MainActivity.class);
        // Navigate to the main activity
        startActivity(intent);
        // Show a translate animation
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * Navigate to the main activity
     */
    @Override
    public void navigateRegisterActivity() {
        // Create a new intent instance
        Intent intent = new Intent(this, RegisterActivity.class);
        // Navigate to the main activity
        startActivity(intent);
        // Show a translate animation
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * Show a Toast message
     * @param message
     */
    @Override
    public void showToastMessage(String message){
        // Check if there is message to show
        if(!message.equals("")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Setup the facebook login
     */
    @Override
    public void setupFacebookLoginButton(){
        this.callbackManager = CallbackManager.Factory.create();
        this.facebookLoginButton.setReadPermissions("email", "public_profile");
        this.facebookLoginButton.registerCallback(this.callbackManager, new AuthManager(this.loginPresenter));
    }

    /**
     * Get the callback from facebook webview.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Called when the user clicks on the login button
     * @param view
     */
    public void onClickButtonLogin(View view){
        // Navigate to the next view
        this.navigateMainActivity();
    }

    public void onClickButtonSingUp(View view){
        this.navigateRegisterActivity();
    }

}
