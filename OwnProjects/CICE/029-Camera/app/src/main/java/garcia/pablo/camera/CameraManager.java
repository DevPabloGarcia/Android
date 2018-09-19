package garcia.pablo.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by V on 2/2/16.
 */
public class CameraManager {

    private static final int TYPE_PICTURE = 1;
    private static final int TYPE_VIDEO = 2;

    Uri uri;
    File mediafile;
    Activity activity;

    public CameraManager(Activity activity) {
        this.activity = activity;
    }

    public void takePicture(int requestCode) throws CameraException {

        uri = createUri(TYPE_PICTURE);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(takePictureIntent, requestCode);

    }

    public void takeVideo(int requestCode) throws CameraException {

        uri = createUri(TYPE_VIDEO);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(takePictureIntent, requestCode);

    }

    private Uri createUri(int type) throws CameraException {

        File fileDirectory;

        if (type==TYPE_PICTURE)
            fileDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"MyCamera");
        else
            fileDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),"MyCamera");

        // Creamos el directorio si no existe.
        if (!fileDirectory.exists()){
            if(!fileDirectory.mkdirs()){
                throw new CameraException("No se pudo crear el directorio");
            }
        }

        // Creamos el archivo de la foto
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        if(type == TYPE_PICTURE){
            mediafile = new File(fileDirectory.getAbsolutePath(),"IMG_"+timeStamp+".jpg");
        }else{
            mediafile = new File(fileDirectory.getAbsolutePath(),"VID_"+timeStamp+".mp4");
        }

        return Uri.fromFile(mediafile);
    }

    public Uri getUriOfLastAction() {
        new MediaScanner(activity,mediafile);
        return uri;
    }

    public File getFileOfLastAction() {
        return mediafile;
    }

}
