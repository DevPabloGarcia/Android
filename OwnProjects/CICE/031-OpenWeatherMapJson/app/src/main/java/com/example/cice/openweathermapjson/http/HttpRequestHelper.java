package com.example.cice.openweathermapjson.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by CICE on 9/2/16.
 */
public class HttpRequestHelper {

    HttpURLConnection connection;

    public String getResponseFromUrlWithOkHttp(String url) throws HttpException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpException("No se pudo conectar al servidor");
        }

    }
    /*
    public String getResponseFromUrl(String url) throws HttpException {

        return openConnection(url).getResponse();
    }

    private HttpRequestHelper openConnection(String stringUrl) throws HttpException {

        URL url;
        try {
            url = new URL(stringUrl);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
        } catch (IOException e) {
            throw new HttpException("No se pudo conectar con el servidor");
        }

        return this;
    }

    private String getResponse() throws HttpException {

        InputStream inputStream;

        try {
            if (connection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                return IOUtils.toString(inputStream);


                //    BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                //    StringBuilder total = new StringBuilder();
                //    String line;
                //   while ((line = r.readLine()) != null) {
                //       total.append(line);
                //   }


            } else {
                throw new HttpException("No se pudo conectar con el servidor");
                //inputStream = connection.getErrorStream();
                //return IOUtils.toString(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpException("No se pudo conectar con el servidor");
        }
    }
    */

    public static String createQuery(HashMap<String,String> params) {

        StringBuilder query = new StringBuilder();

        query.append("?");

        for (HashMap.Entry<String, String> entry : params.entrySet()) {
            query.append(entry.getKey() + "=" + entry.getValue());
            query.append("&");
        }

        query.deleteCharAt(query.length()-1);

        return query.toString();

    }
}
