package pablo.com.mipiso.ui.main.taskList;

import java.util.ArrayList;

import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BaseView;

public interface TasksListView extends BaseView {

    void initializeRecyclerView();

    void updateUsers(ArrayList<User> users);

}
