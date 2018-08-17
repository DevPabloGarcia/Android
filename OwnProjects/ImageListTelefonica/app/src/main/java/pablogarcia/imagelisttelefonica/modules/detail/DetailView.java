package pablogarcia.imagelisttelefonica.modules.detail;

import pablogarcia.imagelisttelefonica.model.Image;

public interface DetailView {

    void getIntents();

    void updateImage(Image image);

    void setupToolbar();

    void navigateBack();
}
