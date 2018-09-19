package com.example.cice.sqlite.database;

import com.example.cice.sqlite.Model.Trasto;

import java.util.ArrayList;

/**
 * Created by CICE on 19/1/16.
 */
public interface TrastosTable {

    String TABLE_NAME = "t_trastos";

    String COLUMN_ID        = "id";
    String COLUMN_NAME      = "name";
    String COLUMN_INFO      = "info";
    String COLUMN_PRICE     = "price";
    String COLUMN_ONSALE    = "on_sale";
    String COLUMN_IMAGE     = "image";

    String[] PROJECTION_TOTAL = {COLUMN_ID, COLUMN_NAME, COLUMN_INFO, COLUMN_PRICE, COLUMN_ONSALE, COLUMN_IMAGE  };

    // Todas estas creaciones son la misma

    // Primera manera.
    String CREATE = "CREATE TABLE '" + TABLE_NAME + "' (" +
            COLUMN_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME     + " TEXT NOT NULL, " +
            COLUMN_INFO     + " TEXT, " +
            COLUMN_PRICE    + " REAL DEFAULT 0, " +
            COLUMN_ONSALE   + " BOOLEAN, " +
            COLUMN_IMAGE    + " BLOB " +
            ");";

    // Segunda manera.
    String CREATE2 = String.format("CREATE TABLE '%s' (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT , %s TEXT , %s REAL , %s BOOLEAN, %s BLOB);",
            TABLE_NAME,
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_INFO,
            COLUMN_PRICE,
            COLUMN_ONSALE,
            COLUMN_IMAGE);

    // Tercera manera.
    String ID       = COLUMN_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
    String NAME     = COLUMN_NAME   + " TEXT, ";
    String INFO     = COLUMN_INFO   + " TEXT, ";
    String PRICE    = COLUMN_PRICE  + " REAL, ";
    String ONSALE   = COLUMN_ONSALE + " BOOLEAN, ";
    String IMAGE    = COLUMN_IMAGE  + " BLOB";

    String CREATE3 = String
        .format("CREATE TABLE %s (%s, %s , %s, %s, %s , %s );",
                TABLE_NAME,
                ID,
                NAME,
                INFO,
                PRICE,
                ONSALE,
                IMAGE);

    long insertTrastos(Trasto trasto) throws DatabaseException;
    void deleteTrasto(Trasto trasto);
    void updateTrasto(Trasto trasto);
    Trasto getTrasto(long id) throws DatabaseException;
    ArrayList<Trasto> getTrastos();
}
