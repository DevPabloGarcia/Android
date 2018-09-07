package pablogarcia.meetup.Modules.Fragments.PickImage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.R;


public class PickImageFragment extends DialogFragment implements PickImageView{

    private PickImageInteractor.OnSelectImageDialogListener listener;

    @BindView(R.id.buttonCamera) ImageButton buttonCamera;
    @BindView(R.id.buttonGallery) ImageButton buttonGallery;


    public static PickImageFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PickImageFragment fragment = new PickImageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_select_image, container);

        ButterKnife.bind(this, view);
        this.setupButtonsClick();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (PickImageInteractor.OnSelectImageDialogListener)context;
        } catch (ClassCastException e) {
            Log.e("CAST ERROR", e.getMessage());
        }

    }

    @Override
    public void setupButtonsClick() {
        this.buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickSelectFromGallery();
                dismiss();
            }
        });
        this.buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickSelectFromCamera();
                dismiss();
            }
        });
    }

}
