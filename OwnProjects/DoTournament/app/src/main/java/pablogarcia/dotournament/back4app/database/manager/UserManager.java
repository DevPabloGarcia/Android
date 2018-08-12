package pablogarcia.dotournament.back4app.database.manager;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

import pablogarcia.dotournament.back4app.database.tables.TableUser;
import pablogarcia.dotournament.model.User;

/**
 * Created by V on 13/4/16.
 */
public class UserManager implements TableUser{

    Context context;

    public UserManager(Context context) {
        this.context = context;
    }

    @Override
    public void singUpUser(String name, String surname, String email , String pass, boolean isAdmin, byte[] image, final SignUpCallback callback){

        final ParseUser parseUser = new ParseUser();

        String imageName = "user_"+System.currentTimeMillis()+".png";

        parseUser.setUsername(name);
        parseUser.setEmail(email);
        parseUser.setPassword(pass);
        parseUser.put(TableUser.COLUMN_SURNAME, surname);
        parseUser.setACL(createACL());
        putRole(parseUser, isAdmin);

        final ParseFile file = new ParseFile(imageName, image);
        file.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null){
                    parseUser.put(TableUser.COLUMN_IMAGE, file);
                    parseUser.signUpInBackground(callback);
                }
            }
        });
    }

    @Override
    public void updateUser(String name, String surname, String mail, byte[] image, final SignUpCallback callback){

        final ParseUser user = ParseUser.getCurrentUser();
        String imageName = "user_"+System.currentTimeMillis()+".png";

        user.setUsername(name);
        user.put(TableUser.COLUMN_SURNAME, surname);
        user.setEmail(mail);

        final ParseFile file = new ParseFile(imageName, image);
        file.saveInBackground(new SaveCallback() {

            @Override
            public void done(ParseException e) {
                if (e==null){
                    user.put(TableUser.COLUMN_IMAGE, file);
                    user.signUpInBackground(callback);
                }
            }
        });

    }

    @Override
    public void logginUser(String name, String pass,LogInCallback logInCallback){
        ParseUser.logInInBackground(name, pass, logInCallback);
    }

    @Override
    public boolean isUserAdmin(ParseUser user){
        if(user!=null) {
            return user.getString(TableUser.COLUMN_ROLE).equals(TableUser.ROLE_ADMIN);
        } else{
            return false;
        }
    }

    @Override
    public void getAllPlayers(FindCallback<ParseUser> callback){

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.orderByAscending(TableUser.COLUMN_NAME);
        query.whereEqualTo(TableUser.COLUMN_ROLE, TableUser.ROLE_PLAYER);
        query.findInBackground(callback);

    }

    @Override
    public User getUser(String id) throws ParseException {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        return parseUserToUser(query.get(id));
    }

    public ArrayList<User> parseUsersToUsers(List<ParseUser> users){
        ArrayList<User> players = new ArrayList<>();
        for (ParseUser player: users) {
            players.add(parseUserToUser(player));
        }
        return players;
    }

    @Override
    public User getCurrentUser(){
        ParseUser user = ParseUser.getCurrentUser();
        return parseUserToUser(user);
    }

    //region Private Methods
    private ParseACL createACL(){

        ParseACL parseACL = new ParseACL();
        parseACL.setPublicReadAccess(true);
        return parseACL;
    }

    private void putRole(ParseUser parseUser, Boolean isAdmin){
        if(isAdmin){
            parseUser.put(TableUser.COLUMN_ROLE,TableUser.ROLE_ADMIN);
        }else{
            parseUser.put(TableUser.COLUMN_ROLE,TableUser.ROLE_PLAYER);
        }
    }

    protected User parseUserToUser(ParseUser parseUser){

        ParseFile parseFile = parseUser.getParseFile(TableUser.COLUMN_IMAGE);

        User user = new User();
        user.setId(parseUser.getObjectId());
        user.setName(parseUser.getUsername());
        user.setSurname(parseUser.getString(TableUser.COLUMN_SURNAME));
        user.setEmail(parseUser.getEmail());
        user.setImage(parseFile.getUrl());

        return user;

    }

    //endregion
}
