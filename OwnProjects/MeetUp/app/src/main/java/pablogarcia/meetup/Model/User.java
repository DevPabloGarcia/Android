package pablogarcia.meetup.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.UUID;

public class User implements Parcelable {

    private String id;
    private String name;
    private String pass;
    private String email;
    private String image;

    public User() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.pass);
        dest.writeString(this.email);
        dest.writeString(this.image);
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.pass = in.readString();
        this.email = in.readString();
        this.image = in.readString();
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
