package pablogarcia.dotournament.back4app.database.tables;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import pablogarcia.dotournament.model.User;

/**
 * Created by V on 5/5/16.
 */
public interface TableUser {

    String TABLE_NAME       = "User";

    String COLUMN_NAME      = "username";
    String COLUMN_SURNAME   = "surname";
    String COLUMN_IMAGE     = "image";
    String COLUMN_ROLE      = "role";

    String ROLE_ADMIN       = "admin";
    String ROLE_PLAYER      = "player";

    /**
     * Sing up the user into the external database
     * @param name of the user
     * @param surname of the user
     * @param email of the user
     * @param pass of the user
     * @param isAdmin role of the user
     * @param image of the user
     * @param callback with the method which will be executed
     */
    void singUpUser(String name, String surname, String email , String pass, boolean isAdmin, byte[] image, final SignUpCallback callback);

    /**
     * Update the user info
     * @param name of the user
     * @param surname of the user
     * @param mail of the user
     * @param image of the user
     * @param callback with the method which will be executed
     */
    void updateUser(String name, String surname, String mail, byte[] image, final SignUpCallback callback);

    /**
     * Loggin the user and create session token
     * @param name of the user
     * @param pass of the user
     * @param logInCallback with the method which will be executed
     */
    void logginUser(String name, String pass,LogInCallback logInCallback);

    /**
     * Get a list of all the players registered in the external database
     * @param callback with the method wich will be executed
     */
    void getAllPlayers(FindCallback<ParseUser> callback);

    /**
     * Get the user we are searching for
     * @param id of the user
     * @return the user
     * @throws ParseException
     */
    User getUser(String id) throws ParseException;

    /**
     * Get the user role
     * @param user which we wanted to know the role
     * @return true if he is admin, false in other case
     */
    boolean isUserAdmin(ParseUser user);

    /**
     * Get the current user
     * @return the user
     */
    User getCurrentUser();

}
