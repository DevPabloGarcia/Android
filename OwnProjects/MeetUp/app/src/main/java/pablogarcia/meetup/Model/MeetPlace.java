package pablogarcia.meetup.Model;

import android.os.Parcel;
import android.os.Parcelable;


public class MeetPlace implements Parcelable {

        private String id;
        private LatLng latLng;
        private String name;
        private String address;

    public MeetPlace() {}

    public MeetPlace(String id, LatLng latLng, String name, String address) {
            this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeParcelable(this.latLng, flags);
    }

    protected MeetPlace(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.address = in.readString();
        this.latLng = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Creator<MeetPlace> CREATOR = new Creator<MeetPlace>() {
        @Override
        public MeetPlace createFromParcel(Parcel in) {
            return new MeetPlace(in);
        }

        @Override
        public MeetPlace[] newArray(int size) {
            return new MeetPlace[size];
        }
    };

}
