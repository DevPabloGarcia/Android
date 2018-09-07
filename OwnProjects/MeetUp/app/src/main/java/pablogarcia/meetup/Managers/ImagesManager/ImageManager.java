package pablogarcia.meetup.Managers.ImagesManager;

import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageManager {

    public void loadImageIntoImageView(ImageView imageView, Uri uri){
        Picasso.get().load(uri).fit().centerCrop().into(imageView);
    }

    public void loadImageIntoImageView(ImageView imageView, String string){
        Picasso.get().load(string).fit().centerCrop().into(imageView);
    }

    public void loadImageIntoImageView(ImageView imageView, File file){
        Picasso.get().load(file).fit().centerCrop().into(imageView);
    }

    public void loadImageIntoImageView(ImageView imageView, int resourceId){
        Picasso.get().load(resourceId).fit().centerCrop().into(imageView);
    }

}
