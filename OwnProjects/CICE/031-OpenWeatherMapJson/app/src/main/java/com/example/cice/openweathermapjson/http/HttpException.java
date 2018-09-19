package com.example.cice.openweathermapjson.http;

/**
 * Created by CICE on 11/2/16.
 */
public class HttpException extends Exception {
    public HttpException(String detailMessage) {
        super(detailMessage);
    }
}