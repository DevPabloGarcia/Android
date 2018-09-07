package pablogarcia.meetup.Managers.FacebookManager;

import com.google.firebase.auth.FirebaseUser;

public class FacebookManager {

    public FacebookManager() {
    }

    public String getFacebookImage(FirebaseUser user){

        String finalString;
        String photoUrl = user.getPhotoUrl().toString();
        finalString = photoUrl + FacebookConsts.FACEBOOK_IMAGE_PATH_END;

        return finalString;
    }

}
