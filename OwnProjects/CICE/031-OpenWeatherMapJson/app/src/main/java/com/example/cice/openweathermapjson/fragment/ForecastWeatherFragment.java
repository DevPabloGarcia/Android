package com.example.cice.openweathermapjson.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cice.openweathermapjson.http.HttpException;
import com.example.cice.openweathermapjson.R;
import com.example.cice.openweathermapjson.api.ApiManager;
import com.example.cice.openweathermapjson.model.Forecast;
import com.example.cice.openweathermapjson.recyclerv_view.ForecastRecyclerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CICE on 12/2/16.
 */
public class ForecastWeatherFragment extends Fragment{
    
    private static final String ARG_FORECAST_CITY = "forecast_city";
    private static final String ARG_WEATHER_CITIES = "weather_cities";

    Context context;
    ApiManager apiManager;
    AsyncTask<String, Void, ArrayList<Forecast>> task;

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;

    private String forecastCity;
    private ArrayList<String> weatherCities;
    private ForecastRecyclerAdapter adapter;


    public static ForecastWeatherFragment newInstance(String forecastCity, ArrayList<String> weatherCities) {

        ForecastWeatherFragment fragment = new ForecastWeatherFragment();

        Bundle args = new Bundle();
        args.putString(ARG_FORECAST_CITY, forecastCity);
        args.putStringArrayList(ARG_WEATHER_CITIES, weatherCities);
        fragment.setArguments(args);

        return fragment;
    }

    public ForecastWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();

        if (getArguments() != null) {
            forecastCity = getArguments().getString(ARG_FORECAST_CITY);
            weatherCities = getArguments().getStringArrayList(ARG_WEATHER_CITIES);
        }

        apiManager = ApiManager.getInstance(getContext());

        task = new ForecastTask();
        task.execute(forecastCity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.recycler_view, container, false);

        ButterKnife.bind(this, layout);
        setupRecycler();
        setupSwipeRefreshLayout();

        return layout;
    }

    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshListener());
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
    }

    private class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            task.cancel(true);
            task = new ForecastTask();
            task.execute(forecastCity);
        }
    }


    private void setupRecycler() {

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ForecastRecyclerAdapter(context, getChildFragmentManager());
        recyclerView.setAdapter(adapter);

    }



    private class ForecastTask extends AsyncTask<String, Void, ArrayList<Forecast>> {

        ProgressDialog progressDialog;
        String error;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (swipeRefreshLayout==null || !swipeRefreshLayout.isRefreshing()) {
                progressDialog = ProgressDialog.show(context, "Tranqui", "Descargando");
            }

        }

        @Override
        protected ArrayList<Forecast> doInBackground(String... params) {



            try {
                String forecastCity = params[0];
                return apiManager.getDailyForecast(forecastCity, 7);
            } catch (HttpException e) {
                e.printStackTrace();
                error = e.getMessage();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Forecast> forecasts) {
            super.onPostExecute(forecasts);

            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            } else {
                progressDialog.dismiss();
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (forecasts!=null) {

                adapter.setDataSet(forecasts);
            } else {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    
}
