package com.example.cice.autocompletetextview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CICE on 10/3/16.
 */
public class AutoCompleteIngredientsAdapter extends ArrayAdapter<Ingredient> {

    private ArrayList<Ingredient> ingredients;
    private List<Ingredient> filteredIngredients;

    public AutoCompleteIngredientsAdapter (Context context, ArrayList<Ingredient> ingredients) {
        super(context, 0, ingredients);
        this.ingredients = ingredients;
        this.filteredIngredients = new ArrayList<>();
    }

    public void setFilteredIngredients(List<Ingredient> filteredIngredients) {
        this.filteredIngredients.clear();
        this.filteredIngredients.addAll(filteredIngredients);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return filteredIngredients.size();
    }

    @Override
    public Filter getFilter() {
        return new IngredientFilter(this, ingredients);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Ingredient ingredient = filteredIngredients.get(position);

        convertView = LayoutInflater
                .from(getContext())
                .inflate(R.layout.list_item_dropdown_ingredient, parent, false);

        TextView textViewIngredientName = (TextView) convertView
                .findViewById(R.id.text_view_ingredient_name);

        textViewIngredientName.setText(ingredient.getName());

        return convertView;
    }

    @Override
    public Ingredient getItem(int position) {
        return super.getItem(position);
    }
}
