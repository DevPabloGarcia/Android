package garcia.pablo.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by V on 20/1/16.
 */

public class SessionManager {

    private static final String PREF_NAME = "session";
    private static final String KEY_NAME = "user_name";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    
    Context context;
    SharedPreferences preferences;

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }

    public void createSession(User user){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NAME,user.getName());
        editor.putBoolean(KEY_IS_LOGGED_IN,true);

        editor.apply();
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(KEY_IS_LOGGED_IN,false);
    }

    public void logOut(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        checkSession();

    }

    public void checkSession(){
        if(!isLoggedIn()){
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }

    public User getUserLogged(){

        User user = new User();
        user.setName(preferences.getString(KEY_NAME,""));
        return user;
    }

}