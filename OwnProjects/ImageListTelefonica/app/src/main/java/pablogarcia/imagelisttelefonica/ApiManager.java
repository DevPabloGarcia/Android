package pablogarcia.imagelisttelefonica;

import pablogarcia.imagelisttelefonica.managers.retrofitManager.ApiService;
import pablogarcia.imagelisttelefonica.managers.retrofitManager.RetrofitClient;

public class ApiManager {

    public static ApiService getApiService(String baseUrl){
        return RetrofitClient.getClient(baseUrl).create(ApiService.class);
    }

}
