package pablogarcia.meetup.Managers.CameraManager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pablogarcia.meetup.R;

import static pablogarcia.meetup.Utils.Consts.REQ_CAMERA;

public class CameraManager {

    private Uri photoURI;
    private File mediafile;

    /**
     *
     * @param activity
     * @return
     */
    public Uri getUriOfLastAction(Activity activity) {
        new MediaScanner(activity, mediafile);
        return this.photoURI;
    }

    /**
     *
     * @param context
     * @param activity
     */
    public void showCamera(Context context, Activity activity){
        if(this.checkPermissions(context)){
            this.openCamera(activity);
        }else{
            this.requestPermissions(activity);
        }
    }

    /**
     *
     * @param activity
     */
    public void openCamera(Activity activity){
        try {
            this.photoURI = this.createUri(activity);

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            activity.startActivityForResult(cameraIntent, REQ_CAMERA);

        } catch (Exception e){
            Log.e("Camera Error", e.getMessage());
            Toast.makeText(activity, R.string.openCameraError, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     *
     * @param activity
     * @return
     * @throws CameraException
     */
    private Uri createUri(Activity activity) throws CameraException{

        File fileDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"MyCamera");

        if (!fileDirectory.exists()){
            if(!fileDirectory.mkdirs()){
                throw new CameraException(activity.getString(R.string.createCameraDirError));
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());

        mediafile = new File(fileDirectory.getAbsolutePath(),"IMG_"+timeStamp+".jpg");
        return FileProvider.getUriForFile(activity,"pablogarcia.meetup.provider", mediafile);
    }

    /**
     *
     * @param context
     * @return
     */
    private boolean checkPermissions(Context context){

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }else{
            return true;
        }

    }

    /**
     *
     * @param activity
     */
    private void requestPermissions(Activity activity){
        ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQ_CAMERA);
    }

}
