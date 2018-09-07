package pablogarcia.meetup.Modules.Fragments.DetailPlace;

import com.google.android.gms.maps.GoogleMap;

import pablogarcia.meetup.Model.MeetPlace;

public class DetailPlacePresenter {

    private DetailPlaceView detailPlaceView;
    private DetailPlaceInteractor detailPlaceInteractor;

    public DetailPlacePresenter(DetailPlaceView detailPlaceView, DetailPlaceInteractor detailPlaceInteractor) {
        this.detailPlaceView = detailPlaceView;
        this.detailPlaceInteractor = detailPlaceInteractor;
    }
    public void setupMap(MeetPlace place, GoogleMap map){
        this.detailPlaceInteractor.setupMap(place, map);
    }
}
