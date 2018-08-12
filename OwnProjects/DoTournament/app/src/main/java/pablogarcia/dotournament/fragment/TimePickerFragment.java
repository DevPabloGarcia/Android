package pablogarcia.dotournament.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;

import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;

import pablogarcia.dotournament.R;

/**
 * Created by V on 12/5/16.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private TimePickerDialog.OnTimeSetListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), R.style.DialogTheme, this, hour, minute,true);
    }

    public void setmListener(TimePickerDialog.OnTimeSetListener listener){
        this.mListener = listener;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(mListener != null){
            mListener.onTimeSet(view, hourOfDay, minute);
        }
    }
}
