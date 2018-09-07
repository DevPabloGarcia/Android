package pablogarcia.meetup.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String id;
    private String name;
    private String surname;
    private String email;
    private String image;

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image.replace("https","http");
    }

    public void setImage(String image) {
        this.image = image.replace("https","http");
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(email);
        dest.writeString(image);
    }

    protected User(Parcel in) {
        id = in.readString();
        name = in.readString();
        surname = in.readString();
        email = in.readString();
        image = in.readString();
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
