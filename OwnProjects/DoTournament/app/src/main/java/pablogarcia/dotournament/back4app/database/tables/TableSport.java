package pablogarcia.dotournament.back4app.database.tables;

import com.parse.ParseException;

import java.util.ArrayList;

import pablogarcia.dotournament.model.Sport;

/**
 * Created by V on 26/4/16.
 */
public interface TableSport {

    String TABLE_NAME = "Sport";

    String COLUMN_NAME          = "name";
    String COLUMN_SUMARY        = "sumary";
    String COLUMN_DESCRIPTION   = "description";
    String COLUMN_IMAGE         = "image";

    /**
     * Get all the sport stored in the external database
     * @return a list of all the sports stored in the external database
     * @throws ParseException
     */
    ArrayList<Sport> getAllSports() throws ParseException;

    /**
     * Get the sport we are searching for
     * @param id of the sport which we are searching
     * @return the Sport
     * @throws ParseException
     */
    Sport getSport(String id) throws ParseException;

}
