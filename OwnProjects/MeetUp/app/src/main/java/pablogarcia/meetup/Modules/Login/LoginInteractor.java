package pablogarcia.meetup.Modules.Login;

import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class LoginInteractor {

    public interface OnLoginListener{
        void onLoginComplete();
    }

    public void checkUserLogged(OnLoginListener onLoginListener){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if(isLoggedIn){
            onLoginListener.onLoginComplete();
        }
    }

    public void handleFacebookAccessToken(LoginResult loginResult, final OnLoginListener onLoginListener) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        AccessToken token = loginResult.getAccessToken();

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    onLoginListener.onLoginComplete();
                }else{

                }
            }
        });
    }

}
