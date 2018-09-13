package pablogarcia.meetup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseUser;

import java.io.File;

import pablogarcia.meetup.Managers.CameraManager.CameraManager;
import pablogarcia.meetup.Managers.DatabaseManager.DatabaseManager;
import pablogarcia.meetup.Managers.DatabaseManager.OnDatabaseGetListener;
import pablogarcia.meetup.Managers.DatabaseManager.OnDatabaseSaveListener;
import pablogarcia.meetup.Managers.FacebookManager.FacebookManager;
import pablogarcia.meetup.Managers.ImagesManager.ImageManager;
import pablogarcia.meetup.Managers.SessionManager.OnSessionListener;
import pablogarcia.meetup.Managers.SessionManager.SessionManager;
import pablogarcia.meetup.Managers.StorageManager.OnStorageListener;
import pablogarcia.meetup.Managers.StorageManager.StorageManager;
import pablogarcia.meetup.Model.Meet;
import pablogarcia.meetup.Model.User;

public class ApiManager {

    private static volatile ApiManager sApiManager = new ApiManager();

    private CameraManager cameraManager;
    private FacebookManager facebookManager;
    private ImageManager imageManager;
    private StorageManager storageManager;
    private DatabaseManager databaseManager;
    private SessionManager sessionManager;

    private ApiManager() {
        this.storageManager = new StorageManager();
        this.facebookManager = new FacebookManager();
        this.imageManager = new ImageManager();
        this.cameraManager = new CameraManager();
        this.databaseManager = new DatabaseManager();
        this.sessionManager = SessionManager.newInstance();
    }

    public static ApiManager getInstance(){
        if(sApiManager == null){
            sApiManager = new ApiManager();
        }
        return sApiManager;
    }

    public String getFacebookImage(FirebaseUser user){
        return this.facebookManager.getFacebookImage(user);
    }

    public void loadImageIntoImageView(ImageView imageView, Uri uri){
        this.imageManager.loadImageIntoImageView(imageView, uri);
    }

    public void loadImageIntoImageView(ImageView imageView, String string){
        this.imageManager.loadImageIntoImageView(imageView, string);
    }

    public void loadImageIntoImageView(ImageView imageView, File file){
        this.imageManager.loadImageIntoImageView(imageView, file);
    }

    public void loadImageIntoImageView(ImageView imageView, int resourceId){
        this.imageManager.loadImageIntoImageView(imageView, resourceId);

    }

    public void showCamera(Context context, Activity activity){
        this.cameraManager.showCamera(context, activity);
    }

    public void openCamera(Activity activity){
        this.cameraManager.openCamera(activity);
    }

    public Uri getCameraImagePath(Activity activity){
        return this.cameraManager.getUriOfLastAction(activity);
    }

    public void uploadMeetImage(Bitmap imageBitmap, OnStorageListener listener){
        this.storageManager.uploadMeetImage(imageBitmap, listener);
    }

    public void addUser(User user, OnDatabaseSaveListener listener){
        this.databaseManager.addUser(user, listener);
    }

    public void putMeet(Meet meet, OnDatabaseSaveListener listener){
        this.databaseManager.putMeet(meet, listener);
    }

    public void getCurrentMeets(OnDatabaseGetListener listener){
        this.databaseManager.getCurrentMeets(listener);
    }

    public void getFinishedMeets(OnDatabaseGetListener listener){
        this.databaseManager.getFinishedMeets(listener);
    }

    public void updateCurrentUser(FirebaseUser user){
        this.sessionManager.updateCurrentUser(user);
    }

    public User getCurrentUser(){
        return this.sessionManager.getCurrentUser();
    }

    public void logOut(OnSessionListener listener){
        this.sessionManager.logOut(listener);
    }

}
