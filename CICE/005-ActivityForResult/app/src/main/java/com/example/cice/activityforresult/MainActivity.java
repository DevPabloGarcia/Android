package com.example.cice.activityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CODE = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickNext(View view) {
        Intent intent = new Intent(this,DetailActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView textView = (TextView) findViewById(R.id.my_text_view);
        if(requestCode==REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                textView.setText(data.getStringExtra("texto"));
            } else if (resultCode == RESULT_CANCELED) {
                textView.setText("Cancelado");
            }
        }
    }
}
