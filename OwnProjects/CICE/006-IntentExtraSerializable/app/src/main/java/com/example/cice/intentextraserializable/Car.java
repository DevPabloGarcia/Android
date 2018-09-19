package com.example.cice.intentextraserializable;

import java.io.Serializable;

/**
 * Created by cice on 24/11/15.
 */
public class Car implements Serializable {

    private String marca,modelo;
    private int km;

    public Car() {
        this.marca = "Ford";
        this.modelo = "Focus";
        this.km = 0;
    }

    public Car(String marca, String modelo, int km) {
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
    }

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
}
