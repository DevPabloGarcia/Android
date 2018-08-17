package pablogarcia.imagelisttelefonica.managers.recyclerViewManager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.imagelisttelefonica.R;
import pablogarcia.imagelisttelefonica.model.Image;

public class ImageViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.authorNameTextView) TextView authorNameTextview;
    @BindView(R.id.imageNameTextView) TextView imageNameTextView;
    @BindView(R.id.imageView) ImageView imageView;

    private View row;
    OnClickImageRow mListener;

    public ImageViewHolder(View itemView, OnClickImageRow listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.row = itemView;
        this.mListener = listener;
    }

    public void bind(final Image image){
        this.authorNameTextview.setText(image.getAuthor());
        this.imageNameTextView.setText(image.getFilename());
        Picasso.get()
                .load(image.getImgePath())
                .fit()
                .centerCrop()
                .into(this.imageView);
        this.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickImageRow(image);
            }
        });
    }
}
