package pablo.com.mipiso.utils;

import java.util.UUID;

public class Utils {

    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
