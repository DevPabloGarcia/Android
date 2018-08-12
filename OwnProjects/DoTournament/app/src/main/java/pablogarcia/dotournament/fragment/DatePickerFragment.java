package pablogarcia.dotournament.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import pablogarcia.dotournament.R;

/**
 * Created by V on 13/5/16.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private DatePickerDialog.OnDateSetListener mListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), R.style.DialogTheme, this, year, month, day);
    }

    public void setmListener(DatePickerDialog.OnDateSetListener listener){
        this.mListener = listener;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if(mListener!=null){
            mListener.onDateSet(view, year, monthOfYear,dayOfMonth);
        }
    }
}
