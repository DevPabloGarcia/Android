package com.example.cice.intentextraserializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cice on 24/11/15.
 */
public class CarParcelable implements Parcelable{
    private String marca,modelo;
    private int km;

    public CarParcelable() {
        this.marca = "Ford";
        this.modelo = "Focus";
        this.km = 0;
    }

    public CarParcelable(String marca, String modelo, int km) {
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
    }

    protected CarParcelable(Parcel in) {
        marca = in.readString();
        modelo = in.readString();
        km = in.readInt();
    }

    public static final Creator<CarParcelable> CREATOR = new Creator<CarParcelable>() {
        @Override
        public CarParcelable createFromParcel(Parcel in) {
            return new CarParcelable(in);
        }

        @Override
        public CarParcelable[] newArray(int size) {
            return new CarParcelable[size];
        }
    };

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getKm() {
        return km;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setKm(int km) {
        this.km = km;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(modelo);
        dest.writeInt(km);
    }
}
