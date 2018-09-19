package com.example.cice.openweathermapjson.api;

import android.content.Context;

import com.example.cice.openweathermapjson.http.HttpException;
import com.example.cice.openweathermapjson.http.HttpRequestManager;
import com.example.cice.openweathermapjson.model.Forecast;

import java.util.ArrayList;

/**
 * Created by CICE on 9/2/16.
 */
public class ApiManager {

    private Context context;
    private static ApiManager mInstance;

    private HttpRequestManager httpRequestManager;

    public static ApiManager getInstance(Context context) {

        if (mInstance==null) {
            mInstance = new ApiManager(context);
        }

        return mInstance;
    }

    private ApiManager(Context context) {
        this.context = context;
        httpRequestManager = new HttpRequestManager();
    }

    public ArrayList<Forecast> getDailyForecast(String city, int days) throws HttpException {
        return httpRequestManager.getDailyForecast(city, days);
    }
}
