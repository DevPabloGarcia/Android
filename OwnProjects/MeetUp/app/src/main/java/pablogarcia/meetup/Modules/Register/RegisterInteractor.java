package pablogarcia.meetup.Modules.Register;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import pablogarcia.meetup.ApiManager;
import pablogarcia.meetup.Managers.DatabaseManager.OnDatabaseSaveListener;
import pablogarcia.meetup.Managers.StorageManager.OnStorageListener;
import pablogarcia.meetup.Model.User;
import pablogarcia.meetup.Modules.Fragments.PickImage.PickImageFragment;
import pablogarcia.meetup.Modules.NewMeet.NewMeetInteractor;
import pablogarcia.meetup.R;

public class RegisterInteractor {

    private User user;

    public interface OnRegisterListener{
        void navigateBack();
    }

    public RegisterInteractor() {
        this.user = new User();
    }

    public void updateUserName(String name){
        this.user.setName(name);
    }

    public void updateUserPass(String pass){
        this.user.setPass(pass);
    }

    public void updateUserEmail(String email){
        this.user.setEmail(email);
    }

    public void updateUserImage(String image){
        this.user.setImage(image);
    }

    public void openSelectImageSourceFragment(FragmentManager fragmentManager){
        PickImageFragment selectImageDialog = PickImageFragment.newInstance();
        selectImageDialog.show(fragmentManager, "");
    }

    public void onOptionsItemSelected(MenuItem item , OnRegisterListener listener){
        switch (item.getItemId()){
            case android.R.id.home:
                listener.navigateBack();
                break;
        }
    }

    public void uploadImage(Bitmap imageBitmap, OnStorageListener listener){
        ApiManager.getInstance().uploadMeetImage(imageBitmap, listener);
    }

    public void saveNewUser(OnDatabaseSaveListener listener){
        ApiManager.getInstance().addUser(user, listener);
    }

}
