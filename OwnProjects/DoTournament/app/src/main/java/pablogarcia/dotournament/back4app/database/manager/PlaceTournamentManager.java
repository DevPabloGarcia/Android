package pablogarcia.dotournament.back4app.database.manager;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import pablogarcia.dotournament.back4app.database.tables.TablePlace;
import pablogarcia.dotournament.model.PlaceTournament;

/**
 * Created by V on 2/7/16.
 */
public class PlaceTournamentManager implements TablePlace {

    @Override
    public PlaceTournament getPlace(String id) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_NAME);
        ParseObject object = query.get(id);
        return parseObjectToPlace(object);
    }

    @Override
    public String savePlace(PlaceTournament placeTournament) throws ParseException {

        ParseObject parseObject = new ParseObject(TABLE_NAME);

        parseObject.put(COLUMN_NAME, placeTournament.getName());
        ParseGeoPoint geoPoint = new ParseGeoPoint(placeTournament.getLatLng().latitude , placeTournament.getLatLng().longitude);
        parseObject.put(COLUMN_LATLNG, geoPoint);
        parseObject.put(COLUMN_ADDRESS, placeTournament.getAddress());

        parseObject.save();

        return parseObject.getObjectId();
    }

    protected PlaceTournament parseObjectToPlace(ParseObject object){
        PlaceTournament placeTournament = new PlaceTournament();
        placeTournament.setName(object.getString(COLUMN_NAME));
        ParseGeoPoint parseGeoPoint = object.getParseGeoPoint(COLUMN_LATLNG);
        placeTournament.setAddress(object.getString(COLUMN_ADDRESS));

        LatLng latLng = new LatLng(parseGeoPoint.getLatitude(),parseGeoPoint.getLongitude());

        placeTournament.setLatLng(latLng);

        return placeTournament;
    }

}
