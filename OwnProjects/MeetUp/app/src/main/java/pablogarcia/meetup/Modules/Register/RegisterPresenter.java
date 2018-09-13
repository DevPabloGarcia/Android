package pablogarcia.meetup.Modules.Register;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;

import pablogarcia.meetup.Managers.DatabaseManager.OnDatabaseSaveListener;
import pablogarcia.meetup.Managers.StorageManager.OnStorageListener;

public class RegisterPresenter implements RegisterInteractor.OnRegisterListener, OnStorageListener, OnDatabaseSaveListener{

    private RegisterView registerView;
    private RegisterInteractor registerInteractor;
    private OnDatabaseSaveListener onDatabaseListener;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
        this.registerInteractor = new RegisterInteractor();
        this.onDatabaseListener = this;
    }

    public void updateUserName(String name){
        this.registerInteractor.updateUserName(name);
    }

    public void updateUserPass(String pass){
        this.registerInteractor.updateUserPass(pass);
    }

    public void updateUserEmail(String email){
        this.registerInteractor.updateUserEmail(email);
    }

    public void updateUserImage(String image){
        this.registerInteractor.updateUserImage(image);
    }

    public void uploadImage(Bitmap imageBitmap){
        this.registerInteractor.uploadImage(imageBitmap, this);
    }

    public void openSelectImageSourceFragment(FragmentManager fragmentManager){
        this.registerInteractor.openSelectImageSourceFragment(fragmentManager);
    }

    public void onOptionsItemSelected(MenuItem item){
        this.registerInteractor.onOptionsItemSelected(item, this);
    }

    @Override
    public void navigateBack() {
        if(this.registerView != null){
            this.registerView.navigateBack();
        }
    }

    @Override
    public void onUploadSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        try{
            taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    registerInteractor.updateUserImage(task.getResult().toString());
                    registerInteractor.saveNewUser(onDatabaseListener);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("Error saving Image url" , e.getMessage());
                }
            });

        }catch(Exception e){
            Log.e("Error saving Image url" , e.getMessage());
        }
    }

    @Override
    public void onUploadFailure(Exception e) {
        Log.e("IMAGE UPLOAD FAIL", e.getMessage());
    }

    @Override
    public void onSaveSuccess(Task<Void> task) {
        if(this.registerView != null){
            this.registerView.navigateBack();
        }
    }

    @Override
    public void onSaveFailure(Exception e) {
        Log.e("DATABASE ERROR", e.getMessage());
    }
}
