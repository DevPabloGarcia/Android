package pablogarcia.meetup.Modules.Fragments.DetailPlace;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pablogarcia.meetup.Model.MeetPlace;

public class DetailPlaceInteractor {

    public void setupMap(MeetPlace place, GoogleMap map){

        try{
            // Add a marker in Sydney, Australia, and move the camera.
            LatLng latLng = new LatLng(place.getLatLng().getLatitude(), place.getLatLng().getLongitude());
            map.addMarker(new MarkerOptions().position(latLng).title(place.getName()+ " - " +place.getAddress()));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));

        }catch(Exception e){
            Log.e("MAP ERROR!", e.getMessage());
        }

    }

}
