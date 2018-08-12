package com.example.cice.openweathermapjson.http;

import com.example.cice.openweathermapjson.model.Forecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by CICE on 9/2/16.
 */
public class JsonParser {


    public static ArrayList<Forecast> parseForecasts(JSONObject responseJsonObject) throws JSONException {

        ArrayList<Forecast> forecasts = new ArrayList<>();

        JSONArray forecastList = responseJsonObject.getJSONArray("list");
        for (int i=0; i<forecastList.length();i++) {

            JSONObject forecastJsonObject = forecastList.getJSONObject(i);
            forecasts.add(parseForecast(forecastJsonObject));

        }

        return forecasts;
    }

    private static Forecast parseForecast(JSONObject forecastJsonObject) throws JSONException {

        Forecast forecast = new Forecast();

        int dateTime;
        String pressure;
        String speed;

        dateTime = forecastJsonObject.getInt("dt");
        //pasar a fecha string y meterlo al objecto forecast

        Date time = new Date((long)dateTime*1000);

        forecast.setDateTime(new SimpleDateFormat("dd/MM/yyyy").format(time));


        pressure = forecastJsonObject.getString("pressure");
        forecast.setPressure(pressure);

        speed = forecastJsonObject.getString("speed");
        forecast.setSpeed(speed);

        JSONObject temJsonObject = forecastJsonObject.getJSONObject("temp");
        parseTemperature(forecast, temJsonObject);

        JSONArray weatherJsonArray = forecastJsonObject.getJSONArray("weather");
        parseWeather(forecast, weatherJsonArray.getJSONObject(0));

        return forecast;

    }

    private static void parseTemperature(Forecast forecast, JSONObject tempJsonObject) throws JSONException {

        String dayTemp, minTemp, maxTemp;

        dayTemp = tempJsonObject.getString("day");
        minTemp = tempJsonObject.getString("min");
        maxTemp = tempJsonObject.getString("max");

        forecast.setDayTemp(dayTemp);
        forecast.setMinTemp(minTemp);
        forecast.setMaxTemp(maxTemp);
    }

    private static void parseWeather(Forecast forecast, JSONObject weatherJsonObject) throws JSONException {

        forecast.setText(weatherJsonObject.getString("main"));
        forecast.setDescription(weatherJsonObject.getString("description"));
    }
}
