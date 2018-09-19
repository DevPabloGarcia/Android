package com.example.cice.openweathermapjson.http;

import com.example.cice.openweathermapjson.model.Forecast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CICE on 9/2/16.
 */
public class HttpRequestManager {

    /*
    *  http://api.openweathermap.org/data/2.5
    *  /forecast/daily
    *  ?q=London&mode=xml&units=metric&cnt=7&appid=44db6a862fba0b067b1930da0d769e98
    * */

    HttpRequestHelper httpRequestHelper;

    private final String API_KEY = "53d6800cc78467cae9c1a71aae941976";
    private final String API_URL = "http://api.openweathermap.org/data/2.5";
    private final String API_SERVICE = "/forecast/daily";

    public HttpRequestManager() {
        httpRequestHelper = new HttpRequestHelper();
    }

    public ArrayList<Forecast> getDailyForecast(String city, int days) throws HttpException {

        HashMap<String, String> params = new HashMap<>();
        params.put("q", city);
        params.put("units", "metric");
        params.put("appid", API_KEY);
        params.put("cnt", String.valueOf(days));

        String query = HttpRequestHelper.createQuery(params);

        String response = httpRequestHelper.getResponseFromUrlWithOkHttp(API_URL + API_SERVICE + query);

        ArrayList<Forecast> forecasts;
        try {
            JSONObject responseJsonObject = new JSONObject(response);
            forecasts = JsonParser.parseForecasts(responseJsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new HttpException("La respuesta del servidor no es correcta");
        }

        return forecasts;
    }
}
