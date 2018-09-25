package pablo.com.mipiso.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Flat implements Parcelable {

    private String id;
    private String name;
    private String image;
    private LatLng latLng;
    private ArrayList<User> tenants;
    private ArrayList<Room> rooms;
    private ArrayList<Room> commonRooms;
    private ArrayList<Task> tasks;
    private int renting;
    private String wifiSSID;
    private String wifiPass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public ArrayList<User> getTenants() {
        return tenants;
    }

    public void setTenants(ArrayList<User> tenants) {
        this.tenants = tenants;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getCommonRooms() {
        return commonRooms;
    }

    public void setCommonRooms(ArrayList<Room> commonRooms) {
        this.commonRooms = commonRooms;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getRenting() {
        return renting;
    }

    public void setRenting(int renting) {
        this.renting = renting;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWifiSSID() {
        return wifiSSID;
    }

    public void setWifiSSID(String wifiSSID) {
        this.wifiSSID = wifiSSID;
    }

    public String getWifiPass() {
        return wifiPass;
    }

    public void setWifiPass(String wifiPass) {
        this.wifiPass = wifiPass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.image);
        dest.writeParcelable(this.latLng, flags);
        dest.writeTypedList(this.tenants);
        dest.writeTypedList(this.rooms);
        dest.writeTypedList(this.commonRooms);
        dest.writeTypedList(this.tasks);
        dest.writeInt(this.renting);
        dest.writeString(this.wifiSSID);
        dest.writeString(this.wifiPass);
    }

    protected Flat(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.image = in.readString();
        this.latLng = in.readParcelable(LatLng.class.getClassLoader());
        this.tenants = in.createTypedArrayList(User.CREATOR);
        this.rooms = in.createTypedArrayList(Room.CREATOR);
        this.commonRooms = in.createTypedArrayList(Room.CREATOR);
        this.tasks = in.createTypedArrayList(Task.CREATOR);
        this.renting = in.readInt();
        this.wifiSSID = in.readString();
        this.wifiPass = in.readString();
    }

    public static final Creator<Flat> CREATOR = new Creator<Flat>() {
        @Override
        public Flat createFromParcel(Parcel in) {
            return new Flat(in);
        }

        @Override
        public Flat[] newArray(int size) {
            return new Flat[size];
        }
    };
}
