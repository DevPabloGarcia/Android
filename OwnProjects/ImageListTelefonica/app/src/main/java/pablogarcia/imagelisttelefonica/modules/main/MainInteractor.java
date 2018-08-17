package pablogarcia.imagelisttelefonica.modules.main;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import pablogarcia.imagelisttelefonica.ApiManager;
import pablogarcia.imagelisttelefonica.managers.recyclerViewManager.OnClickImageRow;
import pablogarcia.imagelisttelefonica.managers.recyclerViewManager.RecyclerViewAdapter;
import pablogarcia.imagelisttelefonica.managers.retrofitManager.ApiService;
import pablogarcia.imagelisttelefonica.model.Image;
import pablogarcia.imagelisttelefonica.utils.Consts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractor {

    public interface OnImageListCallback{

        void onSuccess(ArrayList<Image> images);

        void onFailure();
    }

    public void setupRecyclerView(RecyclerView recyclerView, ArrayList<Image> images, Context context, OnClickImageRow listener){

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        adapter.setDataSet(images);

    }

    public void getImageList(final OnImageListCallback mListener){

        ApiService apiService = ApiManager.getApiService(Consts.IMAGES_URL);
        apiService.imageList().enqueue(new Callback<ArrayList<Image>>() {
            @Override
            public void onResponse(Call<ArrayList<Image>> call, Response<ArrayList<Image>> response) {
                ArrayList<Image> images = response.body();
                Collections.sort(images);
                mListener.onSuccess(images);
            }

            @Override
            public void onFailure(Call<ArrayList<Image>> call, Throwable t) {
                mListener.onFailure();
            }
        });

    }

}
