package pablogarcia.meetup.Managers.SessionManager;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pablogarcia.meetup.Model.User;

public class SessionManager {

    private User user;
    private static volatile SessionManager instance;

    public static SessionManager newInstance() {
        if(instance == null){
            instance = new SessionManager();
        }
        return instance;
    }

    public User getCurrentUser(){
        return this.user;
    }

    public void updateCurrentUser(FirebaseUser firebaseUser){
        this.user = new User();
        this.user.setId(firebaseUser.getUid());
        this.user.setName(firebaseUser.getDisplayName());
        this.user.setEmail(firebaseUser.getEmail());
        this.user.setImage(firebaseUser.getPhotoUrl().toString());
    }

    public void logOut(OnSessionListener listener){
        this.user = null;
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        listener.onLogOutFinished();
    }
}
