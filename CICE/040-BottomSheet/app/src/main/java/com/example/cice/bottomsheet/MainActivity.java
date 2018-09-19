package com.example.cice.bottomsheet;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setPeekHeight(100);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i("bottomsheet", "collapsed");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("bottomsheet", "dragging");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i("bottomsheet", "expanded");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i("bottomsheet", "hidden");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.i("bottomsheet", "settling" );
                        break;
                }

            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });



    }
}
