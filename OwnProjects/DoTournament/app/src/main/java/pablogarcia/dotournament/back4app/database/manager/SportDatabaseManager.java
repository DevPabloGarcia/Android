package pablogarcia.dotournament.back4app.database.manager;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import pablogarcia.dotournament.back4app.database.tables.TableSport;
import pablogarcia.dotournament.model.Sport;

/**
 * Created by V on 26/4/16.
 */
public class SportDatabaseManager implements TableSport{

    @Override
    public ArrayList<Sport> getAllSports() throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_NAME);
        query.orderByAscending(COLUMN_NAME);
        List<ParseObject> objects = query.find();
        return parseObjectsToSports(objects);
    }

    @Override
    public Sport getSport(String id) throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_NAME);
        ParseObject object = query.get(id);
        return parseObjectToSport(object);

    }

    public Sport parseObjectToSport(ParseObject parseObject){

        //parseObject.fetchIfNeeded();

        ParseFile parseFile = parseObject.getParseFile(COLUMN_IMAGE);

        Sport sport = new Sport();
        sport.setId(parseObject.getObjectId());
        sport.setName(parseObject.getString(COLUMN_NAME));
        sport.setSumary(parseObject.getString(COLUMN_SUMARY));
        sport.setDescription(parseObject.getString(COLUMN_DESCRIPTION));
        sport.setImage(parseFile.getUrl());

        return sport;

    }

    //region Private Methods

    private ArrayList<Sport> parseObjectsToSports(List<ParseObject> objects) {
        ArrayList<Sport> sports = new ArrayList<>();
        for (ParseObject object: objects) {
            sports.add(parseObjectToSport(object));
        }
        return sports;
    }

    //endregion

}
