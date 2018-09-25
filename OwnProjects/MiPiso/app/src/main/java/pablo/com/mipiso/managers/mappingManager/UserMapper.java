package pablo.com.mipiso.managers.mappingManager;

import com.parse.ParseUser;

import pablo.com.mipiso.model.User;


public class UserMapper extends BaseMapper<ParseUser, User>{


    private static String USER_ID              = "objectId";
    private static String USER_NAME            = "username";
    private static String USER_SURNAME         = "surname";
    private static String USER_IMAGE           = "image";
    private static String USER_PHONE           = "phone";
    private static String USER_CURRENT_TASK    = "currentTask";
    private static String USER_ROOM            = "room";
    private static String USER_PAIDS           = "paids";
    private static String USER_NEXT_PAID       = "nextPaid";
    private static String USER_FLAT            = "flat";

    @Override
    public User map(ParseUser parseUser){

        User user = new User();

        user.setName(parseUser.getUsername());

        user.setId(parseUser.getObjectId());
        user.setName(parseUser.getUsername());
        user.setSurname(parseUser.getString(USER_SURNAME));
        user.setImage(parseUser.getString(USER_IMAGE));
        user.setPhone(parseUser.getString(USER_PHONE));

        return user;
    }

}
