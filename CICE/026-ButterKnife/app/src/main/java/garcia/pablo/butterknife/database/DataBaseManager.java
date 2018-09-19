package garcia.pablo.butterknife.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by V on 25/1/16.
 */
public class DataBaseManager extends SQLiteOpenHelper {

    private Context context;

    private static final String DB_NAME = "playerDB.db";
    private static final int DB_VERSION = 1;

    private static DataBaseManager mInstance = null;

    public static DataBaseManager getInstance(Context context){

        if(mInstance == null){
            mInstance = new DataBaseManager(context);
        }
        return mInstance;

    }

    private DataBaseManager(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PlayerTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
