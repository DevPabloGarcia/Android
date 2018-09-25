package pablo.com.mipiso.managers.appPreferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import pablo.com.mipiso.BaseApplication;
import pablo.com.mipiso.model.User;

public class AppPreferences {

    private static AppPreferences mInstance;
    private SharedPreferences mPreferences;

    public static String USER_ID        = "pref_user_id";
    public static String USER_NAME      = "pref_user_name";
    public static String USER_FLAT_ID   = "pref_user_flat";
    public static String USER_TAKS      = "pref_user_task";
    public static String USER_PAID      = "pre_user_paid";


    private AppPreferences() {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getAppContext());
    }

    public static AppPreferences getInstance() {
        if (mInstance == null)
            mInstance = new AppPreferences();
        return mInstance;
    }

    public void setPreferenceString(String key, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defaultValue) {
        return mPreferences.getString(key, defaultValue);
    }

    public void setPreferenceInt(String key, int value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int defaultValue) {
        return mPreferences.getInt(key, defaultValue);
    }

    public void saveUser(User user){
        setPreferenceString(USER_ID, user.getId());
        setPreferenceString(USER_NAME, user.getName());
        setPreferenceString(USER_FLAT_ID, user.getFlat() != null ? user.getFlat().getId() : "");
        setPreferenceString(USER_TAKS, user.getCurrentTask() != null ? user.getCurrentTask().getId() : "");
        setPreferenceInt(USER_PAID, user.getNextPaid());
    }

    public String getUserFlat(){
        return getString(USER_FLAT_ID, "");
    }
}
