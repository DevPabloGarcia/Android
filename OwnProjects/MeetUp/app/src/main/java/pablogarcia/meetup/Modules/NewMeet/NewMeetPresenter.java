package pablogarcia.meetup.Modules.NewMeet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;

import pablogarcia.meetup.Managers.DatabaseManager.OnDatabaseSaveListener;
import pablogarcia.meetup.Managers.StorageManager.OnStorageListener;
import pablogarcia.meetup.Modules.Fragments.DateTimePicker.OnDateTimePickerListener;
import pablogarcia.meetup.R;

public class NewMeetPresenter implements NewMeetInteractor.OnNewMeetListener, OnStorageListener, OnDatabaseSaveListener, OnDateTimePickerListener
{

    private NewMeetView newMeetView;
    private NewMeetInteractor newMeetInteractor;
    private OnDatabaseSaveListener onDatabaseListener;
    private int currentEditTextId;

    public NewMeetPresenter(NewMeetView newMeetView) {
        this.newMeetView = newMeetView;
        this.newMeetInteractor = new NewMeetInteractor();
        this.onDatabaseListener = this;
    }

    public void onOptionsItemSelected(MenuItem item){
        this.newMeetInteractor.onOptionsItemSelected(item, this);
    }

    public void uploadImage(Bitmap imageBitmap){
        this.newMeetInteractor.uploadImage(imageBitmap, this);
    }

    public void showDateTimePicker(FragmentManager fragmentManager, int currentEditTextId){
        this.currentEditTextId = currentEditTextId;
        this.newMeetInteractor.showDateTimePicker(fragmentManager, this);
    }

    public void openSelectImageSourceFragment(FragmentManager fragmentManager){
        this.newMeetInteractor.openSelectImageSourceFragment(fragmentManager);
    }

    public void openPlaceActivity(Activity activity){
        this.newMeetInteractor.openPlaceActivity(activity);
    }

    public void updatePlace(Place place){
        this.newMeetInteractor.updatePlace(place);
    }

    public void updateTitle(String title){
        this.newMeetInteractor.updateTitle(title);
    }

    public void updateDescription(String description){
        this.newMeetInteractor.updateDescription(description);
    }

    @Override
    public void navigateBack() {
        if(this.newMeetView!=null){
            this.newMeetView.navigateBack();
        }
    }

    @Override
    public void addMeet() {
        if(this.newMeetView!=null){
            this.newMeetView.uploadImage();
        }
    }

    @Override
    public void onUploadSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        try{
            taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    newMeetInteractor.updateImage(task.getResult().toString());
                    newMeetInteractor.saveNewMeet(onDatabaseListener);
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
        Log.e("Upload Image Error", e.getMessage());
    }

    @Override
    public void onSaveSuccess(Task<Void> task) {
        if(this.newMeetView != null){
            this.newMeetView.navigateBack();
        }
    }

    @Override
    public void onSaveFailure(Exception e) {
        if(this.newMeetView != null){
            this.newMeetView.showToastMessage(e.getMessage());
        }
    }

    @Override
    public void onDateTimeClose(String date, long dateMillis) {
        if(this.newMeetView != null){
            if(this.currentEditTextId == R.id.newMeetEditTextInitDate){
                this.newMeetView.updateTextInputInitDate(date);
                this.newMeetInteractor.updateInitDate(date);
                this.newMeetInteractor.updateInitDateMillis(dateMillis);
            }else if(this.currentEditTextId == R.id.newMeetEditTextEndDate){
                this.newMeetView.updateTextInputEndDate(date);
                this.newMeetInteractor.updateEndDate(date);
            }
        }
    }
}
