package pablogarcia.dotournament.back4app.database.tables;

import com.parse.ParseException;

import pablogarcia.dotournament.model.PlaceTournament;

/**
 * Created by V on 2/7/16.
 */
public interface TablePlace {

    String TABLE_NAME = "Place";

    String COLUMN_NAME      = "name";
    String COLUMN_LATLNG    = "latlng";
    String COLUMN_ADDRESS   = "address";

    /**
     *
     * @param id
     * @return the place tournamet storaged in back4app
     * @throws ParseException
     */
    PlaceTournament getPlace(String id) throws ParseException;

    /**
     *
     * @param placeTournament
     * @return the id of the place saved.
     * @throws ParseException
     */
    String savePlace(PlaceTournament placeTournament) throws ParseException;
}
