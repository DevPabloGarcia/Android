package com.example.cice.sqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CICE on 19/1/16.
 */
public class DatabaseManager extends SQLiteOpenHelper {

    Context context;

    private static final String DB_NAME = "trastosDB.db";
    private static final int DB_VERSION = 2;

    private static DatabaseManager mInstance = null;

    protected static DatabaseManager getInstance(Context context) {
        if(mInstance==null)
            mInstance = new DatabaseManager(context);
        return mInstance;
    }

    private DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // La primera creacion de la base de datos si no existe.
        // Creamos tablas de la BBDD.
        // FORMA MALA: db.execSQL("CREATE TABLE 't_trastos' (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);");
        // Sacamos a la interfaz.
        db.execSQL(TrastosTable.CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
