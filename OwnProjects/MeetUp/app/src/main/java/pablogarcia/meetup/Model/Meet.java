package pablogarcia.meetup.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.UUID;

public class Meet implements Parcelable{

    private String id;
    private String title;
    private String description;
    private String image;
    private String initDate;
    private String endDate;
    private MeetPlace place;
    private long initDateMillis;
    private User owner;
    private ArrayList<User> participants;

    public Meet() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public Meet(String title, String description, String image, String initDate, String endDate) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        this.title = title;
        this.description = description;
        this.image = image;
        this.initDate = initDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public MeetPlace getPlace() {
        return place;
    }

    public void setPlace(MeetPlace place) {
        this.place = place;
    }

    public long getInitDateMillis() {
        return initDateMillis;
    }

    public void setInitDateMillis(long initDateMillis) {
        this.initDateMillis = initDateMillis;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }

    public void addParticipant(User user){
        this.participants.add(user);
    }

    public void removeParticipant(User user){
        int i = 0;
        boolean removed = false;
        while(i < this.participants.size() && !removed){
            if(this.participants.get(i).getId().equals(user.getId())){
                removed = true;
                this.participants.remove(i);
            }
            i++;
        }
    }

    public boolean isParticipant(String id){
        boolean isInMeet = false;
        int i = 0;
        while(i < this.participants.size() && !isInMeet){
            if(this.participants.get(i).getId().equals(id)){
                isInMeet = true;
            }
            i++;
        }
        return isInMeet;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.image);
        dest.writeString(this.initDate);
        dest.writeString(this.endDate);
        dest.writeParcelable(this.place, flags);
        dest.writeLong(this.initDateMillis);
        dest.writeParcelable(this.owner, flags);
        dest.writeTypedList(this.participants);

    }

    protected Meet(Parcel in){
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        this.initDate = in.readString();
        this.endDate = in.readString();
        this.place = in.readParcelable(MeetPlace.class.getClassLoader());
        this.initDateMillis = in.readLong();
        this.owner = in.readParcelable(User.class.getClassLoader());
        this.participants = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<Meet> CREATOR = new Creator<Meet>() {
        @Override
        public Meet createFromParcel(Parcel in) {
            return new Meet(in);
        }

        @Override
        public Meet[] newArray(int size) {
            return new Meet[size];
        }
    };

}
