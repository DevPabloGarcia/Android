package pablogarcia.imagelisttelefonica.managers.recyclerViewManager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pablogarcia.imagelisttelefonica.R;
import pablogarcia.imagelisttelefonica.model.Image;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private ArrayList<Image> images;
    private OnClickImageRow mListener;

    /**
     * Create the Recycler view adapter
     * @param mListener - The callback to call when te user clicks a row.
     */
    public RecyclerViewAdapter(OnClickImageRow mListener) {
        this.mListener = mListener;
    }

    /**
     * Create each view holder
     * @param parent - the view group
     * @param viewType - the view type
     * @return
     */
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_row, parent, false);
        return new ImageViewHolder(view, this.mListener);
    }

    /**
     * Bind the data to the view holder
     * @param holder - The view
     * @param position - The image object to bind
     */
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bind(this.images.get(position));
    }

    /**
     *
     * @return the total images number
     */
    @Override
    public int getItemCount() {
        if(images != null){
            return images.size();
        }
        return 0;
    }

    /**
     * Set the data to the view adapter and notify it
     * @param images
     */
    public void setDataSet(ArrayList<Image> images){
        this.images = images;
        notifyDataSetChanged();
    }
}
