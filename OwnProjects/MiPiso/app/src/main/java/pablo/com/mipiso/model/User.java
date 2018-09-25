package pablo.com.mipiso.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import pablo.com.mipiso.utils.Utils;

public class User implements Parcelable{

    private String id;
    private String name;
    private String surname;
    private String image;
    private String phone;
    private Task currentTask;
    private Room room;
    private ArrayList<Paid> paids;
    private int nextPaid;
    private Flat flat;

    public User() {
        this.id = Utils.generateUUID();
    }

    public User(String id, String name, String surname, String image, String phone, Task currentTask, Room room, ArrayList<Paid> paids, int nextPaid, Flat flat) {
        this.id = Utils.generateUUID();
        this.name = name;
        this.surname = surname;
        this.image = image;
        this.phone = phone;
        this.currentTask = currentTask;
        this.room = room;
        this.paids = paids;
        this.nextPaid = nextPaid;
        this.flat = flat;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ArrayList<Paid> getPaids() {
        return paids;
    }

    public void setPaids(ArrayList<Paid> paids) {
        this.paids = paids;
    }

    public int getNextPaid() {
        return nextPaid;
    }

    public void setNextPaid(int nextPaid) {
        this.nextPaid = nextPaid;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.surname);
        dest.writeString(this.image);
        dest.writeString(this.phone);
        dest.writeParcelable(this.currentTask, flags);
        dest.writeParcelable(this.room, flags);
        dest.writeTypedList(this.paids);
        dest.writeInt(this.nextPaid);
        dest.writeParcelable(this.flat, flags);
    }

    protected User(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.surname = in.readString();
        this.image = in.readString();
        this.phone = in.readString();
        this.currentTask = in.readParcelable(Task.class.getClassLoader());
        this.room = in.readParcelable(Room.class.getClassLoader());
        this.paids = in.createTypedArrayList(Paid.CREATOR);
        this.nextPaid = in.readInt();
        this.flat = in.readParcelable(Flat.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
