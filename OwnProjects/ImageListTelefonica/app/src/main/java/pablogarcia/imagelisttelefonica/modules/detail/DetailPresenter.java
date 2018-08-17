package pablogarcia.imagelisttelefonica.modules.detail;

import android.view.MenuItem;

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

    @Override
    public void navigateBack() {
        this.detailView.navigateBack();
    }
}
