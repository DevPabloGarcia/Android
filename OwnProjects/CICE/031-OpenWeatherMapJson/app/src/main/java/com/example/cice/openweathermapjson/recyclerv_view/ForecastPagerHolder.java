package com.example.cice.openweathermapjson.recyclerv_view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cice.openweathermapjson.viewpager.ForecastPagerAdapter;
import com.example.cice.openweathermapjson.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CICE on 12/2/16.
 */
public class ForecastPagerHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.view_pager) ViewPager viewPager;

    public ForecastPagerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(FragmentManager fm, ArrayList<Fragment> fragments) {

        ForecastPagerAdapter forecastPagerAdapter = new ForecastPagerAdapter(fm, fragments);

        /*
        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = fragments.get(0).getView().getLayoutParams().height;
        viewPager.setLayoutParams(params);*/

        viewPager.setAdapter(forecastPagerAdapter);

    }
}
