package pablogarcia.dotournament.apimanager;


import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import pablogarcia.dotournament.back4app.database.manager.MatchDatabaseManager;
import pablogarcia.dotournament.back4app.database.manager.PlaceTournamentManager;
import pablogarcia.dotournament.back4app.database.manager.SportDatabaseManager;
import pablogarcia.dotournament.back4app.database.manager.UserManager;
import pablogarcia.dotournament.localdatabase.exception.LocalDatabaseException;
import pablogarcia.dotournament.localdatabase.sport.SportLocalDatabaseManager;
import pablogarcia.dotournament.model.Match;
import pablogarcia.dotournament.model.PlaceTournament;
import pablogarcia.dotournament.model.User;
import pablogarcia.dotournament.model.Sport;
import pablogarcia.dotournament.back4app.session.SessionManager;
import pablogarcia.dotournament.utils.Consts;

/**
 * Created by V on 8/3/16.
 */
public class ApiManager {

    private static ApiManager mInstance;
    private UserManager userManager;
    private SessionManager sessionManager;
    private SportDatabaseManager sportDatabaseManager;
    private MatchDatabaseManager matchDatabaseManager;
    private SportLocalDatabaseManager sportLocalDatabaseManager;
    private PlaceTournamentManager placeTournamentManager;

    /**
     * In order to implement Singleton Pattern.
     * @param context
     * @return the instance of ApiManager.
     */
    public static ApiManager getInstance(Context context) {

        if(mInstance == null){
            mInstance = new ApiManager(context);
        }
        return mInstance;
    }

    /**
     * Constructor of the ApiManager Class.
     * @param context
     */
    private ApiManager(Context context){
        this.userManager = new UserManager(context);
        this.sessionManager = new SessionManager(context);
        this.sportDatabaseManager = new SportDatabaseManager();
        this.matchDatabaseManager = new MatchDatabaseManager(context);
        this.sportLocalDatabaseManager = new SportLocalDatabaseManager(context);
        this.placeTournamentManager = new PlaceTournamentManager();
    }

    //region Session

    /**
     * Return if the user is logged in
     * @return true if the user is logged in, false in other case
     */
    public boolean isLoggedIn(){
        return this.sessionManager.isLoggedIn();
    }

    /**
     * Log out of the app, and return to the login activity
     */
    public void logOut(){
        this.sessionManager.logOut();
    }

    /**
     * Check if exists a user logged and return to the login activity in the false case
     */
    public void checkSession(){
        this.sessionManager.checkSession();
    }

    //endregion

    //region User

    public void logginUser(String name, String pass, LogInCallback logInCallback){
        this.userManager.logginUser(name, pass, logInCallback);
    }

    public void updateUser(String name, String surname, String mail, byte[] image, final SignUpCallback callback){
        userManager.updateUser(name, surname, mail, image, callback);
    }

    public void signUpUser(String name, String surname, String email , String pass, Boolean isAdmin,byte[] image, SignUpCallback callback){
        this.userManager.singUpUser(name, surname, email, pass, isAdmin, image, callback);
    }


    public boolean isUserAdmin(ParseUser user){
        return userManager.isUserAdmin(user);
    }

    //endregion

    //region Local Database

    public void insertAllSportsInLocalDatabase(ArrayList<Sport> sports) throws LocalDatabaseException {
        sportLocalDatabaseManager.insertAllSports(sports);
    }

    public ArrayList<Sport> getAllSportsFromLocalDatabase(){
        return sportLocalDatabaseManager.getAllSports();
    }

    public Sport getSportFromLocalDatabase(String id) throws LocalDatabaseException {
        return sportLocalDatabaseManager.getSport(id);
    }

    //endregion

    //region External Database

    public ArrayList<Sport> getAllSportsFromDatabase() throws ParseException {
        return sportDatabaseManager.getAllSports();
    }

    public User getPlayer(String id) throws ParseException {
        return userManager.getUser(id);
    }

    public void getAllPlayersFromDatabase(FindCallback<ParseUser> callback){
        userManager.getAllPlayers(callback);
    }

    public User getCurrentUser(){
        return userManager.getCurrentUser();
    }

    public ArrayList<User> parseUsersToPlayers(List<ParseUser> users){
        return userManager.parseUsersToUsers(users);
    }

    public String saveMatchInDatabase(Match match) throws ParseException {
        return matchDatabaseManager.saveMatch(match);
    }

    public ArrayList<Match> getMatchesFromDatabase(int matchCondition) throws ParseException {

        ParseQuery<ParseObject> query;

        switch (matchCondition){

            case Consts.USER_MATCHES:
                query = getUserMatchesFromDatabase();
                break;
            case Consts.FINISHED_MATCHES:
                query =  getFinishedMatchesFromDatabase();
                break;
            default:
                query = getCurrentMatchesFromDatabase();
        }

        return matchDatabaseManager.getMaches(query);

    }

    private ParseQuery<ParseObject> getCurrentMatchesFromDatabase() throws ParseException {
        return matchDatabaseManager.getCurrentMatchesQuery();
    }

    private ParseQuery<ParseObject> getUserMatchesFromDatabase() throws ParseException {
        return matchDatabaseManager.getUserMatchesQuery();
    }

    private ParseQuery<ParseObject> getFinishedMatchesFromDatabase() throws ParseException {
        return matchDatabaseManager.getFinishedMatchesQuery();
    }

    public void addPlayerToMatchInDatabase(String matchId) throws ParseException {
        matchDatabaseManager.addCurrentPlayerToMatch(matchId);
    }

    public void removePlayerFromMatch(String matchId) throws ParseException {
        matchDatabaseManager.removeCurrentPlayerFromMatch(matchId);
    }

    //endregion

    //region Views Methods

    public void setErrorToEditText(EditText editText, String message){
        editText.setError(message);
    }

    public boolean validateInputs(ArrayList<EditText> editTexts, String message){

        boolean continueFlag = true;

        for (EditText editText: editTexts) {
            if(editText.getText().toString().isEmpty()){
                setErrorToEditText(editText, message);
                continueFlag = false;
            }
        }
        return continueFlag;
    }

    public boolean validateInputsTime(EditText first, EditText second, String message){
        if (first.getText().toString().compareTo(second.getText().toString())>=0){
            setErrorToEditText(second, message);
            return false;
        }
        return true;
    }

    public boolean validateInputsPass(EditText pass1, EditText pass2, String message){
        if(!pass1.getText().toString().trim().equals(pass2.getText().toString().trim())){
            setErrorToEditText(pass2,message);
            return false;
        }
        return true;
    }

    public String getFinalTimeString(int time){
        String finalTime = String.valueOf(time);
        if (time < 10){
            finalTime = "0"+time;
        }
        return finalTime;
    }

    public void loadImageIntoImageView(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).override(300,300).into(imageView);
    }

    //endregion

    //region Utils

    public Date getDateFormat(int year, int monthOfYear, int dayOfMonth, int hour, int minute){

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DATE, dayOfMonth);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND,0);

        return  cal.getTime();
    }

    public String convertDateInString(Date date){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
        return dateFormat.format(date);

    }

    public String convertTimeInString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
        return dateFormat.format(date);
    }

    //endregion

    //region Share

    public void shareInSocialNetworks(Context context, String text){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    //endregion

}
