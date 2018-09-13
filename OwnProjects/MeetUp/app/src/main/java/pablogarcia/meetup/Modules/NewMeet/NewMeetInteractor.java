package pablogarcia.meetup.Modules.NewMeet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import pablogarcia.meetup.ApiManager;
import pablogarcia.meetup.Managers.DatabaseManager.OnDatabaseSaveListener;
import pablogarcia.meetup.Managers.StorageManager.OnStorageListener;
import pablogarcia.meetup.Model.LatLng;
import pablogarcia.meetup.Model.Meet;
import pablogarcia.meetup.Model.MeetPlace;
import pablogarcia.meetup.Modules.Fragments.DateTimePicker.DateTimePicker;
import pablogarcia.meetup.Modules.Fragments.DateTimePicker.OnDateTimePickerListener;
import pablogarcia.meetup.Modules.Fragments.PickImage.PickImageFragment;
import pablogarcia.meetup.R;

import static pablogarcia.meetup.Utils.Consts.REQ_PLACE;

public class NewMeetInteractor{

    private Meet meet;

    public NewMeetInteractor() {
        this.meet = new Meet();
        this.meet.setOwner(ApiManager.getInstance().getCurrentUser());
    }

    public interface OnNewMeetListener{
        void navigateBack();
        void addMeet();
    }

    public void onOptionsItemSelected(MenuItem item , OnNewMeetListener listener){
        switch (item.getItemId()){
            case android.R.id.home:
                listener.navigateBack();
                break;
            case R.id.add_meet:
                listener.addMeet();
        }
    }

    public void uploadImage(Bitmap imageBitmap, OnStorageListener listener){
        ApiManager.getInstance().uploadMeetImage(imageBitmap, listener);
    }

    public void saveNewMeet(OnDatabaseSaveListener listener){
        ApiManager.getInstance().putMeet(meet, listener);
    }

    public void updateTitle(String title){
        this.meet.setTitle(title);
    }

    public void updateDescription(String description){
        this.meet.setDescription(description);
    }

    public void updateImage(String image){
        this.meet.setImage(image);
    }

    public void updatePlace(Place place){
        LatLng latLng = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
        MeetPlace meetPlace = new MeetPlace(place.getId(), latLng, place.getName().toString(), place.getAddress().toString());
        this.meet.setPlace(meetPlace);
    }

    public void updateInitDate(String date){
        this.meet.setInitDate(date);
    }

    public void updateInitDateMillis(long dateMillis){
        this.meet.setInitDateMillis(dateMillis);
    }

    public void updateEndDate(String date){
        this.meet.setEndDate(date);
    }

    public void showDateTimePicker(FragmentManager fragmentManager, OnDateTimePickerListener listener){
        new DateTimePicker(fragmentManager, listener);
    }

    public void openSelectImageSourceFragment(FragmentManager fragmentManager){
        PickImageFragment selectImageDialog = PickImageFragment.newInstance();
        selectImageDialog.show(fragmentManager, "");
    }

    public void openPlaceActivity(Activity activity){
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try{
            activity.startActivityForResult(builder.build(activity), REQ_PLACE);
        }catch (Exception e){
            Log.e("ERROR!", e.getMessage());
        }
    }


}
