package pablogarcia.dotournament.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by V on 9/5/16.
 */
public class Match implements Parcelable{

    private String id;
    private User owner;
    private ArrayList<User> players;
    private Sport sport;
    private Date date;
    private Date initTime;
    private Date endTime;
    private int maxPlayer;
    private PlaceTournament place;

    public Match() {
        players = new ArrayList<>();
    }

    public Match(Sport sport, Date date, Date initTime, Date endTime, int maxPlayer, PlaceTournament place, User owner) {
        this.sport = sport;
        this.date = date;
        this.initTime = initTime;
        this.endTime = endTime;
        this.maxPlayer = maxPlayer;
        this.players = new ArrayList<>();
        this.place = place;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<User> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public void addPlayerToMatch(User user){
        players.add(user);
    }

    public void removePlayerFromMatch(User user){
        int i = 0;
        boolean removed = false;
        while(i<players.size() && !removed){
            if(players.get(i).getId().equals(user.getId())){
                removed = true;
                players.remove(i);
            }
            i++;
        }
    }

    public boolean isAvailableSites(){
        return players.size() < maxPlayer;
    }

    public boolean isPlayerInMatch(String id){
        boolean isInMatch = false;
        int i = 0;
        while(i < players.size() && !isInMatch){
            if(players.get(i).getId().equals(id)){
                isInMatch = true;
            }
            i++;
        }
        return isInMatch;
    }

    public PlaceTournament getPlace() {
        return place;
    }

    public void setPlace(PlaceTournament place) {
        this.place = place;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(owner, flags);
        dest.writeTypedList(players);
        dest.writeParcelable(sport, flags);
        dest.writeInt(maxPlayer);
        dest.writeSerializable(date);
        dest.writeSerializable(initTime);
        dest.writeSerializable(endTime);
        dest.writeParcelable(place, flags);
    }

    protected Match(Parcel in) {
        id = in.readString();
        owner = in.readParcelable(User.class.getClassLoader());
        players = in.createTypedArrayList(User.CREATOR);
        sport = in.readParcelable(Sport.class.getClassLoader());
        maxPlayer = in.readInt();
        date = (java.util.Date)in.readSerializable();
        initTime = (java.util.Date)in.readSerializable();
        endTime = (java.util.Date)in.readSerializable();
        place = in.readParcelable(PlaceTournament.class.getClassLoader());
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    public int getAvailableSites(){
        return maxPlayer - players.size();
    }

}
