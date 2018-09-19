package com.example.cice.openweathermapjson.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CICE on 9/2/16.
 */
public class Forecast implements Parcelable {

    String dateTime;
    String dayTemp, minTemp, maxTemp;
    String pressure;
    String text, description;
    String speed;

    public Forecast() {
    }

    public Forecast(String dateTime, String dayTemp, String minTemp, String maxTemp, String pressure, String text, String description, String speed) {
        this.dateTime = dateTime;
        this.dayTemp = dayTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.pressure = pressure;
        this.text = text;
        this.description = description;
        this.speed = speed;
    }

    protected Forecast(Parcel in) {
        dateTime = in.readString();
        dayTemp = in.readString();
        minTemp = in.readString();
        maxTemp = in.readString();
        pressure = in.readString();
        text = in.readString();
        description = in.readString();
        speed = in.readString();
    }

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel in) {
            return new Forecast(in);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };

    public String getDateTime() {
        return dateTime;
    }

    public String getDayTemp() {
        return dayTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getText() {
        return text;
    }

    public String getDescription() {
        return description;
    }

    public String getSpeed() {
        return speed;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDayTemp(String dayTemp) {
        this.dayTemp = dayTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dateTime);
        dest.writeString(dayTemp);
        dest.writeString(minTemp);
        dest.writeString(maxTemp);
        dest.writeString(pressure);
        dest.writeString(text);
        dest.writeString(description);
        dest.writeString(speed);
    }
}
