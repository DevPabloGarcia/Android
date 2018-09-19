package com.example.cice.openweathermapjson.recyclerv_view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cice.openweathermapjson.viewpager.ForecastPagerFragment;
import com.example.cice.openweathermapjson.R;
import com.example.cice.openweathermapjson.model.Forecast;

import java.util.ArrayList;

/**
 * Created by CICE on 12/2/16.
 */
public class ForecastRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int HOLDER_PAGER = 1;
    private static final int HOLDER_FORECAST = 2;

    private Context context;
    private FragmentManager fm;
    private ArrayList<Forecast> forecasts;
    private ArrayList<Fragment> forecastFragments;

    public ForecastRecyclerAdapter(Context context, FragmentManager fm) {
        this.forecasts = new ArrayList<>();
        this.context = context;
        this.fm = fm;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==HOLDER_PAGER) {
            View layout = LayoutInflater.from(context).inflate(R.layout.row_view_pager, parent, false);
            return new ForecastPagerHolder(layout);

        } else if (viewType==HOLDER_FORECAST){
            View layout = LayoutInflater.from(context).inflate(R.layout.row_forecast, parent, false);
            return new ForecastViewHolder(layout);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ForecastViewHolder) {
            ((ForecastViewHolder)holder).bind(forecasts.get(position));

        } else if (holder instanceof ForecastPagerHolder) {

            ((ForecastPagerHolder)holder).bind(fm, forecastFragments);
        }
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public void setDataSet(ArrayList<Forecast> forecasts) {
        this.forecasts = forecasts;
        notifyDataSetChanged();

        forecastFragments = new ArrayList<>();
        for (Forecast forecast: forecasts) {
            forecastFragments.add(ForecastPagerFragment.newInstance(forecast));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0)
            return HOLDER_PAGER;
        else
            return HOLDER_FORECAST;
    }
}
