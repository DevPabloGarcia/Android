package pablogarcia.meetup.Modules.Fragments.DetailImage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.Model.Meet;
import pablogarcia.meetup.R;

public class DetailImageFragment extends Fragment {

    private static final String MEET_ARGS = "MEET";

    @BindView(R.id.detailImageView) ImageView imageView;

    public static DetailImageFragment newInstance(Meet meet) {

        Bundle args = new Bundle();

        args.putParcelable(MEET_ARGS, meet);

        DetailImageFragment fragment = new DetailImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_view, container, false);

        ButterKnife.bind(this, view);
        Meet meet = getArguments().getParcelable(MEET_ARGS);
        Picasso.get()
                .load(meet.getImage())
                .fit()
                .centerCrop()
                .into(this.imageView);
        return view;
    }
}
