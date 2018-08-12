package com.example.cice.autocompletetextview;

/**
 * Created by CICE on 10/3/16.
 */
public class Ingredient {

    private String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {

        Ingredient ingredient = (Ingredient)o;

        if (ingredient.getName().equals(name))
            return true;
        return false;

    }

    /*
    String string1 = "1";
    String string2 = "1";

    if (string1 == string2) --> false;
    if (string1.equals(string2)) --> true;
    */
}


