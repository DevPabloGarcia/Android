package com.example.cice.autocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextViewIngredients, getAutoCompleteTextViewCustomIngredients;
    String[] ingredients = {"Leche", "Huevos", "Cereal", "Cerebro", "Celulosa", "Gluten", "Glutamato monosódico"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextViewIngredients
                = (AutoCompleteTextView) findViewById(R.id.auto_complete_text_view_ingredients);
        getAutoCompleteTextViewCustomIngredients
                = (AutoCompleteTextView) findViewById(R.id.auto_complete_text_view_custom_ingredients);

        setupAutoComplete();
        setupAutoCompleteCustom();

    }

    private void setupAutoCompleteCustom() {
        AutoCompleteIngredientsAdapter ingredientsAdapter
                = new AutoCompleteIngredientsAdapter(this, getIngredients());

        getAutoCompleteTextViewCustomIngredients.setAdapter(ingredientsAdapter);
    }

    private ArrayList<Ingredient> getIngredients() {

        ArrayList<Ingredient> ingredients = new ArrayList<>();


        for (int i=0;i<10000;i++) {
            ingredients.add(new Ingredient("Leche " +i));
        }

        return ingredients;
    }

    private void setupAutoComplete() {
        ArrayAdapter<String> stringArrayAdapter
                = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ingredients);

        autoCompleteTextViewIngredients.setAdapter(stringArrayAdapter);

        autoCompleteTextViewIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("autocomplete", "onClick");
            }
        });

        autoCompleteTextViewIngredients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("autocomplete", "onItemClick " + position);
            }
        });

        /*
        autoCompleteTextViewIngredients.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.i("autocomplete", "onDismiss");
            }
        });*/

        autoCompleteTextViewIngredients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("autocomplete", "onItemSelected " + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("autocomplete", "onNothingSelected");
            }
        });
    }
}
