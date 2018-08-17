package pablogarcia.imagelisttelefonica.modules.main;

import java.util.ArrayList;

import pablogarcia.imagelisttelefonica.model.Image;

public interface MainView {

    void setupToolbar();
    void showErrorToast();
    void setupRecyclerView(ArrayList<Image> images);
    void navigateDetailImage(Image image);
}
