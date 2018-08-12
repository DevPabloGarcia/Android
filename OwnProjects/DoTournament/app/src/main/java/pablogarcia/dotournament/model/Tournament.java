package pablogarcia.dotournament.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by V on 23/3/16.
 */
public class Tournament implements Parcelable{

    private String idTournament;
    private String nameTournament;
    private String descriptionTournament;
    private Sport sportTournament;
    private int maxPlayers;
    private ArrayList<User> playersTournament;

    public Tournament() {
    }

    public Sport getSportTournament() {
        return sportTournament;
    }

    public void setSportTournament(Sport sportTournament) {
        this.sportTournament = sportTournament;
    }

    public String getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(String idTournament) {
        this.idTournament = idTournament;
    }

    public String getNameTournament() {
        return nameTournament;
    }

    public void setNameTournament(String nameTournament) {
        this.nameTournament = nameTournament;
    }

    public String getDescriptionTournament() {
        return descriptionTournament;
    }

    public void setDescriptionTournament(String descriptionTournament) {
        this.descriptionTournament = descriptionTournament;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public ArrayList<User> getPlayersTournament() {
        return playersTournament;
    }

    public void setPlayersTournament(ArrayList<User> playersTournament) {
        this.playersTournament = playersTournament;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idTournament);
        dest.writeString(nameTournament);
        dest.writeString(descriptionTournament);
        dest.writeParcelable(sportTournament, flags);
        dest.writeInt(maxPlayers);
        dest.writeTypedList(playersTournament);
    }

    protected Tournament(Parcel in) {
        idTournament = in.readString();
        nameTournament = in.readString();
        descriptionTournament = in.readString();
        sportTournament = in.readParcelable(Sport.class.getClassLoader());
        maxPlayers = in.readInt();
        playersTournament = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<Tournament> CREATOR = new Creator<Tournament>() {
        @Override
        public Tournament createFromParcel(Parcel in) {
            return new Tournament(in);
        }

        @Override
        public Tournament[] newArray(int size) {
            return new Tournament[size];
        }
    };
}
