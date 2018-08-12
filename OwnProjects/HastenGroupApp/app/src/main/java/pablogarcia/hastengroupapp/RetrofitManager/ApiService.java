package pablogarcia.hastengroupapp.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import pablogarcia.hastengroupapp.Model.Sport;
        import retrofit2.Call;
        import retrofit2.http.GET;

public interface ApiService {

    @GET("bins/66851")
    Call<ArrayList<Sport>> sportList();
}
