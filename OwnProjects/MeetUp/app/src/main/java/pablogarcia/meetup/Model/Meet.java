package pablogarcia.meetup.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Meet implements Parcelable{

    private String title;
    private String description;
    private String image;
    private String initDate;
    private String endDate;

    public Meet(String title, String description, String image, String initDate, String endDate) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.initDate = initDate;
        this.endDate = endDate;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(initDate);
        dest.writeString(endDate);

    }

    protected Meet(Parcel in){
        this.title = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        this.initDate = in.readString();
        this.endDate = in.readString();
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
