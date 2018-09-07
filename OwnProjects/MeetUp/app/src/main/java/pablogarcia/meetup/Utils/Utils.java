package pablogarcia.meetup.Utils;

public class Utils {

    public static String twoCharacters(int toConvert){
        String toReturn = "";

        if(toConvert >= 0 && toConvert <= 9){
            toReturn = "0"+toConvert;
        }else{
            toReturn = Integer.toString(toConvert);
        }

        return toReturn;
    }

}
