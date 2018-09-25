package pablo.com.mipiso.managers.dataManager.repository;

import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import pablo.com.mipiso.managers.dataManager.BaseCallback;
import pablo.com.mipiso.managers.mappingManager.UserMapper;
import pablo.com.mipiso.model.Flat;
import pablo.com.mipiso.model.Paid;
import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BaseActivity;

public class MockDataRepository extends BaseRepository implements DataRepository {

    /**
     * gson object for parse data in json format
     */
    private Gson gson;

    public MockDataRepository() {
        this.gson = new Gson();
    }

    @Override
    public void getUsers(final BaseCallback listener) {
        try {
            final String serverResponseString = readAsset("users.json");
            new Thread(new Runnable() {
                public void run() {
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<User> response = gson.fromJson(serverResponseString, new TypeToken<ArrayList<User>>() {
                            }.getType());
                            listener.onSuccess(response);
                        }
                    };
                    mainHandler.postDelayed(myRunnable, 1000);
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveFlat(Flat flat, BaseCallback listener) {

    }

    @Override
    public void getFlat(String id, BaseCallback listener) {

    }

    @Override
    public void getFlats(BaseCallback listener) {

    }

    @Override
    public void savePaid(Paid paid, BaseCallback listener) {

    }

    @Override
    public void getPaid(String id, BaseCallback listener) {

    }

    @Override
    public void login(String userName, String userPass, BaseCallback listener) {

    }

    @Override
    public void registerUser(String userName, String userPass, BaseCallback listener) {

    }

    @Override
    public void getUser(String id, final BaseCallback listener){
        try {
            final String serverResponseString = readAsset("user.json");
            new Thread(new Runnable() {
                public void run() {
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            User response = gson.fromJson(serverResponseString, User.class);
                            listener.onSuccess(response);
                        }
                    };
                    mainHandler.postDelayed(myRunnable, 1000);
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readAsset(final String assetsName) throws IOException {
        String fileContent = "";
        AssetManager assetManager = BaseActivity.getContext().getAssets();
        InputStream in;
        in = assetManager.open(assetsName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        fileContent = sb.toString();
        in.close();
        in = null;
        return fileContent;
    }
}
