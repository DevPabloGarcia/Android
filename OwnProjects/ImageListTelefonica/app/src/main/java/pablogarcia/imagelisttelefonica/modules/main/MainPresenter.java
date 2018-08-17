package pablogarcia.imagelisttelefonica.modules.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import pablogarcia.imagelisttelefonica.managers.recyclerViewManager.OnClickImageRow;
import pablogarcia.imagelisttelefonica.model.Image;

public class MainPresenter implements MainInteractor.OnImageListCallback{

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenter(MainView mainView, MainInteractor mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    public void getImageList(){
        this.mainInteractor.getImageList(this);
    }

    public void setupRecyclerView(RecyclerView recyclerView, ArrayList<Image> images, Context context, OnClickImageRow listener){
        this.mainInteractor.setupRecyclerView(recyclerView, images, context, listener);
    }

    @Override
    public void onSuccess(ArrayList<Image> images) {
        this.mainView.setupRecyclerView(images);
    }

    @Override
    public void onFailure() {
        this.mainView.showErrorToast();
    }
}
