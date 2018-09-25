package pablo.com.mipiso.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Paid implements Parcelable{

    public String id;
    public Double amount;
    public String items;
    public String date;

    public Paid(String id, Double amount, String items, String date) {
        this.id = id;
        this.amount = amount;
        this.items = items;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeDouble(this.amount);
        dest.writeString(this.items);
        dest.writeString(this.date);
    }

    protected Paid(Parcel in){
        this.id = in.readString();
        this.amount = in.readDouble();
        this.items = in.readString();
        this.date = in.readString();
    }

    public static final Creator<Paid> CREATOR = new Creator<Paid>() {
        @Override
        public Paid createFromParcel(Parcel in) {
            return new Paid(in);
        }

        @Override
        public Paid[] newArray(int size) {
            return new Paid[size];
        }
    };
}
