package pablogarcia.meetup.Managers.StorageManager;

import com.google.firebase.storage.UploadTask;

public interface OnStorageListener {
    void onUploadSuccess(UploadTask.TaskSnapshot taskSnapshot);
    void onUploadFailure(Exception e);
}
