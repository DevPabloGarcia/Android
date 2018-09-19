package garcia.pablo.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by V on 2/2/16.
 */
public class BitmapHelper {

    public static Bitmap getBitmapFromFile(File file){
        BitmapFactory.Options options = new BitmapFactory.Options();
        return BitmapFactory.decodeFile(file.getAbsolutePath(),options);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int targetWith){
        Bitmap newBitmap;
        float aspectRadio = bitmap.getWidth() / (float) bitmap.getHeight();
        int width = targetWith;
        int height = Math.round(width / aspectRadio);
        newBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        return newBitmap;
    }
    /*
    public static int dpToPx(int dp){
        return Math.round(context.getResources());
    }
    */
}
