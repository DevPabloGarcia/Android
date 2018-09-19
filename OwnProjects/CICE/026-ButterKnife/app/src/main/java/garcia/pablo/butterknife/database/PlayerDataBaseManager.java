package garcia.pablo.butterknife.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import garcia.pablo.butterknife.database.exceptions.DatabaseException;
import garcia.pablo.butterknife.model.Player;

/**
 * Created by V on 25/1/16.
 */
public class PlayerDataBaseManager implements PlayerTable {

    Context context;
    DataBaseManager dataBaseManager;

    public PlayerDataBaseManager(Context context) {

        this.context = context;
        dataBaseManager = DataBaseManager.getInstance(context);

    }

    @Override
    public long insertTrastos(Player player) throws DatabaseException {

        SQLiteDatabase db = dataBaseManager.getWritableDatabase();

        ContentValues row = new ContentValues();

        row.put(PlayerTable.COLUMN_NAME, player.getName());
        row.put(PlayerTable.COLUMN_SURNAME, player.getSurname());
        row.put(PlayerTable.COLUMN_NICK, player.getNick());
        row.put(PlayerTable.COLUMN_ADDRESS, player.getAddress());
        row.put(PlayerTable.COLUMN_EMAIL, player.getEmail());
        row.put(PlayerTable.COLUMN_DNI, player.getDni());
        row.put(PlayerTable.COLUMN_AGE, player.getAge());
        row.put(PlayerTable.COLUMN_RANKING, player.getRanking());

        long id = db.insert(PlayerTable.TABLE_NAME,null,row);

        if (id == -1){
            throw new DatabaseException("No se ha podido insertar.");
        }

        return id;
    }
}
