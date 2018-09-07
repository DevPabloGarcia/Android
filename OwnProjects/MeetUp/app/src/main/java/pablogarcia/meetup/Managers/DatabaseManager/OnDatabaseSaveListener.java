package pablogarcia.meetup.Managers.DatabaseManager;

import com.google.android.gms.tasks.Task;

public interface OnDatabaseSaveListener {

    void onSaveSuccess(Task<Void> task);
    void onSaveFailure(Exception e);

}
