package com.example.cice.autocompletetextview;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CICE on 10/3/16.
 */
public class IngredientFilter extends Filter {

    AutoCompleteIngredientsAdapter adapter;
    ArrayList<Ingredient> originalIngredients;
    ArrayList<Ingredient> filteredIngredients;

    public IngredientFilter(AutoCompleteIngredientsAdapter adapter, ArrayList<Ingredient> originalIngredients) {
        this.adapter = adapter;
        this.originalIngredients = originalIngredients;
        this.filteredIngredients = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults filterResults = new FilterResults();

        filteredIngredients.clear();

        if (constraint==null || constraint.length()==0) {
            filteredIngredients.addAll(originalIngredients);
        } else {

            String filterPattern = constraint.toString().toLowerCase().trim();

            for (Ingredient ingredient : originalIngredients) {
                if (ingredient.getName().toLowerCase().contains(filterPattern)) {
                    filteredIngredients.add(ingredient);
                }
            }
        }

        filterResults.values = filteredIngredients;
        filterResults.count = filteredIngredients.size();

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.setFilteredIngredients((List<Ingredient>) results.values);

    }
}
