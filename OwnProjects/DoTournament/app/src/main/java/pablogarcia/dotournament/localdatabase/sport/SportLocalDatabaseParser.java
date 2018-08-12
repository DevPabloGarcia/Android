package pablogarcia.dotournament.localdatabase.sport;

import android.database.Cursor;

import java.util.ArrayList;

import pablogarcia.dotournament.localdatabase.exception.CursorParseException;
import pablogarcia.dotournament.model.Sport;

/**
 * Created by V on 17/5/16.
 */
public class SportLocalDatabaseParser {

    protected static Sport parseSport(Cursor cursor) throws CursorParseException {
        Sport sport = new Sport();
        if(cursor.getCount() > 0){

            int index;

            if(cursor.getCount() == 1){
                cursor.moveToFirst();
            }

            index = cursor.getColumnIndex(SportLocalTable.COLUMN_ID);
            if(index!=-1){
                sport.setId(cursor.getString(index));
            }

            index = cursor.getColumnIndex(SportLocalTable.COLUMN_NAME);
            if(index!=-1){
                sport.setName(cursor.getString(index));
            }

            index = cursor.getColumnIndex(SportLocalTable.COLUMN_SUMARY);
            if(index!=-1){
                sport.setSumary(cursor.getString(index));
            }

            index = cursor.getColumnIndex(SportLocalTable.COLUMN_DESCRIPTION);
            if(index!=-1){
                sport.setDescription(cursor.getString(index));
            }

            index = cursor.getColumnIndex(SportLocalTable.COLUMN_IMAGE);
            if(index!=-1){
                sport.setImage(cursor.getString(index));
            }

        }else{
            throw new CursorParseException("");
        }
        return sport;
    }

    protected static ArrayList<Sport> parseAllSports(Cursor cursor){
        ArrayList<Sport> sports = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                try {
                    sports.add(parseSport(cursor));
                } catch (CursorParseException e) {
                    e.printStackTrace();
                }
            }while (cursor.moveToNext());
        }

        return sports;
    }
}
