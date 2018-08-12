package pablogarcia.dotournament.back4app.database.tables;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

import pablogarcia.dotournament.model.Match;

/**
 * Created by V on 17/5/16.
 */
public interface TableMatch {

    String TABLE_NAME = "Match";

    String COLUMN_CREATOR       = "creator";
    String COLUMN_SPORT         = "sport";
    String COLUMN_DATE          = "date";
    String COLUMN_INIT_TIME     = "init_time";
    String COLUMN_END_TIME      = "end_time";
    String COLUMN_MAX_PLAYERS   = "max_players";
    String COLUMN_PLAYERS       = "players";
    String COLUMN_PLACE         = "place";

    /**
     * Save the match into the external database
     * @param match to save in the external database
     * @return the Id of the saved Match
     * @throws ParseException
     */
    String saveMatch(Match match) throws ParseException;

    /**
     * Get a list of matches
     * @param query with the matches condition
     * @return the matches list.
     * @throws ParseException
     */
    ArrayList<Match> getMaches(ParseQuery<ParseObject> query) throws ParseException;

    /**
     * Join the current user to the match
     * @param matchId to which the user wanted to join in
     * @throws ParseException
     */
    void addCurrentPlayerToMatch(String matchId) throws ParseException;

    /**
     * Remove the current user for the match
     * @param matchId wich the user wanted to remove for
     * @throws ParseException
     */
    void removeCurrentPlayerFromMatch(String matchId) throws ParseException;
}
