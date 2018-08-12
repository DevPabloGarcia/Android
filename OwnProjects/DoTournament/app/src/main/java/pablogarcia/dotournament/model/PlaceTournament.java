package pablogarcia.dotournament.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by V on 2/7/16.
 */
public class PlaceTournament implements Parcelable {

    private String id;
    private LatLng latLng;
    private String name;
    private String address;

    public PlaceTournament() {
    }

    public PlaceTournament(LatLng latLng, String name, String address) {
        this.latLng = latLng;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    protected PlaceTournament(Parcel in) {
        latLng = in.readParcelable(LatLng.class.getClassLoader());
        name = in.readString();
        address = in.readString();
    }

    public static final Creator<PlaceTournament> CREATOR = new Creator<PlaceTournament>() {
        @Override
        public PlaceTournament createFromParcel(Parcel in) {
            return new PlaceTournament(in);
        }

        @Override
        public PlaceTournament[] newArray(int size) {
            return new PlaceTournament[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(latLng, flags);
        dest.writeString(name);
        dest.writeString(address);
    }
}
