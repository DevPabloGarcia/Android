package pablogarcia.meetup.Modules.Fragments.DetailPlace;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.Model.Meet;
import pablogarcia.meetup.Model.MeetPlace;
import pablogarcia.meetup.Modules.Fragments.DetailImage.DetailImageView;
import pablogarcia.meetup.R;

public class DetailPlaceFragment extends Fragment implements DetailPlaceView, OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String MEET_ARGS = "MEET";
    private DetailPlacePresenter detailPlacePresenter;

    public static DetailPlaceFragment newInstance(Meet meet) {
        
        Bundle args = new Bundle();

        args.putParcelable(MEET_ARGS, meet);
        
        DetailPlaceFragment fragment = new DetailPlaceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.detailPlacePresenter = new DetailPlacePresenter(this, new DetailPlaceInteractor());
        return inflater.inflate(R.layout.fragment_map_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity()!=null) {

            SupportMapFragment mFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);

            if (mFragment != null) {
                mFragment.getMapAsync(this);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Meet meet = getArguments().getParcelable(MEET_ARGS);
        MeetPlace place = meet.getPlace();
        mMap = googleMap;
        this.detailPlacePresenter.setupMap(place, mMap);
    }

}
