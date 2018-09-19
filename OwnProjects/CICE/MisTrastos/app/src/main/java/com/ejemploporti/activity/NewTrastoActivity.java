package com.ejemploporti.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.ejemploporti.api.ApiManager;
import com.ejemploporti.api.TrastoException;
import com.ejemploporti.mistrastos.R;
import com.ejemploporti.model.Trasto;

public class NewTrastoActivity extends AppCompatActivity {


    EditText editTextName, editTextInfo, editTextPrice;
    TextInputLayout textInputLayoutName,  textInputLayoutInfo, textInputLayoutPrice;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trasto);

        this.doBinding();
        this.setupToolbar();
        this.setupTextInputLayout();
    }

    private void doBinding(){

        editTextName = (EditText) findViewById(R.id.edit_text_name);
        editTextInfo = (EditText) findViewById(R.id.edit_text_info);
        editTextPrice = (EditText) findViewById(R.id.edit_text_price);

        textInputLayoutInfo = (TextInputLayout) findViewById(R.id.text_imput_layout_info);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.text_imput_layout_name);
        textInputLayoutPrice = (TextInputLayout) findViewById(R.id.text_imput_layout_price);

        checkBox = (CheckBox) findViewById(R.id.checkbox);

    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar =  getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Nuevo Trasto");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_clear);
        }
    }

    private void setupTextInputLayout(){
        textInputLayoutInfo.setCounterEnabled(true);
        textInputLayoutInfo.setCounterMaxLength(140);

        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    textInputLayoutName.setErrorEnabled(false);
                }else{
                    String name = editTextName.getText().toString().trim();

                    if (name.isEmpty()){
                        textInputLayoutName.setErrorEnabled(true);
                        textInputLayoutName.setError("Este campo no puede estar vacío.");
                    }
                }
            }
        });

    }

    private void createTrasto(){

        Trasto trasto = new Trasto();

        String name = editTextName.getText().toString().trim();
        if(name.isEmpty())
            return;
        trasto.setName(name);

        String info = editTextInfo.getText().toString().trim();
        if(!info.isEmpty())
            trasto.setInfo(info);

        String stringPrice = editTextPrice.getText().toString().trim();
        if(checkBox.isChecked()){
            trasto.setOnSale(true);
            if(stringPrice.isEmpty()){
                textInputLayoutPrice.setErrorEnabled(true);
                textInputLayoutPrice.setError("Si esta en venta, ha de tener un precio.");
                editTextPrice.requestFocus();
                return;
            }else{
                textInputLayoutPrice.setErrorEnabled(false);
            }
        }else{
            trasto.setOnSale(false);
        }
        if(!stringPrice.isEmpty()) {
            float price = Float.parseFloat(stringPrice);
            trasto.setPrice(price);
        }

        // Creamos el trasto y lo metemos en bd.

        Intent result = new Intent();
        result.putExtra("trasto", trasto);
        try {
            new ApiManager(this).insertTrasto(trasto);
            setResult(RESULT_OK, result);
        } catch (TrastoException e) {
            setResult(RESULT_CANCELED, result);
            e.printStackTrace();
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                break;
            case R.id.action_done:
                createTrasto();
                break;
            default:
                break;
        }
        return true;
    }

}
