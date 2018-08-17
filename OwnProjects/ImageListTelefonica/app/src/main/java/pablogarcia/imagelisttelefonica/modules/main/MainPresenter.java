package pablogarcia.imagelisttelefonica.modules.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import pablogarcia.imagelisttelefonica.managers.recyclerViewManager.OnClickImageRow;
import pablogarcia.imagelisttelefonica.model.Image;

public class MainPresenter implements MainInteractor.OnImageListCallback{

    private MainView mainView;
    private MainInteractor mainInteractor;

    /**
     * Create the main presenter
     * @param mainView
     * @param mainInteractor
     */
    public MainPresenter(MainView mainView, MainInteractor mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    /**
     * Call the interactor game image list function in order to get the image list data
     */
    public void getImageList(){
        this.mainInteractor.getImageList(this);
    }

    /**
     * Setup the recycler view
     * @param recyclerView - The recyclerview to setup
     * @param images - The images list
     * @param context - The context
     * @param listener - The callback implementation listener
     */
    public void setupRecyclerView(RecyclerView recyclerView, ArrayList<Image> images, Context context, OnClickImageRow listener){
        this.mainInteractor.setupRecyclerView(recyclerView, images, context, listener);
    }

    /**
     * Callback success
     * @param images
     */
    @Override
    public void onSuccess(ArrayList<Image> images) {
        this.mainView.setupRecyclerView(images);
    }

    /**
     * Callback failure
     */
    @Override
    public void onFailure() {
        this.mainView.showErrorToast();
    }
}
