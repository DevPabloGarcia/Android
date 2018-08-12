package pablogarcia.dotournament.customview;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by V on 5/5/16.
 */
public class CustomProgressDialog extends ProgressDialog {

    public CustomProgressDialog(Context context, String title, String message) {
        super(context);
        setTitle(title);
        setMessage(message);
        show();
    }

}
