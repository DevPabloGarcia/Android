package pablogarcia.imagelisttelefonica.managers.retrofitManager;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    /**
     * Create the client withe the url passed
     * @param baseUrl - the url with the json response
     * @return the instance
     */
    public static Retrofit getClient(String baseUrl){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}