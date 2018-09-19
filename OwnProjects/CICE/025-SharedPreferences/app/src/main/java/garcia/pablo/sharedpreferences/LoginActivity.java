package garcia.pablo.sharedpreferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText editTextName, editTextPass;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Comprobamos si esta ya loggeado.

        sessionManager = new SessionManager(this);
        if(sessionManager.isLoggedIn()){
            startActivity(new Intent(this,MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        editTextName = (EditText) findViewById(R.id.name);
        editTextPass = (EditText) findViewById(R.id.pass);
    }

    public void onButtonClickLogIn(View view) {

        String name = editTextName.getText().toString().trim();
        String pass = editTextPass.getText().toString().trim();

        // Comprobamos que estan llenos.
        if(!name.isEmpty() && !pass.isEmpty()){
            // Creo sesion.
            User user = new User(name);
            sessionManager.createSession(user);
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
    }
}










