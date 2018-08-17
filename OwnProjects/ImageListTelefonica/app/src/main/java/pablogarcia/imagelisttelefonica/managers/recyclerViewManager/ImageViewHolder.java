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
    private OnClickImageRow mListener;

    /**
     * Create the image view holder
     * @param itemView - The view
     * @param listener - The callback
     */
    public ImageViewHolder(View itemView, OnClickImageRow listener) {
        super(itemView);

        // Get the xml views
        ButterKnife.bind(this, itemView);

        // Store the view and the callback in order to use in the callback
        this.row = itemView;
        this.mListener = listener;
    }

    /**
     * Bind the image information to the view
     * @param image - The image object to obtain the data to show
     */
    public void bind(final Image image){
        this.authorNameTextview.setText(image.getAuthor());
        this.imageNameTextView.setText(image.getFilename());
        Picasso.get()
                .load(image.getImagePath())
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
