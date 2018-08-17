package pablogarcia.imagelisttelefonica.modules.detail;

import android.view.MenuItem;
import android.widget.ImageView;

import pablogarcia.imagelisttelefonica.model.Image;

public class DetailPresenter implements DetailInteractor.OnDetailListener{

    private DetailView detailView;
    private DetailInteractor detailInteractor;

    public DetailPresenter(DetailView detailView, DetailInteractor detailInteractor) {
        this.detailView = detailView;
        this.detailInteractor = detailInteractor;
    }

    public void onOptionItemSelected(MenuItem item){
        this.detailInteractor.onOptionItemSelected(item, this);
    }

    public void updateImage(Image image, ImageView imageView){
        this.detailInteractor.updateImage(image, imageView);
    }

    @Override
    public void navigateBack() {
        this.detailView.navigateBack();
    }
}
