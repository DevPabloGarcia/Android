package com.example.cice.customtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by CICE on 16/2/16.
 */

public class TimeTextView extends TextView {

    String template;
    int duration;

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs,R.styleable.TimeTextView);

        duration = attributes.getInt(R.styleable.TimeTextView_duration,0);
        template = attributes.getString(R.styleable.TimeTextView_template);


        setDuration(duration);

        attributes.recycle();

    }

    public void setDuration(int seconds){

        int durationInMinutes = seconds / 60;
        int hours = durationInMinutes / 60;
        int minutes = durationInMinutes % 60;

        String hoursText = "";
        String minutesText = "";

        if(hours > 0){
            hoursText = hours + (hours == 1 ? " hour " : " hours ");

        }
        if(minutes > 0){
            minutesText = minutes + (minutes == 1 ? " minute " : " minutes ");

        }
        if(hours == 0 && minutes == 0){
            minutesText = "less than 1 minute";
        }

        String duration = hoursText + minutesText;
        if(template != null){
            duration = String.format(template, duration);
        }
        setText(duration);

    }



}
