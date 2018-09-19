package garcia.pablo.butterknife.api;

import android.content.Context;

import garcia.pablo.butterknife.database.PlayerDataBaseManager;
import garcia.pablo.butterknife.database.exceptions.DatabaseException;
import garcia.pablo.butterknife.model.Player;

/**
 * Created by V on 25/1/16.
 */
public class ApiManager {

    PlayerDataBaseManager playerDataBaseManager;

    public ApiManager(Context context) {

        this.playerDataBaseManager = new PlayerDataBaseManager(context);

    }

    public Player insertPlayer(Player player) throws PlayerException {

        long id;

        try {
            id = playerDataBaseManager.insertTrastos(player);
            player.setId(id);
        } catch (DatabaseException e) {
            throw new PlayerException(e.getMessage());
        }
        return player;
    }
}
