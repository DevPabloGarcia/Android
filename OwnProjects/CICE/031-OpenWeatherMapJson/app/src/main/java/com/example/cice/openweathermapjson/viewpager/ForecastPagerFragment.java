package com.example.cice.openweathermapjson.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cice.openweathermapjson.R;
import com.example.cice.openweathermapjson.model.Forecast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CICE on 12/2/16.
 */
public class ForecastPagerFragment extends Fragment{

    private static final String ARG_FORECAST = "forecast";

    Context context;

    @Bind(R.id.text_view_day) TextView textViewDay;
    @Bind(R.id.text_view_temp) TextView textViewTemp;
    @Bind(R.id.text_view_max_temp) TextView textViewMaxTemp;
    @Bind(R.id.text_view_min_temp) TextView textViewMinTemp;

    private Forecast forecast;


    public static ForecastPagerFragment newInstance(Forecast forecast) {

        ForecastPagerFragment fragment = new ForecastPagerFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_FORECAST, forecast);
        fragment.setArguments(args);

        return fragment;
    }

    public ForecastPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();

        if (getArguments() != null) {
            forecast = getArguments().getParcelable(ARG_FORECAST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.row_forecast, container, false);

        ButterKnife.bind(this, layout);

        textViewDay.setText(forecast.getDateTime());
        textViewTemp.setText(String.format("%s ºC", forecast.getDayTemp()));
        textViewMaxTemp.setText(String.format("Max: %s ºC", forecast.getMaxTemp()));
        textViewMinTemp.setText(String.format("Min: %s ºC", forecast.getMinTemp()));

        return layout;
    }

}
