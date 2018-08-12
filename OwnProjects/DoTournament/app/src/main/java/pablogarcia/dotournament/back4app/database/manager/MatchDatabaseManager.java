package pablogarcia.dotournament.back4app.database.manager;


import android.content.Context;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import pablogarcia.dotournament.back4app.database.tables.TableMatch;
import pablogarcia.dotournament.back4app.database.tables.TablePlace;
import pablogarcia.dotournament.back4app.database.tables.TableSport;
import pablogarcia.dotournament.model.Match;
import pablogarcia.dotournament.model.PlaceTournament;
import pablogarcia.dotournament.model.Sport;
import pablogarcia.dotournament.model.User;

/**
 * Created by V on 17/5/16.
 */
public class MatchDatabaseManager implements TableMatch{

    DatabaseManager databaseManager;
    SportDatabaseManager sportDatabaseManager;
    UserManager userManager;
    PlaceTournamentManager placeTournamentManager;

    public MatchDatabaseManager(Context context) {
        sportDatabaseManager = new SportDatabaseManager();
        userManager = new UserManager(context);
        databaseManager = new DatabaseManager();
        placeTournamentManager = new PlaceTournamentManager();
    }

    @Override
    public ArrayList<Match> getMaches(ParseQuery<ParseObject> query) throws ParseException {

        List<ParseObject> objects = query.find();
        return parseAllObjectsToMatchList(objects);

    }

    @Override
    public void addCurrentPlayerToMatch(String matchId) throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_NAME);
        ParseObject object = query.get(matchId);

        ParseRelation<ParseUser> relation = object.getRelation(COLUMN_PLAYERS);
        relation.add(ParseUser.getCurrentUser());

        object.save();

    }

    @Override
    public void removeCurrentPlayerFromMatch(String matchId) throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_NAME);
        ParseObject match = query.get(matchId);
        ParseRelation relation = match.getRelation(COLUMN_PLAYERS);
        relation.remove(ParseUser.getCurrentUser());

        match.save();
    }

    @Override
    public String saveMatch(Match match) throws ParseException {

        try{
            ParseObject parseObject = new ParseObject(TABLE_NAME);

            parseObject.put(COLUMN_DATE, match.getDate());
            parseObject.put(COLUMN_INIT_TIME, match.getInitTime());
            parseObject.put(COLUMN_END_TIME, match.getEndTime());
            parseObject.put(COLUMN_MAX_PLAYERS, match.getMaxPlayer());
            parseObject.put(COLUMN_CREATOR, ParseUser.getCurrentUser());

            ParseObject sport = ParseObject.createWithoutData(TableSport.TABLE_NAME,
                    match.getSport().getId());

            String placeId = placeTournamentManager.savePlace(match.getPlace());

            ParseObject place = ParseObject.createWithoutData(TablePlace.TABLE_NAME, placeId);

            parseObject.put(COLUMN_SPORT, sport);
            parseObject.put(COLUMN_PLACE, place);

            ParseRelation<ParseUser> relation = parseObject.getRelation(COLUMN_PLAYERS);
            relation.add(ParseUser.getCurrentUser());

            parseObject.save();

            return parseObject.getObjectId();

        }catch (ParseException e){
            throw new ParseException(e);
        }
    }

    private Date getCurrentDate(){
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public ParseQuery<ParseObject> getCurrentMatchesQuery(){

        HashMap<String, Object> notEqualsTo = new HashMap<>();
        notEqualsTo.put(COLUMN_PLAYERS, ParseUser.getCurrentUser());

        HashMap<String, Date> gratherThan = new HashMap<>();
        gratherThan.put(COLUMN_DATE, getCurrentDate());

        ArrayList<String> includes = new ArrayList<>();
        includes.add(COLUMN_SPORT);
        includes.add(COLUMN_CREATOR);
        includes.add(COLUMN_PLACE);

        return databaseManager.createQuery(TABLE_NAME,
                includes,
                COLUMN_DATE,
                null,
                null,
                notEqualsTo,
                gratherThan,
                null);
    }

    public ParseQuery<ParseObject> getUserMatchesQuery(){

        HashMap<String, Object> equalsTo = new HashMap<>();
        equalsTo.put(COLUMN_PLAYERS, ParseUser.getCurrentUser());

        HashMap<String, Date> gratherThan = new HashMap<>();
        gratherThan.put(COLUMN_DATE, getCurrentDate());

        ArrayList<String> includes = new ArrayList<>();
        includes.add(COLUMN_SPORT);
        includes.add(COLUMN_CREATOR);
        includes.add(COLUMN_PLACE);

        return databaseManager.createQuery(TABLE_NAME,
                includes,
                COLUMN_DATE,
                null,
                equalsTo,
                null,
                gratherThan,
                null);
    }

    public ParseQuery<ParseObject> getFinishedMatchesQuery(){

        HashMap<String, Object> equalsTo = new HashMap<>();
        equalsTo.put(COLUMN_PLAYERS, ParseUser.getCurrentUser());

        HashMap<String, Date> lessThan = new HashMap<>();
        lessThan.put(COLUMN_DATE, getCurrentDate());

        ArrayList<String> includes = new ArrayList<>();
        includes.add(COLUMN_SPORT);
        includes.add(COLUMN_CREATOR);
        includes.add(COLUMN_PLACE);

        return databaseManager.createQuery(TABLE_NAME,
                includes,
                COLUMN_DATE,
                null,
                equalsTo,
                null,
                null,
                lessThan);
    }

    private Match parseObjectToMatch(ParseObject object) throws ParseException {

        try{

            Match match = new Match();

            match.setId(object.getObjectId());
            match.setDate(object.getDate(COLUMN_DATE));
            match.setInitTime(object.getDate(COLUMN_INIT_TIME));
            match.setEndTime(object.getDate(COLUMN_END_TIME));
            match.setMaxPlayer(object.getInt(COLUMN_MAX_PLAYERS));

            ParseObject objectSport = object.getParseObject(COLUMN_SPORT);
            if(objectSport!= null){
                Sport sport = sportDatabaseManager.parseObjectToSport(objectSport);
                match.setSport(sport);
            }

            ParseUser userCreator = object.getParseUser(COLUMN_CREATOR);
            if (userCreator!=null){
                User user = userManager.parseUserToUser(userCreator);
                match.setOwner(user);
            }

            ParseObject objectPlace = object.getParseObject(COLUMN_PLACE);
            if(objectPlace!=null){
                PlaceTournament place = placeTournamentManager.parseObjectToPlace(objectPlace);
                match.setPlace(place);
            }

            ParseRelation<ParseUser> relation = object.getRelation(COLUMN_PLAYERS);
            ParseQuery query = relation.getQuery();
            List users = query.find();

            ArrayList players = userManager.parseUsersToUsers(users);

            match.setPlayers(players);

            return match;

        } catch (ParseException e) {
            throw new ParseException(e);
        }
    }

    private ArrayList<Match> parseAllObjectsToMatchList(List<ParseObject> objects) throws ParseException {

        ArrayList<Match> matches = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++){
            matches.add(parseObjectToMatch(objects.get(i)));
        }
        return matches;
    }

}
