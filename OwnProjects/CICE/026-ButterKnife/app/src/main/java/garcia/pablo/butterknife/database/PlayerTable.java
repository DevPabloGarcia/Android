package garcia.pablo.butterknife.database;

import garcia.pablo.butterknife.database.exceptions.DatabaseException;
import garcia.pablo.butterknife.model.Player;

/**
 * Created by V on 24/1/16.
 */
public interface PlayerTable {

    String TABLE_NAME = "t_player";

    String COLUMN_ID        = "id";
    String COLUMN_NAME      = "name";
    String COLUMN_SURNAME   = "surname";
    String COLUMN_NICK      = "nick";
    String COLUMN_ADDRESS   = "address";
    String COLUMN_EMAIL     = "email";
    String COLUMN_DNI       = "dni";
    String COLUMN_AGE       = "age";
    String COLUMN_RANKING   = "ranking";

    String[] PROJECTION_TOTAL = {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_SURNAME,
            COLUMN_NICK,
            COLUMN_ADDRESS,
            COLUMN_EMAIL,
            COLUMN_DNI,
            COLUMN_AGE,
            COLUMN_RANKING
    };

    String ID       = COLUMN_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT";
    String NAME     = COLUMN_NAME       + " TEXT";
    String SURNAME  = COLUMN_SURNAME    + " TEXT";
    String NICK     = COLUMN_NICK       + " TEXT";
    String ADDRESS  = COLUMN_ADDRESS    + " TEXT";
    String EMAIL    = COLUMN_EMAIL      + " TEXT";
    String DNI      = COLUMN_DNI        + " TEXT";
    String AGE      = COLUMN_AGE        + " INTEGER";
    String RANKING  = COLUMN_RANKING    + " INTEGER";


    String CREATE_TABLE = String.format("CREATE TABLE %s ( %s ,%s , %s, %s, %s, %s, %s, %s, %s)",
            TABLE_NAME,
            ID,
            NAME,
            SURNAME,
            NICK,
            ADDRESS,
            EMAIL,
            DNI,
            AGE,
            RANKING);

    long insertTrastos(Player player) throws DatabaseException;

}
