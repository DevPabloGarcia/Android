package pablogarcia.hastengroupapp;

import android.content.Context;

import pablogarcia.hastengroupapp.ConectivityManager.ConectivityManager;
import pablogarcia.hastengroupapp.RetrofitManager.ApiService;
import pablogarcia.hastengroupapp.RetrofitManager.RetrofitClient;

public class ApiManager {

    public ApiManager() {}

    public ApiService getApiService(String baseUrl){
        return RetrofitClient.getClient(baseUrl).create(ApiService.class);
    }

    public static Boolean checkConection(Context context){
        return ConectivityManager.checkConection(context);
    }
}
