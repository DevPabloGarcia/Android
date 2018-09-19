package com.example.cice.activityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    public void onClickBack(View view) {

        EditText editText = (EditText) findViewById(R.id.edit_text);

        String my_string = editText.getText().toString().trim();

        Intent intent = new Intent();
        intent.putExtra("texto",my_string);
        // Ponemos el resultado
        setResult(RESULT_OK, intent);
        // Cerramos la activity.
        finish();
    }

    public void onClickCancel(View view) {

        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
