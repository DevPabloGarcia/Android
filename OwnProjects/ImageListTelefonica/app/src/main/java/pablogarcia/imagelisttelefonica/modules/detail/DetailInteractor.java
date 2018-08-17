package pablogarcia.imagelisttelefonica.modules.detail;

import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import pablogarcia.imagelisttelefonica.model.Image;

public class DetailInteractor {

    interface OnDetailListener{
        void navigateBack();
    }


    /**
     * Update the image view with the image passed
     * @param image - The object with the image path
     * @param imageView - The container view to store the image
     */
    public void updateImage(Image image, ImageView imageView){
        try{
            Picasso.get()
                    .load(image.getImagePath())
                    .fit()
                    .centerCrop()
                    .into(imageView);
        }catch(Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    /**
     * Set action to the toolbar button
     * @param item
     * @param listener
     */
    public void onOptionItemSelected(MenuItem item, OnDetailListener listener){

        switch (item.getItemId()){
            // Top left button
            case android.R.id.home:
                listener.navigateBack();
                break;
        }
    }

}
