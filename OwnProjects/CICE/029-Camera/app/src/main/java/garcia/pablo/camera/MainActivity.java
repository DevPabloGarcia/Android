package garcia.pablo.camera;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_PICTURE = 1;
    private static final int REQ_VIDEO = 2;
    private static final int REQ_GALLERY = 3;


    ImageView imageView;
    CameraManager cameraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image_view);
        cameraManager = new CameraManager(this);
    }

    public void onPictureClick(View view) {
        try {
            cameraManager.takePicture(REQ_PICTURE);
        } catch (CameraException e) {
            e.printStackTrace();
        }
    }


    public void onVideoClick(View view) {
        try {
            cameraManager.takeVideo(REQ_VIDEO);
        } catch (CameraException e) {
            e.printStackTrace();
        }
    }

    public void onGalleryClick(View view) {

        Intent intentGallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentGallery.setType("image/*");
        startActivityForResult(intentGallery, REQ_GALLERY);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode == REQ_PICTURE){
                imageView.setImageURI(cameraManager.getUriOfLastAction());
            }
            else if(requestCode == REQ_GALLERY){
                imageView.setImageURI(data.getData());
            }
        }
    }


}
