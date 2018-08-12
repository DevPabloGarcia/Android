package pablogarcia.dotournament.localdatabase.sport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import pablogarcia.dotournament.R;
import pablogarcia.dotournament.localdatabase.LocalDatabaseManager;
import pablogarcia.dotournament.localdatabase.exception.CursorParseException;
import pablogarcia.dotournament.localdatabase.exception.LocalDatabaseException;
import pablogarcia.dotournament.model.Sport;

/**
 * Created by V on 17/5/16.
 */
public class SportLocalDatabaseManager implements SportLocalTable {

    private Context context;
    LocalDatabaseManager localDatabaseManager;

    public SportLocalDatabaseManager(Context context) {
        this.context = context;
        localDatabaseManager = LocalDatabaseManager.getInstance(context);
    }

    @Override
    public void insertSport(Sport sport) throws LocalDatabaseException {

        SQLiteDatabase database = localDatabaseManager.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SportLocalTable.COLUMN_ID, sport.getId());
        values.put(SportLocalTable.COLUMN_NAME, sport.getName());
        values.put(SportLocalTable.COLUMN_SUMARY, sport.getSumary());
        values.put(SportLocalTable.COLUMN_DESCRIPTION, sport.getDescription());
        values.put(SportLocalTable.COLUMN_IMAGE, sport.getImage());

        long id = database.insert(SportLocalTable.TABLE_NAME, null, values);
        database.close();

        if (id == -1){
            throw new LocalDatabaseException(context.getString(R.string.exception_insert_sport));
        }
    }

    @Override
    public void insertAllSports(ArrayList<Sport> sports) throws LocalDatabaseException {
        for (int i = 0; i< sports.size(); i++){
            try {
                getSport(sports.get(i).getId());
                updateSport(sports.get(i));
            } catch (LocalDatabaseException e) {
                insertSport(sports.get(i));
            }
        }
    }

    @Override
    public void updateSport(Sport sport) {
        SQLiteDatabase database = localDatabaseManager.getWritableDatabase();

        String WHERE = SportLocalTable.COLUMN_ID + "=?";
        String [] WHERE_ARGS = {sport.getId()};
        ContentValues values = new ContentValues();

        values.put(SportLocalTable.COLUMN_ID, sport.getId());
        values.put(SportLocalTable.COLUMN_NAME, sport.getName());
        values.put(SportLocalTable.COLUMN_SUMARY, sport.getSumary());
        values.put(SportLocalTable.COLUMN_DESCRIPTION, sport.getDescription());
        values.put(SportLocalTable.COLUMN_IMAGE, sport.getImage());

        database.update(SportLocalTable.TABLE_NAME, values,WHERE, WHERE_ARGS);
    }

    @Override
    public Sport getSport(String id) throws LocalDatabaseException {

        SQLiteDatabase database = localDatabaseManager.getReadableDatabase();
        String WHERE = SportLocalTable.COLUMN_ID + "=?";
        String [] WHERE_ARGS = {id};

        Cursor cursor = database.query(
                SportLocalTable.TABLE_NAME,
                SportLocalTable.PROJECTION_TOTAL,
                WHERE,
                WHERE_ARGS,
                null,
                null,
                null
        );

        try{
            return SportLocalDatabaseParser.parseSport(cursor);
        }catch (CursorParseException e){
            throw new LocalDatabaseException(context.getString(R.string.exception_get_sport));
        }

    }

    @Override
    public ArrayList<Sport> getAllSports() {

        SQLiteDatabase database = localDatabaseManager.getReadableDatabase();
        Cursor cursor = database.query(
                SportLocalTable.TABLE_NAME,
                SportLocalTable.PROJECTION_TOTAL,
                null,
                null,
                null,
                null,
                SportLocalTable.COLUMN_NAME+" ASC"
        );
        ArrayList<Sport> sports = SportLocalDatabaseParser.parseAllSports(cursor);
        database.close();
        return  sports;
    }
}
