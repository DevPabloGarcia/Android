package pablo.com.mipiso.ui.main.noFlat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pablo.com.mipiso.R;

public class NoFlatFragment extends Fragment implements NoFlatView{


    public static NoFlatFragment newInstance() {

        Bundle args = new Bundle();

        NoFlatFragment fragment = new NoFlatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_flat,container, false);
        return view;
    }
}
