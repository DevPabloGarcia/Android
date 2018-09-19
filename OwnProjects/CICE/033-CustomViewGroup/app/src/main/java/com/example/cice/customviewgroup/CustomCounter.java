package com.example.cice.customviewgroup;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by CICE on 23/2/16.
 */
public class CustomCounter extends FrameLayout{

    private int counter = 0;
    FloatingActionButton FABdecrease;
    FloatingActionButton FABincrease;

    TextView textViewCounter;

    public CustomCounter(Context context) {
        super(context);
        init();
    }

    public CustomCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCounter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        inflate(getContext(),R.layout.custom_counter, this);

        textViewCounter = (TextView)findViewById(R.id.text_view_counter);
        textViewCounter.setText(String.valueOf(counter));

        FABdecrease = (FloatingActionButton) findViewById(R.id.button_decrease);
        FABincrease = (FloatingActionButton) findViewById(R.id.button_increase);

        FABincrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseCounter();
            }
        });

        FABdecrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseCounter();
            }
        });
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    private void increaseCounter(){
        counter++;
        textViewCounter.setText(String.valueOf(counter));
    }

    private void decreaseCounter(){
        counter--;
        textViewCounter.setText(String.valueOf(counter));
    }

}
