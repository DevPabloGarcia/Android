package pablogarcia.meetup.Managers.StorageManager;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StorageManager {

    FirebaseStorage storage;

    public StorageManager() {
        this.storage = FirebaseStorage.getInstance();
    }

    public StorageReference createMeetImageReference(String imageName){
        // Create a storage reference from the app
        StorageReference storageRef = storage.getReference();

        // Create a reference to "mountains.jpg"
        StorageReference meetImagesRef = storageRef.child("meet_images/"+imageName);

        return meetImagesRef;
    }

    public void uploadMeetImage(Bitmap bitmap, final OnStorageListener listener){

        String imageName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        StorageReference storageReference = this.createMeetImageReference(imageName+".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = storageReference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                listener.onUploadFailure(exception);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                listener.onUploadSuccess(taskSnapshot);
            }
        });
    }

}
