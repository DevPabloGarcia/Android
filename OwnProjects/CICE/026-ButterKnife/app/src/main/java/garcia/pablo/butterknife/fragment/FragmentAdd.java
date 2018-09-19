package garcia.pablo.butterknife.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import garcia.pablo.butterknife.R;

/**
 * Created by V on 23/1/16.
 */
public class FragmentAdd extends Fragment {

    public static FragmentAdd newInstance() {

        return  new FragmentAdd();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add,container,false);
    }
}
