package pablogarcia.meetup.Managers.DatabaseManager;

import java.util.ArrayList;

import pablogarcia.meetup.Model.Meet;

public interface OnDatabaseGetListener {

    void onGetSuccess(ArrayList<Meet> meets);
}
