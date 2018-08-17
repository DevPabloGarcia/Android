package pablogarcia.imagelisttelefonica.managers.retrofitManager;

import java.util.ArrayList;

import pablogarcia.imagelisttelefonica.model.Image;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/list")
    Call<ArrayList<Image>> imageList();

}
