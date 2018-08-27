package pablogarcia.meetup.Modules.Fragments.DetailImage;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import pablogarcia.meetup.Model.Meet;

public class DetailImagePresenter {

    private DetailImageInteractor detailImageInteractor;
    private DetailImageView detailImageView;

    public DetailImagePresenter(DetailImageInteractor detailImageInteractor, DetailImageView detailImageView) {
        this.detailImageInteractor = detailImageInteractor;
        this.detailImageView = detailImageView;
    }

    public void setupViewPager(ViewPager viewPager, FragmentManager fm, Meet meet){

    }
}
