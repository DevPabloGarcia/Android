package garcia.pablo.camera;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

import java.io.File;

/**
 * Created by V on 2/2/16.
 */
public class MediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

    private MediaScannerConnection mediaScannerConnection;
    Context context;
    File file;

    public MediaScanner(Context context, File file) {
        this.context = context;
        this.file = file;

        mediaScannerConnection = new MediaScannerConnection(context,this);
        mediaScannerConnection.connect();

    }

    @Override
    public void onMediaScannerConnected() {
        mediaScannerConnection.scanFile(file.getAbsolutePath(),null);

    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        mediaScannerConnection.disconnect();
    }
}
