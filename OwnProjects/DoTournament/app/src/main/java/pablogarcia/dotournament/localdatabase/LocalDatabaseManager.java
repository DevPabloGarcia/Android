package pablogarcia.dotournament.localdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pablogarcia.dotournament.localdatabase.sport.SportLocalTable;

/**
 * Created by V on 18/5/16.
 */
public class LocalDatabaseManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "dotournamentDB.db";
    private static final int DB_VERSION = 1;

    private static LocalDatabaseManager mInstance = null;

    public static LocalDatabaseManager getInstance(Context context){
        if (mInstance == null){
            mInstance = new LocalDatabaseManager(context);
        }
        return mInstance;
    }

    public LocalDatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SportLocalTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
