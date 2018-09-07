package pablogarcia.meetup.Modules.Fragments.DateTimePicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

import pablogarcia.meetup.Utils.Utils;


public class DateTimePicker implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private int year;
    private int month;
    private int dayOfMonth;

    private FragmentManager fragmentManager;
    private OnDateTimePickerListener listener;

    public DateTimePicker(FragmentManager fragmentManager, OnDateTimePickerListener listener) {
        this.fragmentManager = fragmentManager;
        this.listener = listener;
        DatePickerFragment newFragment = DatePickerFragment.newInstance(this);
        newFragment.show(fragmentManager, "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;

        TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(this);
        timePickerFragment.show(fragmentManager, "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String date = Utils.twoCharacters(this.dayOfMonth) + "/"
                + Utils.twoCharacters(this.month)
                + "/"
                + Utils.twoCharacters(this.year)
                + " - "
                + Utils.twoCharacters(hourOfDay)
                + ":"
                + Utils.twoCharacters(minute);

        Calendar calendar = Calendar.getInstance();
        calendar.set(this.year, this.month, this.dayOfMonth, hourOfDay, minute);

        this.listener.onDateTimeClose(date, calendar.getTimeInMillis());

    }
}
