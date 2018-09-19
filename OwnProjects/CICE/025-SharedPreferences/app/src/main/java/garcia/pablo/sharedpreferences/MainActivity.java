package garcia.pablo.sharedpreferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        sessionManager.checkSession();
        user = sessionManager.getUserLogged();
    }

    public void onLogOutClick(View view) {
        sessionManager.logOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void onoSettingsClick(View view) {

        startActivity(new Intent(this, SettingsActivity.class));

    }

    @Override
    protected void onResume() {
        super.onResume();
        sessionManager.checkSession();
    }
}
