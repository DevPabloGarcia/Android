package pablogarcia.dotournament.localdatabase.sport;

import java.util.ArrayList;

import pablogarcia.dotournament.localdatabase.exception.LocalDatabaseException;
import pablogarcia.dotournament.model.Sport;

/**
 * Created by V on 17/5/16.
 */
public interface SportLocalTable {

    String TABLE_NAME = "Sport";

    String COLUMN_ID             = "id";
    String COLUMN_NAME           = "name";
    String COLUMN_SUMARY         = "sumary";
    String COLUMN_DESCRIPTION    = "description";
    String COLUMN_IMAGE          = "image";

    String[] PROJECTION_TOTAL = {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_SUMARY,
            COLUMN_DESCRIPTION,
            COLUMN_IMAGE
    };

    String ID            = COLUMN_ID             + " TEXT, ";
    String NAME          = COLUMN_NAME           + " TEXT, ";
    String SUMARY        = COLUMN_SUMARY         + " TEXT, ";
    String DESCRIPTION   = COLUMN_DESCRIPTION    + " TEXT, ";
    String IMAGE         = COLUMN_IMAGE          + " TEXT";

    String CREATE_TABLE = String.format("CREATE TABLE %s (%s %s %s %s %s);",
            TABLE_NAME,
            ID,
            NAME,
            SUMARY,
            DESCRIPTION,
            IMAGE);

    void insertSport(Sport sport) throws LocalDatabaseException;
    void insertAllSports(ArrayList<Sport> sports) throws LocalDatabaseException;
    void updateSport(Sport sport);
    Sport getSport(String id) throws LocalDatabaseException;
    ArrayList<Sport> getAllSports();

}
