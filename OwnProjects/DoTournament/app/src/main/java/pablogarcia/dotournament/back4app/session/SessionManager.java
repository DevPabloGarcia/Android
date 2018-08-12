package pablogarcia.dotournament.back4app.session;

import android.content.Context;
import android.content.Intent;

import com.parse.ParseUser;

import pablogarcia.dotournament.activity.LoginActivity;

/**
 * Created by V on 14/4/16.
 */
public class SessionManager {

    Context context;

    public SessionManager(Context context) {
        this.context = context;
    }

    /**
     * Check if the user is logged in.
     * @return true if the user is logged in, false in other case.
     */
    public boolean isLoggedIn(){
        return ParseUser.getCurrentUser() != null;
    }

    /**
     * Log out of the app, and return to the login activity
     */
    public void logOut(){
        ParseUser.logOut();
        this.checkSession();
    }

    /**
     * Check if exists a user logged and return to the login activity in the false case
     */
    public void checkSession(){
        if(!isLoggedIn()){
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
