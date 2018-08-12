package pablogarcia.dotournament.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import pablogarcia.dotournament.R;

/**
 * Created by CICE on 29/3/16.
 */
public abstract class MainFragment extends Fragment {

    public void setupScrollBehaviour(boolean scroll){
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();

        if(scroll){
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
        }else{
            params.setScrollFlags(0);
        }

        toolbar.setLayoutParams(params);
    }

    public abstract void setupToolbar();
}
