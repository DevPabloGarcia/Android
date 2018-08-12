package pablogarcia.dotournament.back4app.database.manager;


import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pablo.garcia on 1/6/16.
 */
public class DatabaseManager {

    public ParseQuery<ParseObject> createQuery(String tableName,
                                               ArrayList<String> includes,
                                               String orderByAsc,
                                               String orderByDesc,
                                               HashMap<String, Object> whereEqualTo,
                                               HashMap<String, Object> whereNotEqualTo,
                                               HashMap<String, Date> gratherThan,
                                               HashMap<String, Date> lessThan){

        ParseQuery<ParseObject> query = ParseQuery.getQuery(tableName);

        if(includes!=null){
            for(int i = 0; i < includes.size(); i++)
            query.include(includes.get(i));
        }

        if(orderByAsc!=null){
            query.orderByAscending(orderByAsc);
        }

        if(orderByDesc!=null){
            query.orderByDescending(orderByDesc);
        }

        if(whereEqualTo!=null){
            if(!whereEqualTo.isEmpty()){
                for(Map.Entry<String, Object> entry : whereEqualTo.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    query.whereEqualTo(key, value);
                }
            }
        }

        if(whereNotEqualTo!=null){
            if(!whereNotEqualTo.isEmpty()){
                for(Map.Entry<String, Object> entry : whereNotEqualTo.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    query.whereNotEqualTo(key, value);
                }
            }
        }

        if(gratherThan!=null){
            if(!gratherThan.isEmpty()){
                for(Map.Entry<String, Date> entry : gratherThan.entrySet()) {
                    String key = entry.getKey();
                    Date value = entry.getValue();
                    query.whereGreaterThanOrEqualTo(key, value);
                }
            }
        }

        if(lessThan!=null){
            if(!lessThan.isEmpty()){
                for(Map.Entry<String, Date> entry : lessThan.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    query.whereLessThanOrEqualTo(key, value);
                }
            }
        }

        return query;
    }
}
